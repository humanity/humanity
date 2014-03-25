package com.ttaylorr.dev.humanity.client.game;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.client.client.HumanityClient;
import com.ttaylorr.dev.humanity.client.cards.ClientTrick;
import com.ttaylorr.dev.humanity.client.client.MaskedHumanityClient;
import com.ttaylorr.dev.humanity.client.listeners.OtherJoinListener;
import com.ttaylorr.dev.humanity.server.game.state.GameState;
import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listenable;
import com.ttaylorr.dev.humanity.server.packets.core.Packet07CreatePool;
import com.ttaylorr.dev.humanity.server.packets.core.Packet08GameChangeState;
import com.ttaylorr.dev.logger.Logger;

import java.util.HashSet;
import java.util.Set;

public class ClientGame implements Listenable {

    private HumanityClient client;

    private ClientTrick currentPool;
    private GameState currentState;
    private Set<MaskedHumanityClient> connectedPlayers;

    public ClientGame(HumanityClient client) {
        this.client = Preconditions.checkNotNull(client, "client");
        this.connectedPlayers = new HashSet<>();

        this.setupHandlers();
    }

    private void setupHandlers() {
        this.registerHandlers(this);
        this.registerHandlers(new OtherJoinListener(this));
    }

    public Logger getLogger() {
        return this.client.getLogger();
    }

    public void registerHandlers(Listenable listenable) {
        this.client.getPacketHandler().registerHandlers(listenable);
    }

    public void unregisterHandlers(Listenable listenable) {
        this.client.getPacketHandler().unregisterHandlers(listenable);
    }

    public ClientTrick getCurrentPool() {
        return this.currentPool;
    }

    public GameState getCurrentState() {
        return this.currentState;
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onPoolCreate(Packet07CreatePool packet) {
        this.currentPool = new ClientTrick(packet.getGameId(), packet.getChoice(), this);
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onGameStateChange(Packet08GameChangeState packet) {
        this.currentState = packet.getTo();
    }
}
