package com.ttaylorr.dev.humanity.server.cards.card;

import java.util.LinkedList;

public class BlackCard extends HumanityCard {

    public static final String BLANK_SEPARATOR = "__";

    /**
     * Represents all the messages for a black card.  Blank spots are marked as null.
     * For example "Maybe she's born with it, maybe it's __" is represented as
     * {"Maybe she's born with it, maybe it's ", null}.
     */
    private final LinkedList<String> messages;
    private final int pickCards;

    public BlackCard(LinkedList<String> messages, Expansion expansion) {
        super(expansion);
        this.messages = messages;

        int blank = 0;
        for (String message : this.messages) {
            if (message == null) {
                blank++;
            }
        }

        this.pickCards = blank;
    }

    @Override
    public boolean play() {
        if(!this.state.isPlayable()) {
            return false;
        } else {
            this.state = this.state.advance();
            return false;
        }
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        for(String message : this.messages) {
            if(message == null) {
                builder.append(BlackCard.BLANK_SEPARATOR);
            } else {
                builder.append(message);
            }
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BlackCard{");
        builder.append("message='" + this.getText() + "'");
        builder.append(", state='" + this.state.name() + "'");
        builder.append(", expansion='" + this.expansion.name() + "'");
        builder.append(", pickCards='" + this.pickCards + "'");
        builder.append("}");
        return builder.toString();
    }

    public int getNumberToFill() {
        return this.pickCards;
    }
}
