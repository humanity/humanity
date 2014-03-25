package com.ttaylorr.dev.humanity.server.cards.deck;

import com.ttaylorr.dev.humanity.server.cards.hand.ServerHumanityHand;
import com.ttaylorr.dev.humanity.server.cards.hand.IHumanityHand;
import com.ttaylorr.dev.humanity.server.cards.card.WhiteCard;
import com.ttaylorr.dev.humanity.server.client.ClientConnection;

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
