package com.ttaylorr.dev.humanity.server.game;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.ttaylorr.dev.humanity.server.Bootstrap;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.card.BlackCard;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.deck.BlackCardDeck;
import com.ttaylorr.dev.humanity.server.cards.deck.WhiteCardDeck;
import com.ttaylorr.dev.humanity.server.cards.factory.BlackCardFactory;
import com.ttaylorr.dev.humanity.server.cards.factory.WhiteCardFactory;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.game.state.GameState;
import com.ttaylorr.dev.humanity.server.packets.core.Packet08GameChangeState;
import com.ttaylorr.dev.humanity.server.packets.masked.core.Packet11MaskedDisconnect;

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
