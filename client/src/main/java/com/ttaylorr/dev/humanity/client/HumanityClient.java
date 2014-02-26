package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.Packet02Handshake;

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

                this.inputStream = new ObjectInputStream(this.serverConnection.getInputStream());
                this.outputStream = new ObjectOutputStream(this.serverConnection.getOutputStream());

                this.sendPacket(new Packet02Handshake("Fred"));

                break;
            } catch (ConnectException e) {
                // keep going!
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean sendPacket(Packet packet) {
        try {
            this.outputStream.writeObject(packet);
            this.outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
