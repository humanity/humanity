package net.humanity_game.server.cards.deck;

import net.humanity_game.server.cards.hand.ServerHumanityHand;
import net.humanity_game.server.cards.hand.IHumanityHand;
import net.humanity_game.server.cards.card.WhiteCard;
import net.humanity_game.server.client.ClientConnection;

import java.util.*;

public class WhiteCardDeck extends HumanityDeck<WhiteCard> {

    public WhiteCardDeck(Set<WhiteCard> cards) {
        super(cards);
    }

    public void dealCards(ClientConnection client) {
        ServerHumanityHand hand = client.getDefinition().getPlayerHand();

        while(hand.getCards().size() < IHumanityHand.MAX_HAND_SIZE) {
            hand.addCard(this.drawCard());
        }

        client.getDefinition().updatePlayerHand(hand);
    }

    public synchronized void dealCards(ClientConnection... clients) {
        for (ClientConnection client : clients) {
            this.dealCards(client);
        }
    }
}
