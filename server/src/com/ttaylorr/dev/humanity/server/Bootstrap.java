package com.ttaylorr.dev.humanity.server;

import java.io.IOException;

public class Bootstrap {
	static HumanityServer server;
	public static boolean DEBUG_MODE = false;

	public static void main(String[] args) {
		DEBUG_MODE = System.getProperty("debug") != null;

		System.out.println(DEBUG_MODE + ": debug enbled");

		System.out.println("Starting server...");
		try {
			server = new HumanityServer(7000);
			Thread thread = new Thread(server);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

	public static HumanityServer getServer() {
		return server;
	}

}
