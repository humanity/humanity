package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {

    private Socket connection;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    public ClientConnection(Socket connection) {
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

    public boolean sendObject(Object obj) {
        try {
            this.output.writeObject(obj);
            this.output.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
