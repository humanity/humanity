package com.ttaylorr.dev.humanity.server.packets;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerSnapshot;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketHandler {

    private Map<Class<? extends Packet>, List<HandlerSnapshot>> handlers;
    private HumanityServer server;

    public PacketHandler(HumanityServer server) {
        this.handlers = new HashMap<>();
        this.server = Preconditions.checkNotNull(server, "server");

        this.allowPackets();
    }

    private void allowPackets() {
        this.handlers.put(Packet01KeepAlive.class, new ArrayList<HandlerSnapshot>());
        this.handlers.put(Packet02Handshake.class, new ArrayList<HandlerSnapshot>());
        this.handlers.put(Packet03Disconnect.class, new ArrayList<HandlerSnapshot>());
    }

    public void handlePacket(PacketSnapshot snapshot) {
        Packet packet = snapshot.getPacket();
        ClientConnection owner = snapshot.getOwner();
        for (HandlerSnapshot handler : this.handlers.get(packet.getClass())) {
            if (handler.getHandlingType() == packet.getClass()) {
                try {
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

    private boolean registerPacketHandler(HandlerSnapshot snapshot, Class<? extends Packet> handlingType) {
        if (!this.handlers.containsKey(handlingType)) {
            throw new IllegalArgumentException("cannot handle this type of packet");
        } else {
            List<HandlerSnapshot> handlers = this.handlers.get(handlingType);

            return handlers.add(snapshot);
        }
    }

    public List<HandlerSnapshot> getHandler(Class<? extends Packet> packet) {
        return ImmutableList.copyOf(this.handlers.get(packet));
    }

}
