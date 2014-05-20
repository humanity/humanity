package net.humanity_game.client.listeners;

import net.humanity_game.client.packets.handler.ClientHandler;
import net.humanity_game.server.handlers.HandlerPriority;
import net.humanity_game.server.packets.core.Packet12OpenHand;

public class HandListener {
    @ClientHandler(priority = HandlerPriority.NORMAL, handleSelf = false)
    public void onHandOpen(Packet12OpenHand packet) {
        // steps for this: a) setup the trick; b) prompt for card selection; c) send back the proper packet (Packet13, I think).
        // Only the first two steps should be done here, the third should be async, associated with a GUI button.

        // todo see above.
    }

    // todo all the other listeners that have to do with the hand (czar stuff, perhaps?).
}
