package com.ttaylorr.dev.humanity.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

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
			DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

			output.write("Sending some data from client...".getBytes());

			clientSocket.close();			
		}
	}
	
}
