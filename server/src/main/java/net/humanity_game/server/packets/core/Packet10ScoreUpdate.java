package net.humanity_game.server.packets.core;

import net.humanity_game.server.packets.Packet;

import java.util.UUID;

public class Packet10ScoreUpdate extends Packet {
    private final int score;

    public Packet10ScoreUpdate(UUID uuid, int score) {
        super(uuid);
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }
}
