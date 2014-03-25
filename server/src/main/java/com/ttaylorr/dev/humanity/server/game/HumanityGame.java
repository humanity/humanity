package com.ttaylorr.dev.humanity.server.game;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.ttaylorr.dev.humanity.server.HumanityServer;
import com.ttaylorr.dev.humanity.server.cards.deck.BlackCardDeck;
import com.ttaylorr.dev.humanity.server.cards.deck.WhiteCardDeck;
import com.ttaylorr.dev.humanity.server.cards.factory.BlackCardFactory;
import com.ttaylorr.dev.humanity.server.cards.factory.WhiteCardFactory;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;
import com.ttaylorr.dev.humanity.server.client.player.PlayerState;
import com.ttaylorr.dev.humanity.server.game.state.GameState;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.core.Packet08GameChangeState;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class HumanityGame {

    private final HumanityServer server;

    private final WhiteCardDeck whiteCardDeck;
    private final BlackCardDeck blackCardDeck;

    private final Set<ClientConnection> players;

    private GameState currentState;

    public HumanityGame(File cardsFile, HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");

        this.whiteCardDeck = new WhiteCardFactory(cardsFile, this.server).parse().build();
        this.blackCardDeck = new BlackCardFactory(cardsFile, this.server).parse().build();
        this.players = new HashSet<>();
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
