package com.ttaylorr.dev.humanity.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.ttaylorr.dev.humanity.server.handlers.Handler;
import com.ttaylorr.dev.humanity.server.handlers.Listener;
import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.packets.SimplePacketManager;

public class CardsAgainstHumanityServer extends Thread {

	private final ServerSocket socket;
	private static final SimplePacketManager manager;
	private Socket clientSocket;

	static {
		manager = new SimplePacketManager();
	}

	public CardsAgainstHumanityServer(int port) throws IOException {
		this.socket = new ServerSocket(port);
	}

	@Override
	public void run() {
		while (true) {
			try {
				clientSocket = socket.accept();

				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				System.out.println("Recieved [" + socket.getLocalPort() + "]: " + reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public SimplePacketManager getPacketManager() {
		return this.manager;
	}

	public void sendPacket(Packet packet) {
		manager.queuePacket(packet);
	}

	public static void registerPacketHandler(Listener inst) {
		Method[] methods = inst.getClass().getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(Handler.class) != null) {
			    Class<?>[] params = method.getParameterTypes();
				if (params.length == 1) {
				    Class<? extends Packet> clazz = null;
				    try {
				        clazz = (Class<? extends Packet>) params[0];
				    } catch (ClassCastException e) {
				        throw new IllegalArgumentException(params[0].getSimpleName() + " is not a valid packet class.");
				    }
					manager.registerHandler(clazz, inst, method);
				}
			}
		}
	}
}
