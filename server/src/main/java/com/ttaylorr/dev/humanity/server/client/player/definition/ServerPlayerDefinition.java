package com.ttaylorr.dev.humanity.server.client.player.definition;

import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.cards.pool.ITrick;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;

public class ServerPlayerDefinition implements IPlayerDefinition{

    private int score;
    private String name;
    private final ClientConnection client;

    public ServerPlayerDefinition(String name, ClientConnection client) {
        this.name = name;
        this.client = client;
    }

    @Override
    public String getName() {
        return client.getName();
    }

    @Override
    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public void setScore(int nscore) {

    }


    @Override
    public void playCard(WhiteCard card, ITrick forTrick) {
        // TODO
    }

    @Override
    public void updateHand() {
        // TODO
    }
}
