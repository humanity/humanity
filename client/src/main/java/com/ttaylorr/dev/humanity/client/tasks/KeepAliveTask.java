package com.ttaylorr.dev.humanity.client.tasks;

import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet01KeepAlive;
import org.joda.time.Instant;

import java.util.concurrent.Callable;

public class KeepAliveTask implements Callable<Boolean>, Listenable {

    private final HumanityClient client;

    private Packet01KeepAlive lastSentPacket;
    private Packet01KeepAlive lastReceivedPacket;

    private boolean match = false;

    public KeepAliveTask(HumanityClient client) {
        this.client = client;
        this.client.getPacketHandler().registerHandlers(this);
    }

    @Override
    public Boolean call() throws InterruptedException {
        this.lastSentPacket = new Packet01KeepAlive();

        client.sendPacket(this.lastSentPacket);

        // Hack to wait until we've received a packet
        Instant start = Instant.now();
        while(this.lastReceivedPacket == null) {
            Thread.sleep(100);
        }

        this.client.getPacketHandler().unregisterHandlers(this);
        return this.lastReceivedPacket.equals(this.lastSentPacket);
    }

    @Handler(priority = HandlerPriority.NORMAL)
    public void onPotentialKeepAlive(Packet01KeepAlive packet) {
        if(this.lastReceivedPacket == null) {
            this.lastReceivedPacket = packet;
        }
    }
}
