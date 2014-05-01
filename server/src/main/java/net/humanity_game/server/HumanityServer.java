package net.humanity_game.server;

import com.google.common.base.Preconditions;
import net.humanity_game.server.client.ClientManager;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.listeners.KeepAliveListener;
import net.humanity_game.server.packets.PacketHandler;
import net.humanity_game.server.listeners.HandshakeListener;
import com.ttaylorr.dev.logger.Logger;
import com.ttaylorr.dev.logger.LoggerProvider;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;

public class HumanityServer {

    private PacketHandler packetHandler;

    private ClientManager clientManager;

    private ConnectionListener connectionListener;

    private ServerSocket serverSocket;
    private boolean open;

    private int port;

    private Logger logger;

    private HumanityGame game;

    public HumanityServer(int port) {
        Preconditions.checkArgument(port > 0, "port must be greater than 0");
        this.logger = LoggerProvider.putLogger(this.getClass());
        this.port = port;
        this.packetHandler = new PacketHandler(this);
        this.clientManager = new ClientManager(this);
    }

    public void open() {
        this.logger.info("Opening server \"humanity\"...");
        try {
            this.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        this.logger.info("Closing server \"humanity\"...");
        try {
            this.teardown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.open = true;

        this.clientManager.setup();

        this.registerHandlers();

        this.game = new HumanityGame(new File("./server/src/main/resources/cards-all.json"), this);

        this.connectionListener = new ConnectionListener(this);
        Thread thread = new Thread(connectionListener);
        thread.setName("Connection-Listener");
        thread.start();
    }

    private void registerHandlers() {
        this.packetHandler.registerHandlers(new HandshakeListener(this));
        this.packetHandler.registerHandlers(new KeepAliveListener(this));
    }

    private void teardown() throws IOException {
        this.open = false;
        this.clientManager.disconnectAll(this);
        this.serverSocket.close();
    }

    public ServerSocket getSocket() {
        return this.serverSocket;
    }

    public ClientManager getClientManager() {
        return this.clientManager;
    }

    public PacketHandler getPacketManager() {
        return this.packetHandler;
    }

    public HumanityGame getGame() {
        return this.game;
    }

    public boolean isOpen() {
        return this.open;
    }

    public Logger getLogger() {
        return this.logger;
    }
}
