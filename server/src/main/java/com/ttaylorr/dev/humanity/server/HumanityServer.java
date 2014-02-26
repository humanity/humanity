package com.ttaylorr.dev.humanity.server;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.handlers.PacketHandler;
import com.ttaylorr.dev.humanity.server.listeners.HandshakeListener;
import com.ttaylorr.dev.humanity.server.packets.core.Packet03Disconnect;
import com.ttaylorr.dev.humanity.server.queue.core.InboundPacketQueue;
import com.ttaylorr.dev.humanity.server.queue.core.OutboundPacketQueue;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class HumanityServer {

    private PacketHandler packetHandler;

    private List<ClientConnection> connectedClients;
    private OutboundPacketQueue outboundPacketQueue;
    private InboundPacketQueue inboundPacketQueue;

    private ConnectionListener connectionListener;

    private ServerSocket serverSocket;
    private boolean open;

    private int port;

    public HumanityServer(int port) {
        Preconditions.checkArgument(port > 0, "port must be greater than 0");
        this.port = port;
        this.packetHandler = new PacketHandler(this);
    }

    public void open() {
        System.out.println("Opening server...");
        try {
            this.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.teardown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.open = true;

        this.connectedClients = new ArrayList<>();
        this.outboundPacketQueue = new OutboundPacketQueue();
        this.inboundPacketQueue = new InboundPacketQueue();

        this.registerHandlers();

        this.connectionListener = new ConnectionListener(this);
        Thread thread = new Thread(connectionListener);
        thread.setName("Connection-Listener");
        thread.start();
    }

    private void registerHandlers() {
        this.packetHandler.registerHandlers(new HandshakeListener());
    }

    private void teardown() throws IOException {
        this.open = false;
        this.disconnectAll();
        this.serverSocket.close();
    }

    public ServerSocket getSocket() {
        return this.serverSocket;
    }

    private void disconnectAll() {
        this.outboundPacketQueue.addPacket(new Packet03Disconnect(), this.getConnectedClients());
    }

    public ImmutableList<ClientConnection> getConnectedClients() {
        return ImmutableList.copyOf(this.connectedClients);
    }

    public void connectClient(ClientConnection client) {
        this.connectedClients.add(client);
    }

    public boolean isOpen() {
        return this.open;
    }
}
