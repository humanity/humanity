package net.humanity_game.client.packets;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.client.player.Player;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.client.packets.handler.ClientHandlerSnapshot;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.Packet;
import net.humanity_game.server.packets.core.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ClientPacketHandler {

    private static final int INITIAL_PACKET_QUEUE_SIZE = 16;

    private Map<Class<? extends Packet>, PriorityQueue<ClientHandlerSnapshot>> handlers;
    private HumanityClient client;

    public ClientPacketHandler(HumanityClient client) {
        this.handlers = new HashMap<>();
        this.client = Preconditions.checkNotNull(client, "client");

        this.allowPackets();
    }

    private void allowPackets() {
        //packets registered to be used:
        this.handlers.put(Packet01KeepAlive.class,               new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet03Disconnect.class,              new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet04Join.class,                    new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet05PlayerStateChange.class,       new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet06HandUpdate.class,              new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet07CreatePool.class,              new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet08GameChangeState.class,         new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
        this.handlers.put(Packet09UpdatePlayerList.class,        new PriorityQueue<>(INITIAL_PACKET_QUEUE_SIZE, snapshotComparator));
    }

    public void handlePacket(Packet packet) {
        for (ClientHandlerSnapshot handler : this.handlers.get(packet.getClass())) {
            if (handler.getType().equals(packet.getClass())) {
                try {
                    this.client.getLogger().debug("(S->C) received: {}", packet.getClass().getSimpleName());

                    // If the client intended ID is null, it goes to all clients
                    if (handler.getAnnotation().handleSelf() && this.matchesSelf(packet)) {
                        handler.getMethod().invoke(handler.getInstance(), packet);
                    } else if (!handler.getAnnotation().handleSelf() && this.matchesOtherOrNull(packet)) {

                        handler.getMethod().invoke(handler.getInstance(), packet);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean matchesSelf(Packet packet) {
        return this.client.getDefinition().getUUID() == null || this.client.getDefinition().getUUID().equals(packet.getClientId());
    }

    private boolean matchesSelfOrNull(Packet packet) {
        return packet == null || this.matchesSelf(packet);
    }

    private boolean matchesOtherOrNull(Packet packet) {
        return packet.getClientId() == null || !this.matchesSelf(packet);
    }

    public void registerHandlers(Listenable listenable) {
        for (Method method : listenable.getClass().getMethods()) {
            if (method.getAnnotation(ClientHandler.class) != null) {
                ClientHandler annotation = method.getAnnotation(ClientHandler.class);
                if (method.getParameterTypes().length == 1) {
                    Class<?> clazz = method.getParameterTypes()[0];
                    if (Packet.class.isAssignableFrom(clazz)) {
                        ClientHandlerSnapshot snapshot = new ClientHandlerSnapshot(listenable, method, (Class<? extends Packet>) clazz, annotation);
                        this.registerPacketHandler(snapshot, snapshot.getType());
                    } else {
                        throw new IllegalArgumentException("The first argument is not a packet");
                    }
                } else {
                    throw new IllegalArgumentException("No extra parameters may be provided other then the packet type");
                }
            }
        }
    }

    public boolean unregisterHandlers(Listenable listenable) {
        boolean removed = false;

        for (Map.Entry<Class<? extends Packet>, PriorityQueue<ClientHandlerSnapshot>> entry : new HashSet<>(this.handlers.entrySet())) {
            for(ClientHandlerSnapshot handler : new ArrayList<>(entry.getValue())) {
                if(handler.getInstance() == listenable) {
                    this.handlers.get(entry.getKey()).remove(handler);
                    removed = true;
                }
            }
        }

        if (removed) {
            this.client.getLogger().debug("Removed all handlers: " + listenable.getClass().getSimpleName());
        }
        return removed;
    }

    private boolean registerPacketHandler(ClientHandlerSnapshot snapshot, Class<? extends Packet> handlingType) {
        if (!this.handlers.containsKey(handlingType)) {
            throw new IllegalArgumentException("cannot handle this type of packet");
        } else {
            PriorityQueue<ClientHandlerSnapshot> handlers = this.handlers.get(handlingType);

            this.client.getLogger().debug("Registered handler {}.{}", snapshot.getInstance().getClass().getSimpleName(), snapshot.getMethod().getName());
            return handlers.add(snapshot);
        }
    }

    public List<ClientHandlerSnapshot> getHandler(Class<? extends Packet> packet) {
        return ImmutableList.copyOf(this.handlers.get(packet));
    }

    private Comparator<ClientHandlerSnapshot> snapshotComparator = new Comparator<ClientHandlerSnapshot>() {
        @Override
        public int compare(ClientHandlerSnapshot o1, ClientHandlerSnapshot o2) {
            return o1.getAnnotation().priority().compareTo(o2.getAnnotation().priority());
        }
    };

}
