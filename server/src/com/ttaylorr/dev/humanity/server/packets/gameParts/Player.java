package com.ttaylorr.dev.humanity.server.packets.gameParts;

import java.util.List;

import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Player extends Packet {

	private String name;
	private List<Card> hand;
	private boolean isCardCsar;

	/**
	 * Submit the cards that the player would like to play this round.
	 * 
	 * 
	 * 
	 * @param cards
	 *            the card(s) the player wants to submit. This method generates the CardSelectionPacket that will be sent to the server.
	 *           
	 */
	void submitCards(List<Card> cards) {
	}

}
