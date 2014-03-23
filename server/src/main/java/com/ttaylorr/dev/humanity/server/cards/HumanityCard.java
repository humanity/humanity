package com.ttaylorr.dev.humanity.server.cards;

import java.io.Serializable;

public abstract class HumanityCard implements Serializable {

    protected CardState state;

    public HumanityCard() {
        this.state = CardState.DRAWABLE;
    }

    public CardState advanceState() {
        return (this.state = this.state.advance());
    }

    public CardState getState() {
        return this.state;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public boolean isPlayable() {
        return this.state.isPlayable();
    }

    /** Preforms the actual play operation. For this operation to be valid, isPlayable() must be false. Usually includes sending some packets. */
    public abstract boolean play();

    /** Gets the user-readable text of the card */
    public abstract String getText();

}
