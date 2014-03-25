package com.ttaylorr.dev.humanity.client.client;

import com.google.common.base.Preconditions;

import java.util.UUID;

public class MaskedHumanityClient {

    private final UUID clientId;

    public MaskedHumanityClient(UUID clientId) {
        this.clientId = Preconditions.checkNotNull(clientId);
    }

    public final UUID getClientId() {
        return this.clientId;
    }

}
