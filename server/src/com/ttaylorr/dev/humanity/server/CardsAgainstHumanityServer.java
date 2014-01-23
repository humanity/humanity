package com.ttaylorr.dev.humanity.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CardsAgainstHumanityServer extends Thread {

	private final ServerSocket socket;
	private Socket clientSocket;

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

}
