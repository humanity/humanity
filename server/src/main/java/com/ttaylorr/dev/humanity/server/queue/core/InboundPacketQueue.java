package com.ttaylorr.dev.humanity.server.queue.core;

import com.ttaylorr.dev.humanity.server.packets.PacketSnapshot;
import com.ttaylorr.dev.humanity.server.queue.Queuable;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class InboundPacketQueue implements Queuable {

    private ConcurrentLinkedDeque<PacketSnapshot> incomming;

    @Override
    public Queue getQueue() {
        return this.incomming;
    }

    @Override
    public void handleFirst() {
        incomming.pop();
    }
}
