package com.ttaylorr.dev.humanity.client.game;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.HumanityClient;
import com.ttaylorr.dev.humanity.client.cards.ClientPool;
import com.ttaylorr.dev.humanity.server.game.state.GameState;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet07CreatePool;
import com.ttaylorr.dev.humanity.server.packets.core.Packet08GameChangeState;

public class ClientGame implements Listenable {

    private HumanityClient client;

    private ClientPool currentPool;
    private GameState currentState;

    public ClientGame(HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.registerHandlers(this);
    }

    public void registerHandlers(Listenable listenable) {
        this.client.getPacketHandler().registerHandlers(listenable);
    }

    public void unregisterHandlers(Listenable listenable) {
        this.client.getPacketHandler().unregisterHandlers(listenable);
    }

    public ClientPool getCurrentPool() {
        return this.currentPool;
    }

    public GameState getCurrentState() {
        return this.currentState;
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onPoolCreate(Packet07CreatePool packet) {
        this.currentPool = new ClientPool(packet.getGameId(), packet.getChoice(), this);
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onGameStateChange(Packet08GameChangeState packet) {
        this.currentState = packet.getTo();
    }
}
