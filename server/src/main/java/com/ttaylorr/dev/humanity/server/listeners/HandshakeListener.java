package com.ttaylorr.dev.humanity.server.listeners;

import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet02Handshake;

public class HandshakeListener implements Listenable {

    @Handler(priority = HandlerPriority.NORMAL)
    public void onClientHandshake(Packet02Handshake handshake) {
        System.out.println("we got a handshake from " + handshake.getName());
    }
}
