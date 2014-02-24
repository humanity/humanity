package com.ttaylorr.dev.humanity.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
@Deprecated
public class ServerStream {
    // because these are final, we're okay making them public. This means that for a new connection or whatever,
    // a ServerStream must be remade, and this socket closed.
    public final Socket socket;
    public final ObjectInputStream input;
    public final ObjectOutputStream output;

    public ServerStream(Socket socket) throws IOException {

        this.socket = socket;
        System.out.println("before input construction");
        this.input = new ObjectInputStream(socket.getInputStream());

        System.out.println("before output construction");
       this.output = null/*new ObjectOutputStream(socket.getOutputStream())*/;
        System.out.println("after output construction and flushing");
    }
}
