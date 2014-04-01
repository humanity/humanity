package com.ttaylorr.dev.humanity.server.packets;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.MaskedClientConnection;

import java.io.Serializable;

public abstract class Packet implements Serializable {

    private MaskedClientConnection maskedClientConnection;

    public Packet(ClientConnection clientConnection) {
        maskedClientConnection = clientConnection.getMaskedVersion();
    }

    public Packet(MaskedClientConnection clientConnection) {
        maskedClientConnection = clientConnection;
    }




}
