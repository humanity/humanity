package com.ttaylorr.dev.humanity.server.cards;

public interface HumanityCard {

    /** Sets whether the card is able to be played.  If play() is called and this
     *  is false, the card will not be able to be played.
     */
    public void setPlayable(boolean playable);

    /** Gets the playable state of the card */
    public boolean isPlayable();

    /** Preforms the actual play operation.  Usually includes sending some packets. */
    public boolean play();

    /** Gets the user-readable text of the card */
    public String getText();

}
