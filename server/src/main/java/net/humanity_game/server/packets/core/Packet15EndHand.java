package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.client.ServerClientManager;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.requirements.AlertedPlayersRequirement;
import net.humanity_game.server.game.state.requirements.AlertedScoresRequirement;
import net.humanity_game.server.game.state.states.CompletedHandState;
import net.humanity_game.server.packets.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User: Jack Date: 5/23/2014 Time: 4:27 PM
 */
public class Packet15EndHand extends Packet {

    private final List<ScorePair> scores;
    private transient CompletedHandState state; // not to be sent over the network.

    public Packet15EndHand(HumanityGame game) {
        super(null);
        scores = new ArrayList<ScorePair>(game.getServer().getClientManager().getConnectedClients().size());
    }

    public Packet15EndHand(HumanityGame game, CompletedHandState state) {
        this(game);
        this.state = state; // can be null
    }


    public void add(ClientConnection cnn) {
        scores.add(new ScorePair(cnn.getClientId(), cnn.getDefinition().getScore()));

        if (state != null) {
            AlertedPlayersRequirement pr = state.getPlayersRequirement();
            AlertedScoresRequirement sr = state.getScoresRequirement();
            pr.confirmAlert(cnn);
            sr.confirmAlert(cnn);
        }
    }

    public void add(ServerClientManager manager) {
        ImmutableList<ClientConnection> clients = manager.getConnectedClients();
        for (ClientConnection connection : clients) {
            this.add(connection);
        }
    }

    /**
     * This list to to be treated as an update list, not as an authoritative list. If a player is not included here,
     * then continue using the last known score.
     *
     * @return
     */
    public List<ScorePair> getScores() {
        return scores;
    }

    class ScorePair {
        public final UUID id;
        public final int newScore;

        public ScorePair(UUID uuid, int score) {
            this.id = Preconditions.checkNotNull(uuid, "uuid");
            Preconditions.checkState(score >= 0, "score");
            this.newScore = score;
        }
    }
}
