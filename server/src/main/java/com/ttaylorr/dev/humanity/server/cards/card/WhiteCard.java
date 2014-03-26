package com.ttaylorr.dev.humanity.server.cards.card;

import com.google.common.base.Preconditions;

public class WhiteCard extends HumanityCard {

    protected final String message;

    public WhiteCard(String message, Expansion expansion) {
        super(expansion);
        this.message = Preconditions.checkNotNull(message, "message");
    }
    @Override

    public String getText() {
        return this.message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WhiteCard{");
        builder.append("message='" + this.getText() + "'");
        builder.append(", expansion='" + this.getExpansion().name() + "'");
        builder.append(", state='" + this.getState().name() + "'");
        builder.append("}");
        return builder.toString();
    }
}
