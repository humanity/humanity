package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;

/**
 * Contains the information about this Client's status in the game, rather than in the server's broader system.
 */
public class ServerPlayerDefinition implements IPlayerDefinition {

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
        this.score = nscore;
    }

    @Override
    public PlayerState getPlayerState() {
        return null;
    }

    //todo add player state.
}
