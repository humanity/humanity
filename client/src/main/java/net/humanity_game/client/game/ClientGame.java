package net.humanity_game.client.game;

import com.google.common.base.Preconditions;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.cards.ClientTrick;
import net.humanity_game.client.client.MaskedHumanityClient;
import net.humanity_game.client.listeners.OtherJoinListener;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet07CreatePool;
import net.humanity_game.server.packets.core.Packet08GameChangeState;
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

    public void connectPlayer(MaskedHumanityClient client) {
        this.connectedPlayers.add(client);
    }

    public void handleLogout(MaskedHumanityClient client) {
        this.connectedPlayers.remove(client);
    }
}
