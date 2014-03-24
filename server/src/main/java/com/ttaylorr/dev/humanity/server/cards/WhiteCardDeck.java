package com.ttaylorr.dev.humanity.server.cards;

import com.ttaylorr.dev.humanity.server.client.ClientConnection;

import java.util.*;

public class WhiteCardDeck extends HumanityDeck<WhiteCard> {

    public WhiteCardDeck(Set<WhiteCard> cards) {
        super(cards);
    }

    public void dealCards(ClientConnection client) {
        HumanityHand hand = client.getDefinition().getPlayerHand();

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
