package com.ttaylorr.dev.humanity.server.cards;

import com.google.common.base.Joiner;

import java.util.LinkedList;

public class BlackCard implements HumanityCard {

    public static final String BLANK_SEPARATOR = "_______";

    /**
     * Represents all the messages for a black card.  Blank spots are marked as null.
     * For example "Maybe she's born with it, maybe it's __" is represented as
     * {"Maybe she's born with it...", null}.
     *
     * @see pickCards
     */
    private final LinkedList<String> messages;
    private final int pickCards;
    private boolean playable = true;

    public BlackCard(LinkedList<String> messages) {
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
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    @Override
    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public boolean play() {
        if(!this.playable) {
            return false;
        } else {
            this.playable = false;
            return false;
        }
    }

    @Override
    public String getText() {
        Joiner joiner = Joiner.on(' ');
        joiner.useForNull(BlackCard.BLANK_SEPARATOR).join(this.messages.iterator());

        return joiner.toString();
    }
}
