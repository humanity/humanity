package com.ttaylorr.dev.humanity.server.cards;

import com.google.common.base.Preconditions;

public class WhiteCard implements HumanityCard {

    protected final String message;
    protected boolean playable;

    public WhiteCard(String message) {
        this.message = Preconditions.checkNotNull(message, "message");
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
            return false;
        }
    }

    @Override
    public String getText() {
        return this.message;
    }
}
