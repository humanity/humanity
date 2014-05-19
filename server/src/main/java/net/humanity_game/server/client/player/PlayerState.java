package net.humanity_game.server.client.player;

import java.io.Serializable;

public enum PlayerState implements Serializable {

    CARD_CZAR,

    PLAYING,

    OBSERVING;

    public boolean canSubmitCards() {
        return this == PLAYING;
    }
}
