package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
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

    private Logger logger;

    public ClientConnection(Socket connection) {
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.connection = Preconditions.checkNotNull(connection);
        try {
            this.output = new ObjectOutputStream(connection.getOutputStream());
            this.input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public boolean sendPacket(Packet packet) {
        try {
            this.logger.debug("(S->C) sent: {}", packet.getClass().getSimpleName());
            this.output.writeObject(packet);
            this.output.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
