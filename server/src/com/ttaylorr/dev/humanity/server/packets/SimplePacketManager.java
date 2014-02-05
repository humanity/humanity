package com.ttaylorr.dev.humanity.server.packets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.SynchronousQueue;

import com.ttaylorr.dev.humanity.server.handlers.Listener;

public class SimplePacketManager {

	final private Map<Class<? extends Packet>, Map<Listener, Method>> packets;
	private final SynchronousQueue<Packet> packetQueue;

	public SimplePacketManager() {
		this.packets = new HashMap<>();
		this.packetQueue = new SynchronousQueue<>();
	}

	public void addPacket(Class<? extends Packet> clazz) {
		if (!this.packets.containsKey(clazz)) {
			this.packets.put(clazz, new HashMap<Listener, Method>());
		}
	}

	public void registerHandler(Class<? extends Packet> clazz, Listener inst, Method handler) throws IllegalArgumentException {
		if (!this.packets.containsKey(clazz)) {
			throw new IllegalArgumentException("No packet can handle that type of packet");
		}

		this.packets.get(clazz).put(inst, handler);
	}

	public void unregisterHandler(Class<? extends Packet> clazz, Method handler) throws IllegalArgumentException {
		if (!this.packets.containsKey(clazz)) {
			throw new IllegalArgumentException("No packet can handle that type of packet");
		}

		this.packets.get(clazz).remove(handler);
	}

	public void queuePacket(Packet packet) {
		this.packetQueue.offer(packet);
	}

	class PacketQueueRunner implements Runnable {

		@Override
		public void run() {
			synchronized (packetQueue) {
				Packet packet = packetQueue.poll();
				for (Method handler : packets.get(packet).values()) {
					try {
						if (handler.getParameterTypes()[0].isInstance(packet)) {
							handler.invoke(packets.get(packet.getClass()).get(handler), packet);							
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
