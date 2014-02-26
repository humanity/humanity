package com.ttaylorr.dev.humanity.server.handlers;

import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.packets.Packet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacketHandler {

    private Map<Class<? extends Packet>, List<HandlerSnapshot>> handlers;

    public PacketHandler() {
        this.handlers = new HashMap<>();
    }

    public void registerHandlers(Listenable listenable) {
        for (Method method : listenable.getClass().getMethods()) {
            if (method.getAnnotation(Handler.class) != null) {
                Handler annotation = method.getAnnotation(Handler.class);
                if (method.getParameterTypes().length == 1) {
                    Class<?> clazz = method.getParameterTypes()[0];
                    if (clazz.isAssignableFrom(Packet.class)) {
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
        List<HandlerSnapshot> handlers = this.handlers.get(handlingType);

        if (!this.handlers.containsKey(handlingType)) {
            handlers = this.handlers.put(handlingType, new ArrayList<HandlerSnapshot>());
        }

        return handlers.add(snapshot);
    }

    public List<HandlerSnapshot> getHandler(Class<? extends Packet> packet) {
        return ImmutableList.copyOf(this.handlers.get(packet));
    }

}
