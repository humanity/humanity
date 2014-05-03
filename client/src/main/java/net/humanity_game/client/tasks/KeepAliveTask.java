package net.humanity_game.client.tasks;

import net.humanity_game.client.client.HumanityClient;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet01KeepAlive;
import org.joda.time.Duration;
import org.joda.time.Instant;

import java.util.UUID;
import java.util.concurrent.Callable;

public class KeepAliveTask implements Callable<Boolean>, Listenable {

    private final HumanityClient client;

    private Packet01KeepAlive lastSentPacket;
    private Packet01KeepAlive lastReceivedPacket;

    public KeepAliveTask(HumanityClient client) {
        this.client = client;
        this.client.getPacketHandler().registerHandlers(this);
    }

    @Override
    public Boolean call() throws InterruptedException {
        this.lastSentPacket = new Packet01KeepAlive(this.client.getDefnition().getUUID(), UUID.randomUUID());

        client.sendPacket(this.lastSentPacket);

        // Hack to wait until we've received a packet
        Instant start = Instant.now();
        while(this.lastReceivedPacket == null) {
            Thread.sleep(Bootstrap.LOOP_DELAY);
        }
        this.client.getLogger().debug("Received a response in " + new Duration(start, Instant.now()).getMillis() + " ms!");

        this.client.getPacketHandler().unregisterHandlers(this);
        return this.lastReceivedPacket.equals(this.lastSentPacket);
    }

    @ClientHandler(
        priority = HandlerPriority.NORMAL,
        handleSelf = true
    )
    public void onPotentialKeepAlive(Packet01KeepAlive packet) {
        if(this.lastReceivedPacket == null) {
            this.lastReceivedPacket = packet;
        }
    }
}
