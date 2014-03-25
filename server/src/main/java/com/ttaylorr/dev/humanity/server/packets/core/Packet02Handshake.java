package com.ttaylorr.dev.humanity.server.packets.core;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet02Handshake extends Packet {

    private String name;

    public Packet02Handshake(String name) {
        this.name = Preconditions.checkNotNull(name, "name");
    }

    public String getName() {
        return this.name;
    }

}
