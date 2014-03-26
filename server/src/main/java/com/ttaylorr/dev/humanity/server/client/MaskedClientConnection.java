package com.ttaylorr.dev.humanity.server.client;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.io.Serializable;
import java.util.UUID;

public class MaskedClientConnection implements Serializable {

    private final UUID clientId;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
