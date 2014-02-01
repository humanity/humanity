package com.ttaylorr.dev.humanity.client;

import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.HandlerPriority;
import com.ttaylorr.dev.humanity.server.handlers.Listener;

public class TestPacketHandler implements Listener {
	
	@Handler(priority = HandlerPriority.MONITOR)
	public void onPacket(Packet01Test packet) {
		System.out.println("oh damn!");
	}
}
