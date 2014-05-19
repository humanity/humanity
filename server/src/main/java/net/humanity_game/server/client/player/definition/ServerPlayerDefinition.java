package net.humanity_game.server.client.player.definition;

import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.player.PlayerState;
import net.humanity_game.server.packets.core.Packet10ScoreUpdate;

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
        this.setScore(this.getScore() + 1);
    }

    public void setScore(int nscore) {
        this.score = nscore;
        Packet10ScoreUpdate packet = new Packet10ScoreUpdate(this.client.getClientId(), this.score);
        packet.sendToAll(this.client.getServer().getClientManager());
    }

    @Override
    public PlayerState getPlayerState() {
        return null;
    }

    //todo add player state.
}
