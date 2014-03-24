package com.ttaylorr.dev.humanity.server.client;

import com.google.common.base.Preconditions;
import com.ttaylorr.dev.humanity.server.client.definition.IClientDefinition;
import com.ttaylorr.dev.humanity.server.client.state.PlayerState;
import com.ttaylorr.dev.humanity.server.packets.core.Packet05PlayerStateChange;

/**
 * The server's perception on what the current state of the client is.
 *
 * Fallback to the definitions given in this class.
 */
public class ServerClientDefinition implements IClientDefinition {

    private PlayerState state;
    private final ClientConnection owner;

    public ServerClientDefinition(ClientConnection owner) {
        this.owner = Preconditions.checkNotNull(owner, "client connection");
    }

    @Override
    public PlayerState getPlayerState() {
        return this.state;
    }

    public PlayerState setPlayerState(PlayerState newPlayerState) {
        this.state = newPlayerState;
        this.owner.sendPacket(new Packet05PlayerStateChange(newPlayerState));

        return this.state;
    }

}
