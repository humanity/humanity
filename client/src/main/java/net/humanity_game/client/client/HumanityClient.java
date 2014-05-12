package net.humanity_game.client.client;

import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.definition.ClientDefinition;
import net.humanity_game.client.listeners.JoinVerificationListener;
import net.humanity_game.client.packets.ClientPacketHandler;
import net.humanity_game.client.packets.IncomingPacketListener;
import net.humanity_game.client.tasks.KeepAliveTask;
import net.humanity_game.server.packets.Packet;
import net.humanity_game.server.packets.core.Packet02Handshake;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class HumanityClient {

    private final UUID clientId;
    private String name;

    private final InetSocketAddress address;
    private final Logger logger;
    private final ClientPacketHandler packetHandler;

    private final ClientDefinition definition;

    private Socket serverConnection;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private IncomingPacketListener packetListener;

    private ScheduledFuture<?> keepAliveWaitable;

    public HumanityClient(UUID uuid, String hostname, int port) {
        this(uuid, new InetSocketAddress(hostname, port));
    }

    public HumanityClient(UUID clientId, InetSocketAddress address) {
        this.address = address;
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.packetHandler = new ClientPacketHandler(this);
        this.definition = new ClientDefinition(clientId, this);
        this.clientId = clientId;
    }

    /**
     * Before the client connects, the client won't know its UUID.
     * Should the client assign its own UUID or should the server assign the client one and then tell it?
     * @param hostname
     * @param port
     */
    public HumanityClient(String hostname, int port) {
        this(null, hostname, port);
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
                this.logger.info("Looking for a server to connect to...");
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

                this.sendPacket(new Packet02Handshake(this.clientId, "Fred"));

                break;
            } catch (ConnectException e) {
                // keep going!
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Bootstrap.LOOP_DELAY * 20);
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

        final long DELAY = 5l;
        final int SCALE = 2;
        final TimeUnit UNIT = TimeUnit.SECONDS;

        new Thread(new Runnable() {
            HumanityClient client = HumanityClient.this;

            @Override
            public void run() {
                int missedSinceSuccess = 0;

                while(true) {
                    try {
                        Thread.sleep(TimeUnit.MILLISECONDS.convert(10, TimeUnit.SECONDS));
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
                        boolean result = (Boolean) client.keepAliveWaitable.get(DELAY * SCALE, UNIT);
                        if (!result) {
                            client.getLogger().severe("Got a response, cannot find the server! :(");
                            missedSinceSuccess++;
                        } else {
                            client.getLogger().debug("Found the server, will try again...");
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

    public ClientDefinition getDefinition() {
        return this.definition;
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

    public String getName() {
        return this.name;
    }

    public UUID getClientId() {
        return this.definition.getUUID();
    }
}
