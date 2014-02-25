package com.ttaylorr.dev.humanity.server.packets;

import com.ttaylorr.dev.humanity.server.packets.core.Packet03ClientHandshake;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

public class SimplePacketManager {

    final Map<Class<? extends Packet>, Map<Object, Method>> packets;
    final SynchronousQueue<Packet> packetQueue;

    public SimplePacketManager() {
        this.packets = new HashMap<>();
        this.packetQueue = new SynchronousQueue<>();

        this.setUpPackets();
    }

    private void setUpPackets() {
        this.addPacket(Packet03ClientHandshake.class);
    }

    public void addPacket(Class<? extends Packet> clazz) {
        if (!this.packets.containsKey(clazz)) {
            this.packets.put(clazz, new HashMap<Object, Method>());
        }
    }

    public void registerHandler(Class<? extends Packet> clazz, Object instance, Method handler) throws IllegalArgumentException {
        if (!this.packets.containsKey(clazz)) {
            throw new IllegalArgumentException("No packet can handle that type of packet");
        }
        this.packets.get(clazz).put(instance, handler);
    }

    public void unregisterHandler(Class<? extends Packet> clazz, Method handler) throws IllegalArgumentException {
        if (!this.packets.containsKey(clazz)) {
            throw new IllegalArgumentException("No handler can handle that type of packet");
        }
        this.packets.get(clazz).remove(handler);
    }

    public void queuePacket(Packet packet) {
        this.packetQueue.offer(packet); // this doesn't need to be synchronized because it's a Synchronous list.
    }

}
