package com.ttaylorr.dev.humanity.server.client.state;

import java.io.Serializable;

public enum PlayerState implements Serializable {

    CARD_CSAR(false),

    PLAYING(true),

    OBSERVING(false);

    private final boolean canSubmitCards;

    private PlayerState(boolean canSubmitCards) {
        this.canSubmitCards = canSubmitCards;
    }

    public boolean canSubmitCards() {
        return this.canSubmitCards;
    }
}
