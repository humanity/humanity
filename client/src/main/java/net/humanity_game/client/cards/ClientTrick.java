package net.humanity_game.client.cards;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import net.humanity_game.client.client.player.OwnedPlayer;
import net.humanity_game.client.game.ClientGame;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.cards.card.BlackCard;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.pool.ITrick;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet13WhiteCardChoice;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClientTrick extends ITrick implements Listenable {

    private final BlackCard choiceCard;
    private final List<WhiteCard> submitted;

    private final ClientGame game;
    private final UUID trickID;

    public ClientTrick(UUID trickId, BlackCard choiceCard, ClientGame game) {
        this.game = Preconditions.checkNotNull(game, "game");
        this.trickID = trickId;

        this.choiceCard = Preconditions.checkNotNull(choiceCard, "card");
        this.submitted = new CopyOnWriteArrayList<>();

        this.game.registerHandlers(this);
    }

    @Override
    public BlackCard getChoiceCard() {
        return this.choiceCard;
    }

    @Override
    public List<WhiteCard> getSubmitted() {
        return this.submitted;
    }

    @Override
    public boolean isComplete() {
        return this.submitted.size() >= this.choiceCard.getNumberToFill();
    }

    public void submitCard(OwnedPlayer player, WhiteCard card) {
        this.submitCard(player, ImmutableList.<WhiteCard>builder().add(card).build());
    }

    public void submitCard(OwnedPlayer player, Collection<WhiteCard> cards) {
        this.submitted.addAll(cards);
        Packet13WhiteCardChoice choice = new Packet13WhiteCardChoice(this, cards);
        this.game.sendPacket(choice);
    }

    @ClientHandler ( handleAll = true )
    public void onCurrentTrickUpdate(Packet13WhiteCardChoice packet) {
        this.submitted.addAll(packet.getWhiteCards());
    }

    public UUID getTrickID() {
        return trickID;
    }
}
