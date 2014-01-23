package com.ttaylorr.dev.humanity.server;

import java.io.IOException;

public class Bootstrap {

	public static void main(String[] args) {
		CardsAgainstHumanityServer server;

		System.out.println("Starting server...");
		try {
			server = new CardsAgainstHumanityServer(7000);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
