package com.ttaylorr.dev.humanity.server.client.player;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.io.Serializable;
import java.util.UUID;

public class MaskedClientConnection implements Serializable {

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
}
