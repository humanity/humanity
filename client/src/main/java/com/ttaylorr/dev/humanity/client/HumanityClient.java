package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.client.listeners.JoinVerificationListener;
import com.ttaylorr.dev.humanity.client.tasks.KeepAliveTask;
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
import java.util.concurrent.*;

public class HumanityClient {

    private final InetSocketAddress address;
    private final Logger logger;
    private final ClientPacketHandler packetHandler;

    private Socket serverConnection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private IncomingPacketListener packetListener;

    private ScheduledFuture<?> keepAliveWaitable;

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
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // got the connection, start the keep-alive polling task
        this.initKeepAlive();
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
            this.getLogger().severe("The server unexpectedly closed, disconnecting!");

            System.exit(-1);
            return false;
        }
        return true;
    }

    private void initKeepAlive() {
        if(this.keepAliveWaitable != null) {
            this.keepAliveWaitable.cancel(true);
        }

        long DELAY = 2l;
        new Thread(new Runnable() {
            HumanityClient client = HumanityClient.this;

            @Override
            public void run() {
                int missedSinceSuccess = 0;

                while(true) {
                    try {
                        // Wait 5 seconds
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(missedSinceSuccess > 3) {
                        Bootstrap.requestClose();
                    }

                    KeepAliveTask currentTask = new KeepAliveTask(HumanityClient.this);
                    client.keepAliveWaitable = Bootstrap.threadPoolExecutor.schedule(currentTask, 0l, TimeUnit.SECONDS);

                    try {
                        // We have until the advance cycle to get a response
                        boolean result = (Boolean) client.keepAliveWaitable.get(5l, TimeUnit.SECONDS);
                        if (!result) {
                            client.getLogger().severe("Got a response, cannot find the server! :(");
                            missedSinceSuccess++;
                        } else {
                            client.getLogger().debug("Found the server, will try again in 5 seconds...");
                            missedSinceSuccess = 0;
                        }
                    } catch(TimeoutException e1) {
                        client.keepAliveWaitable.cancel(true);
                        client.getPacketHandler().unregisterHandlers(currentTask);
                        client.getLogger().severe("Timed out, cannot find the server!");
                        missedSinceSuccess++;
                        e1.printStackTrace();
                    } catch (InterruptedException | ExecutionException e1) {
                        client.keepAliveWaitable.cancel(true);
                        missedSinceSuccess++;
                        e1.printStackTrace();
                    }
                }
            }
        }).start();
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
