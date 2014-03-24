package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.client.definition.ServerClientDefinition;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.queue.core.ClientDequeue;
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

    private ClientDequeue dequeue;
    private Thread dequeueThread;

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
        this.dequeue = new ClientDequeue(this, this.server);
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
        this.dequeue.sendPacket(packet);
    }

    public void openDequeue() {
        this.dequeueThread = new Thread(this.dequeue);
        this.dequeueThread.setName("PacketDequeue-" + this.server.getClientManager().getUUIDForClient(this));
        this.dequeueThread.start();
    }

    public void closeDequeue() {
        this.dequeueThread.stop();
    }

    public ClientDequeue getDequeue() {
        return this.dequeue;
    }
}
