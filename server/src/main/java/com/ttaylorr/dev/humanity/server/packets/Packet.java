package com.ttaylorr.dev.humanity.server.packets;

import com.ttaylorr.dev.humanity.server.Client;

import java.io.Serializable;

public abstract class Packet implements Serializable {
    /**
     * If the owner was the server, then this is null.
     */
    final private Client owner;

    Packet(Client owner) {
        this.owner = owner;
    }

}
