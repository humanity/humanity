package net.humanity_game.server.client.player;

import java.io.Serializable;

public enum PlayerState implements Serializable {

    CARD_CSAR,

    PLAYING,

    OBSERVING;

    public boolean canSubmitCards() {
        switch (this) {
            case CARD_CSAR:
            case OBSERVING:
            default:
                return false;
            case PLAYING:
                return true;
        }
    }
}
