package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.CardCzarRequirement;
import net.humanity_game.server.packets.core.Packet11CzarUpdate;

public class AssigningCzarState extends IGameState {
    private ImmutableList<GameState.Requirement> requirementsList;
    private HumanityGame game;

    public AssigningCzarState(HumanityGame game) {
        super(GameState.ASSIGNING_CZAR);
        this.game = Preconditions.checkNotNull(game, "game");
        requirementsList = ImmutableList.of((GameState.Requirement) new CardCzarRequirement(this, game));
    }

    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirementsList;
    }

    @Override
    public void start() {
        Packet11CzarUpdate updatePacket = new Packet11CzarUpdate(game.advanceCzar().getClientId());
        updatePacket.sendToAll(this.game.getServer().getClientManager());
    }


    @Override
    public String toString() {
        return this.getGameState().toString();
    }

}
