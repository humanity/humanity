package com.ttaylorr.dev.humanity.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ttaylorr.dev.humanity.client.packets.ClientPacketSender;

public class ClientRunner {

	ClientPacketSender outputSender;
	ServerStream sstream;

	ClientRunner(String ip, int port) throws UnknownHostException, IOException {
		Socket sock = new Socket(ip, port);
		sstream = new ServerStream(sock);
		outputSender = new ClientPacketSender(sstream);
	}

	public static void main(String[] args) {

	}
}
