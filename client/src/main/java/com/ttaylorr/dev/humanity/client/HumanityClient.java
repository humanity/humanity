package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.Packet02Handshake;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

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

    private Logger logger;

    public HumanityClient(String hostname, int port) {
        this(new InetSocketAddress(hostname, port));
    }

    public HumanityClient(InetSocketAddress address) {
        this.address = address;
        this.logger = LoggerProvider.putLogger(this.getClass());
    }

    public void openConnection() {
        this.logger.info("Attempting to open a connection...");
        if (this.serverConnection != null) {
            this.logger.severe("Already connected to a server!");
            return;
        }

        while (this.serverConnection == null) {
            try {
                this.serverConnection = new Socket(this.address.getHostName(), this.address.getPort());

                this.inputStream = new ObjectInputStream(this.serverConnection.getInputStream());
                this.outputStream = new ObjectOutputStream(this.serverConnection.getOutputStream());

                if (this.serverConnection.isConnected()) {
                    this.logger.info("Successfully connected to server at{}", this.address);
                }

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
            this.logger.debug("Sending {} packet", packet.getClass().getSimpleName());
            this.outputStream.writeObject(packet);
            this.outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
