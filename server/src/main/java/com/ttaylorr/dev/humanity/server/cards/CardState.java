package com.ttaylorr.dev.humanity.server.cards;

public enum CardState {

    DRAWABLE(true),

    IN_HAND(true),

    IN_PLAY(false),

    DISCARDED(false);

    private final boolean playable;

    // TODO: Make this configurable
    public static final boolean PAST_PLAY = false;

    private CardState(boolean playable) {
        this.playable = playable;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    public CardState advance() {
        for(int i = 0; i < this.values().length; i++) {
            if(this.values()[i] == this) {
                try {
                    return this.values()[i++];
                } catch(ArrayIndexOutOfBoundsException e) {
                    if(CardState.PAST_PLAY) {
                        return values()[0];
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
