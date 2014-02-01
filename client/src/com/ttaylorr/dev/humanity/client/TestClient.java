package com.ttaylorr.dev.humanity.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import com.ttaylorr.dev.humanity.server.CardsAgainstHumanityServer;

public class TestClient {

	public static void main(String[] args) throws IOException {
		System.out.println("Attempting to start client...");

		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket("localhost", 7000);
		} catch (ConnectException e) {
			System.err.println("Server not open!");
		}
		
		if (clientSocket != null) {
		    CardsAgainstHumanityServer.registerPacketHandler(new TestPacketHandler());
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

			output.write("Sending some data from client...".getBytes());

			clientSocket.close();			
		}
	}
	
}
