package net.humanity_game.client.listeners;

import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.packets.core.Packet12OpenHand;

public class HandListener {
    @ClientHandler(priority = HandlerPriority.NORMAL, handleSelf = false)
    public void onHandOpen(Packet12OpenHand packet) {
        // todo set the proper stuff (i.e., the trick)
    }

}
