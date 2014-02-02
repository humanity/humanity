package com.ttaylorr.dev.humanity.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;

import com.ttaylorr.dev.humanity.server.packets.Packet;
import com.ttaylorr.dev.humanity.server.player.Player;

public class Client {

	private Player player;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private Queue<Packet> outboundPackets;

	public Client(Socket socket, String playerName) {
		player = new Player(playerName);
		this.socket = socket;
		try {
			constructObjectStreams();
		} catch (IOException e) {
			// assume no dropped connections--at least for now (the security system could help in re-authenticating Clients).
			e.printStackTrace();
		}
	}

	private void constructObjectStreams() throws IOException {
		input = new ObjectInputStream(socket.getInputStream());
		output = new ObjectOutputStream(socket.getOutputStream());
	}

	/**
	 * On its own thread, listens to the input stream associated with this Client, and then, if the Object received is a Packet, sends it
	 * out to the PacketManager.
	 * 
	 * @author Jack
	 * 
	 */
	class ClientInputReader implements Runnable {
		public void run() {
			while (true) {
				try {
					Object read = input.readObject();
					if (read instanceof Packet) {
						Bootstrap.getServer().getPacketManager().queuePacket((Packet) read);
					} else {
						// TODO send back an InvalidPacketError
						socket.close();
					}
				} catch (ClassNotFoundException e) {
				} catch (IOException e) {
					// TODO assume no dropped connections--at least for now (the security system could help in re-authenticating Clients).

					e.printStackTrace();
				}
			}
		}
	}
}
