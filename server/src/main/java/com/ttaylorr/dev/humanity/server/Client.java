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

    public Client(Socket socket, boolean isServer) {
        this(socket, null, isServer);
    }

    public Client(Socket socket, String name, boolean isServer) {
        player = new Player(name);
        this.socket = socket;
        try {
            constructObjectStreams(isServer);
        } catch (IOException e) {
            // assume no dropped connections--at least for now (the security system could help in re-authenticating Clients).
            e.printStackTrace();
        }
    }

    private void constructObjectStreams(boolean isServer) throws IOException {
        output = new ObjectOutputStream(this.getSocket().getOutputStream());
        output.flush();
        input = new ObjectInputStream(this.getSocket().getInputStream());
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
