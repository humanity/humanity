package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.server.packets.Handler;
import com.ttaylorr.dev.humanity.server.packets.HandlerPriority;
import com.ttaylorr.dev.humanity.server.packets.Listener;

public class TestPacketHandler implements Listener {
	
	@Handler(priority = HandlerPriority.MONITOR)
	public void onPacket(Packet01Test packet) {
		System.out.println("oh damn!");
	}
}
