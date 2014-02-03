package com.ttaylorr.dev.humanity.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerStream {

	public final Socket socket;
	public final ObjectInputStream input;
	public final ObjectOutputStream output;

	public ServerStream(Socket socket) throws IOException {
		this.socket = socket;
		this.input = new ObjectInputStream(socket.getInputStream());
		this.output = new ObjectOutputStream(socket.getOutputStream());
	}

}
