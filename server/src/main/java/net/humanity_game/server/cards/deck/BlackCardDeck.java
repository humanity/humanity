package net.humanity_game.server.cards.deck;

import net.humanity_game.server.cards.card.BlackCard;

import java.util.Set;

public class BlackCardDeck extends HumanityDeck<BlackCard> {

    private BlackCard currentCard;

    public BlackCardDeck(Set<BlackCard> cards) {
        super(cards);
    }

    public BlackCard getCurrentCard() {
        return currentCard;
    }
}
