package com.ttaylorr.dev.humanity.server.queue;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import com.ttaylorr.dev.humanity.server.Client;
import com.ttaylorr.dev.humanity.server.packets.Packet;

public class PacketQueueRunnable implements Runnable {

	private LinkedList<Map.Entry<Client, Packet>> packets;

	public PacketQueueRunnable() {
		packets = (LinkedList<Entry<Client, Packet>>) Collections.synchronizedList(new LinkedList<Map.Entry<Client, Packet>>());
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread().sleep(1000 / 30); // TODO make configurable.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (packets.isEmpty())
				continue;
		}
	}

	public synchronized void addPacket(Client forClient, Packet packet) {
		packets.add(new AbstractMap.SimpleEntry<Client, Packet>(forClient, packet));
	}

	public synchronized void addPackets(Iterable<Client> clients, Packet packet) {
		for (Client c : clients) {
			addPacket(c, packet);
		}
	}

}
