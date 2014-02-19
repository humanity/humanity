package com.ttaylorr.dev.humanity.server.packets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Runs the Packets that currently live in the SPM.
 *
 * User: Jack Date: 2/16/14 Time: 11:50 PM
 */
class PacketRunner implements Runnable {

    private SimplePacketManager simplePacketManager;

    public PacketRunner(SimplePacketManager simplePacketManager) {
        this.simplePacketManager = simplePacketManager;
    }

    @Override
    public void run() {
        Packet packet = null;
        synchronized (simplePacketManager.packetQueue) {
            packet = simplePacketManager.packetQueue.poll();
        }
        if (packet == null) {
            return;
        }
        for (Method handler : simplePacketManager.packets.get(packet).values()) {
            try {
                if (handler.getParameterTypes()[0].isInstance(packet)) {
                    handler.invoke(simplePacketManager.packets.get(packet).get(handler), packet);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
