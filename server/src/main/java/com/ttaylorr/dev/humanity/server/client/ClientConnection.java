package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.definition.ServerClientDefinition;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    private ServerClientDefinition definition;
    private Logger logger;

    private HumanityServer server;

    public ClientConnection(Socket connection, HumanityServer server) {
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.connection = Preconditions.checkNotNull(connection);
        this.server = Preconditions.checkNotNull(server, "server");
        try {
            this.output = new ObjectOutputStream(connection.getOutputStream());
            this.input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.definition = new ServerClientDefinition(this, this.server);
    }

    public Socket getConnection() {
        return this.connection;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public ServerClientDefinition getDefinition() {
        return this.definition;
    }

    public void sendPacket(Packet packet) {
        this.server.getOutboundPackets().sendPacket(packet, this);
    }

    public boolean _sendPacket(Packet packet) {
        try {
            this.logger.debug("[INTERNAL] (S->C) sent: {}", packet.getClass().getSimpleName());
            this.output.writeObject(packet);
            this.output.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
