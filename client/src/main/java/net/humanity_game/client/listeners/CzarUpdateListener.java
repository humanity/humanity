package net.humanity_game.client.listeners;

import com.google.common.base.Preconditions;
import net.humanity_game.client.Bootstrap;
import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.handlers.Listenable;
import net.humanity_game.server.packets.core.Packet11CzarUpdate;

public class CzarUpdateListener implements Listenable {

    @ClientHandler(priority = HandlerPriority.NORMAL, handleSelf = false)
    public void handleCzarUpdate(Packet11CzarUpdate packet) {
        Bootstrap.getGame().setCurrentCzar(Preconditions.checkNotNull(packet, "packet").getNewCzar());
    }
}
