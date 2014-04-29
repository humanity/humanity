package com.ttaylorr.dev.humanity.server.cards.card;

import com.google.gson.TypeAdapter;
import com.ttaylorr.dev.humanity.server.cards.card.state.CardState;

import java.io.Serializable;

public abstract class HumanityCard implements Serializable {

    protected CardState state;
    protected Expansion expansion;

    public HumanityCard(Expansion expansion) {
        this.state = CardState.DRAWABLE;
        this.expansion = expansion;
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

    public Expansion getExpansion() {
        return this.expansion;
    }

    public boolean isPlayable() {
        return this.state.isPlayable();
    }

    /** Preforms the actual play operation. Usually includes sending some packets. */
    public abstract boolean play();

    /** Gets the user-readable text of the card */
    public abstract String getText();

    public static enum Expansion {
        NSFH,
        EXPANSION_1,
        EXPANSION_2,
        BASE,
        WEEABOO,
        NEIndy,
        GROGNARDS,
        IMAGE_1,
        CHRISTMAS,
        EXPANSION_3;

        public static Expansion forString(String jsonName) {
            switch(jsonName) {
                case "NSFH":
                    return NSFH;
                case "CAHe1":
                    return EXPANSION_1;
                case "CAHe2":
                    return EXPANSION_2;
                case "Base":
                    return BASE;
                case "CAHweeaboo":
                    return WEEABOO;
                case "NEIndy":
                    return NEIndy;
                case "CAHgrognards":
                    return GROGNARDS;
                case "Image1":
                    return IMAGE_1;
                case "CAHxmas":
                    return CHRISTMAS;
                case "CAHe3":
                    return EXPANSION_3;
                default:
                    return BASE;
            }
        }
    }

}
