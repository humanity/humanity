package net.humanity_game.client;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.sun.javafx.collections.transformation.SortedList;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerSnapshot;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.Packet;
import net.humanity_game.server.packets.core.*;
import net.humanity_game.server.packets.masked.core.Packet09MaskedJoin;
import net.humanity_game.server.packets.masked.core.Packet11MaskedDisconnect;
import net.humanity_game.server.packets.masked.core.Packet12MaskedPlayerStateChange;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ClientPacketHandler {

    private Map<Class<? extends Packet>, SortedList<HandlerSnapshot>> handlers;
    private HumanityClient client;

    public ClientPacketHandler(HumanityClient client) {
        this.handlers = new HashMap<>();
        this.client = Preconditions.checkNotNull(client, "client");

        this.allowPackets();
    }

    private void allowPackets() {
        // Non-masked packets
        this.handlers.put(Packet01KeepAlive.class,               new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet03Disconnect.class,              new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet04Join.class,                    new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet05PlayerStateChange.class,       new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet06HandUpdate.class,              new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet07CreatePool.class,              new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet08GameChangeState.class,         new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));

        // Masked packets
        this.handlers.put(Packet09MaskedJoin.class,              new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet11MaskedDisconnect.class,        new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
        this.handlers.put(Packet12MaskedPlayerStateChange.class, new SortedList<>(new ArrayList<HandlerSnapshot>(), snapshotComparator));
    }

    public void handlePacket(Packet packet) {
        for (HandlerSnapshot handler : this.handlers.get(packet.getClass())) { // TODO sorting
            if (handler.getHandlingType().equals(packet.getClass())) {
                try {
                    this.client.getLogger().debug("(S->C) received: {}", packet.getClass().getSimpleName());
                    handler.getMethod().invoke(handler.getInstance(), packet);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerHandlers(Listenable listenable) {
        for (Method method : listenable.getClass().getMethods()) {
            if (method.getAnnotation(Handler.class) != null) {
                Handler annotation = method.getAnnotation(Handler.class);
                if (method.getParameterTypes().length == 1) {
                    Class<?> clazz = method.getParameterTypes()[0];
                    if (Packet.class.isAssignableFrom(clazz)) {
                        HandlerSnapshot snapshot = new HandlerSnapshot(listenable, method, (Class<? extends Packet>) clazz, annotation);
                        this.registerPacketHandler(snapshot, snapshot.getHandlingType());
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

        for (Map.Entry<Class<? extends Packet>, SortedList<HandlerSnapshot>> entry : new HashSet<>(this.handlers.entrySet())) {
            for(HandlerSnapshot handler : new ArrayList<>(entry.getValue())) {
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

    private boolean registerPacketHandler(HandlerSnapshot snapshot, Class<? extends Packet> handlingType) {
        if (!this.handlers.containsKey(handlingType)) {
            throw new IllegalArgumentException("cannot handle this type of packet");
        } else {
            List<HandlerSnapshot> handlers = this.handlers.get(handlingType);

            this.client.getLogger().debug("Registered handler {}.{}", snapshot.getInstance().getClass().getSimpleName(), snapshot.getMethod().getName());
            return handlers.add(snapshot);
        }
    }

    public List<HandlerSnapshot> getHandler(Class<? extends Packet> packet) {
        return ImmutableList.copyOf(this.handlers.get(packet));
    }

    private Comparator<HandlerSnapshot> snapshotComparator = new Comparator<HandlerSnapshot>() {
        @Override
        public int compare(HandlerSnapshot o1, HandlerSnapshot o2) {
            return o1.getAnnotation().priority().compareTo(o2.getAnnotation().priority());
        }
    };

}
