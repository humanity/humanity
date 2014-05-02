package net.humanity_game.server.listeners;

import com.google.common.base.Preconditions;
import net.humanity_game.server.HumanityServer;
import net.humanity_game.server.client.ClientConnection;
import net.humanity_game.server.handlers.Handler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet01KeepAlive;

public class KeepAliveListener implements Listenable {

    private HumanityServer server;

    public KeepAliveListener(HumanityServer server) {
        this.server = Preconditions.checkNotNull(server, "server");
    }

    @Handler(priority = HandlerPriority.MONITOR)
    public void onKeepAliveIncoming(Packet01KeepAlive packet, ClientConnection client) {
        // send back an identical keep-alive packet directed at the client and with the identical keep-alive id
        client.sendPacket(new Packet01KeepAlive(packet.getClientId(), packet.getKeepAliveId()));
    }
}
