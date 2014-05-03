package net.humanity_game.client.cards;

import com.google.common.base.Preconditions;
import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.cards.hand.IHumanityHand;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet06HandUpdate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientHumanityHand implements IHumanityHand, Listenable, Serializable {

    private final HumanityClient owner;
    private List<WhiteCard> cards;

    public ClientHumanityHand(HumanityClient owner) {
        this(owner, new ArrayList<WhiteCard>(IHumanityHand.MAX_HAND_SIZE));
    }

    public ClientHumanityHand(HumanityClient owner, ArrayList<WhiteCard> cards) {
        this.cards = new ArrayList<>();
        this.owner = Preconditions.checkNotNull(owner, "owner");
        this.owner.getPacketHandler().registerHandlers(this);
    }

    @ClientHandler(
        priority = HandlerPriority.MONITOR,
        handleSelf = true
    )
    public void onHandUpdate(Packet06HandUpdate packet) {
        this.cards.removeAll(packet.getRemovedCards());
        this.cards.addAll(packet.getDrawnCards());
        System.out.println(this.getCards());
    }

    @Override
    public boolean releaseCard(WhiteCard card) {
        return this.cards.remove(card);
    }

    @Override
    public boolean shouldDraw() {
        return this.cards.size() != IHumanityHand.MAX_HAND_SIZE;
    }

    @Override
    public List<WhiteCard> getCards() {
        return this.cards;
    }
}
