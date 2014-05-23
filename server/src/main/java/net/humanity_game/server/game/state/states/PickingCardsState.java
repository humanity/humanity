package net.humanity_game.server.game.state.states;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.server.game.HumanityGame;
import net.humanity_game.server.game.state.GameState;
import net.humanity_game.server.game.state.requirements.PickedCardsRequirement;
import net.humanity_game.server.packets.core.Packet12OpenHand;

/**
 * the czar has sent back the picked-card packet, and the card has been verified as valid
 */
public class PickingCardsState extends IGameState {

    private HumanityGame game;
    private ImmutableList<GameState.Requirement> requirements;

    public PickingCardsState(HumanityGame game) {
        super(GameState.PICKING_CARDS);
        this.game = Preconditions.checkNotNull(game, "game");
        requirements = ImmutableList.of((GameState.Requirement) new PickedCardsRequirement(this, game));
    }


    @Override
    public ImmutableList<GameState.Requirement> getRequirements() {
        return requirements;
    }

    @Override
    public void start() {
        Packet12OpenHand outpacket = new Packet12OpenHand(game.getBlackCardDeck().getCurrentCard(), game.getCurrentCzar().getClientId());
        outpacket.sendToAll(game.getServer().getClientManager());
    }
}
