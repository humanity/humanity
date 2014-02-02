package com.ttaylorr.dev.humanity.server.packets.core;

import com.ttaylorr.dev.humanity.server.packets.Packet;

public class Packet03ClientHandshake extends Packet {

	private String playerName;

	public Packet03ClientHandshake(String playerName) {
		this.playerName = playerName;
	}

}
