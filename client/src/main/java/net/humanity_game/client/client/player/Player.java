package net.humanity_game.client.client.player;

import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.client.player.definition.IPlayerDefinition;

import java.util.UUID;

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

    /**
     * Initially, the player state should be null--it will be set later by the Packet05PlayerStateChange.
     * @param uuid
     * @param name
     */
    public Player(UUID uuid, String name) {
        this(uuid, name, null);
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
