package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;

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

    @Override
    public PlayerState getPlayerState() {
        return null;
    }
}
