package net.humanity_game.client.client.player;

import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.client.player.definition.IPlayerDefinition;

import java.util.UUID;

/**
 * User: Jack Date: 5/8/2014 Time: 8:15 AM
 */
public class Player implements IPlayerDefinition {
    private final UUID clientId;
    private String name;
    private PlayerState state;
    private int score;


    public Player(UUID uuid, String name, PlayerState state) {
        this.clientId = uuid;
        this.name = name;
        this.state = state;
        score = 0;
    }

    public UUID getClientId() {
        return this.clientId;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int getScore() {
        return score;
    }

    /**
     * Only for use by handlers that get info about players' scores.
     *
     * @param scr
     */
    public void setScore(int scr) {
        this.score = scr;
    }


    @Override
    public PlayerState getPlayerState() {
        return state;
    }

    /**
     * Only for use by handlers that get info about players' states.
     * @param nst
     */
    public void setPlayerState(PlayerState nst) {
        this.state = nst;
    }
}
