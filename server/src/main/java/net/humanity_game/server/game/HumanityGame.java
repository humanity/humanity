package net.humanity_game.server.game;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import net.humanity_game.server.Bootstrap;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.cards.deck.BlackCardDeck;
import net.humanity_game.server.cards.deck.WhiteCardDeck;
import net.humanity_game.server.cards.factory.BlackCardFactory;
import net.humanity_game.server.cards.factory.WhiteCardFactory;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.packets.core.Packet08GameChangeState;
import net.humanity_game.server.packets.masked.core.Packet11MaskedDisconnect;

import java.io.File;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class HumanityGame {

    private final HumanityServer server;

    private WhiteCardDeck whiteCardDeck;
    private BlackCardDeck blackCardDeck;

    private final Set<ClientConnection> players; // synchronized

    private GameState currentState;

    public HumanityGame(File cardsFile, HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");

        try {
            this.whiteCardDeck = new WhiteCardFactory(cardsFile, this.server).parse().build();
            this.blackCardDeck = new BlackCardFactory(cardsFile, this.server).parse().build();
        } catch (FileNotFoundException e) {
            System.err.println("Could not find requested deck file... " + e.getMessage());
            Bootstrap.requestClose();
        }

        this.players = Collections.synchronizedSet(new HashSet<ClientConnection>());
        this.currentState = GameState.LOBBY;
    }

    public ImmutableSet<ClientConnection> getPlayers() {
        return ImmutableSet.copyOf(this.players);
    }

    public ImmutableSet<ClientConnection> getPlayers(PlayerState type) {
        Set<ClientConnection> players = new HashSet<>();

        for(ClientConnection client : this.players) {
            if(client.getDefinition().getPlayerState() == type) {
                players.add(client);
            }
        }

        return ImmutableSet.copyOf(players);
    }

    public WhiteCardDeck getWhiteCardDeck() {
        return this.whiteCardDeck;
    }

    public BlackCardDeck getBlackCardDeck() {
        return this.blackCardDeck;
    }

    public void handleLogin(ClientConnection connecting) {
        synchronized (this.players) {
            this.players.add(connecting);
        }
    }

    public void disconnectPlayer(ClientConnection disconnecting) {
        synchronized (this.players) {
            this.players.remove(disconnecting);
        }

        for (ClientConnection client : this.players) {
            client.sendPacket(new Packet11MaskedDisconnect(disconnecting.getClientId()));
        }
    }

    public GameState getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(GameState state) {
        Packet08GameChangeState packet = new Packet08GameChangeState(this.currentState, state);
        this.currentState = state;

        for(ClientConnection client : this.players) {
            client.sendPacket(packet);
        }
    }
}
