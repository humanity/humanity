package com.ttaylorr.dev.humanity.client.packets;

import com.ttaylorr.dev.humanity.client.ServerStream;

public class ClientPacketSender {

	private ServerStream server;

	public ClientPacketSender(ServerStream server) {
		this.server = server;
	}

}
