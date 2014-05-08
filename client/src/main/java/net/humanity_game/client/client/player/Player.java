package net.humanity_game.client.client.player;

import net.humanity_game.server.handlers.Listenable;

import java.util.UUID;

/**
 * User: Jack Date: 5/8/2014 Time: 8:15 AM
 */
public class Player {
    private final UUID clientId;
    private String name;

    public Player(UUID uuid, String name) {
        this.clientId = uuid;
        this.name = name;
    }

    private void attachListener(Listenable listenable) {

    }

    public UUID getClientId() {
        return this.clientId;
    }

    public String getName() {
        return this.name;
    }
}
