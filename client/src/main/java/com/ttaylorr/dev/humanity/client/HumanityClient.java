package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.listeners.JoinVerificationListener;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.*;
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

    private ClientPacketHandler packetHandler;
    private IncomingPacketListener packetListener;

    public HumanityClient(String hostname, int port) {
        this(new InetSocketAddress(hostname, port));
    }

    public HumanityClient(InetSocketAddress address) {
        this.address = address;
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.packetHandler = new ClientPacketHandler(this);
    }

    public void openConnection() {
        this.setup();
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

                this.packetListener = new IncomingPacketListener(this);
                Thread thread = new Thread(packetListener);
                thread.setName("Packet-Listener");
                thread.start();

                if (this.serverConnection.isConnected()) {
                    this.logger.info("Successfully connected to server at {}", this.address);
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

    private void setup() {
        this.packetHandler.registerHandlers(new JoinVerificationListener(this));
    }

    public boolean sendPacket(Packet packet) {
        try {
            this.logger.debug("(C->S) sent: {}", packet.getClass().getSimpleName());
            this.outputStream.writeObject(packet);
            this.outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ClientPacketHandler getPacketHandler() {
        return this.packetHandler;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
