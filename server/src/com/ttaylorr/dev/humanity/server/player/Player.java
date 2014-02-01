package com.ttaylorr.dev.humanity.server.player;

import java.util.List;

import com.ttaylorr.dev.humanity.server.cards.core.Card;
import com.ttaylorr.dev.humanity.server.cards.hand.Hand;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Player extends Packet {

	private String name;
	private boolean isCardCsar;
	private final Hand hand;

	public Player(String name)
	{
		this.name = name;
		hand = new Hand();
	}
	
	/**
	 * Submit the cards that the player would like to play this round.
	 * 
	 * @param cards
	 *            the card(s) the player wants to submit. This method generates the CardSelectionPacket that will be sent to the server.
	 * 
	 */
	void submitCards(List<Card> cards) {
	}

}
