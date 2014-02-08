package com.ttaylorr.dev.humanity.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ttaylorr.dev.humanity.client.packets.ClientPacketSender;
import com.ttaylorr.dev.humanity.server.configuration.Configuration;

public class ClientRunner {

	private ClientPacketSender outputSender;
	private ServerStream sstream;
	private Configuration configuration;

	ClientRunner(String ip, int port) throws UnknownHostException, IOException {
		Socket sock = new Socket(ip, port);
		sstream = new ServerStream(sock);
		outputSender = new ClientPacketSender(sstream);
	}

	public static void main(String[] args) {

	}

	public ClientPacketSender getOutputSender() {
		return outputSender;
	}

	public ServerStream getSstream() {
		return sstream;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

}
