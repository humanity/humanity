package com.ttaylorr.dev.humanity.server.client.player;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.util.UUID;

public abstract class MaskedClientConnection {

    private final UUID clientId;

    public MaskedClientConnection(ClientConnection realClient) {
        this(realClient.getClientId());
    }

    protected MaskedClientConnection() {
        this(UUID.randomUUID());
    }

    private MaskedClientConnection(UUID id) {
        this.clientId = id;
    }

    public final UUID getClientId() {
        return this.clientId;
    }

    @Override
    public final boolean equals(Object o) {
        if (o instanceof MaskedClientConnection) {
            MaskedClientConnection conn = (MaskedClientConnection) o;
            return conn.getClientId().equals(this.clientId);
        } else {
            return false;
        }
    }
}
