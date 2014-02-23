package com.ttaylorr.dev.humanity.server;

import com.ttaylorr.dev.humanity.server.player.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Player player;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Client(Socket socket) {
        this(socket, null);
    }

    public Client(Socket socket, String name) {
        player = new Player(name);
        this.socket = socket;
        try {
            constructObjectStreams();
        } catch (IOException e) {
            // assume no dropped connections--at least for now (the security system could help in re-authenticating Clients).
            e.printStackTrace();
        }
    }

    private void constructObjectStreams() throws IOException {
        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();
        input = new ObjectInputStream(socket.getInputStream());
    }

    public Player getPlayer() {
        return player;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOutput() {
        return output;
    }

    public ObjectInputStream getInput() {
        return input;
    }

    public void setName(String name) {
        player.setName(name);
    }


}
