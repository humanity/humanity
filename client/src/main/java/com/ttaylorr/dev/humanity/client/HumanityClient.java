package com.ttaylorr.dev.humanity.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HumanityClient {

    private InetSocketAddress address;

    private Socket serverConnection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public HumanityClient(String hostname, int port) {
        this(new InetSocketAddress(hostname, port));
    }

    public HumanityClient(InetSocketAddress address) {
        this.address = address;
    }

    public void openConnection() {
        if (this.serverConnection != null) {
            throw new IllegalArgumentException("already connected to a server");
        }

        while (this.serverConnection == null) {
            try {
                this.serverConnection = new Socket(this.address.getHostName(), this.address.getPort());
                this.serverConnection.connect(this.address);
                this.inputStream = new ObjectInputStream(this.serverConnection.getInputStream());
                this.outputStream = new ObjectOutputStream(this.serverConnection.getOutputStream());
                break;
            } catch (ConnectException e) {
                // keep going!
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
