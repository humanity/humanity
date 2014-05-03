package net.humanity_game.server.packets.core;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.humanity_game.server.packets.Packet;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Packet11RemovedPlayersList extends Packet {

    List<UUID> removedPlayers; // todo perhaps a Set would be a better option?

    public Packet11RemovedPlayersList(Collection<UUID> removedPlayers) {

        Preconditions.checkNotNull(removedPlayers);
        for (UUID uuid : removedPlayers) {
            if (uuid == null) continue;
            else
                removedPlayers.add(uuid);
        }
        Preconditions.checkState(removedPlayers.size() > 0, "no players in remove list");
    }

    public Packet11RemovedPlayersList(UUID player) {
        this(Lists.newArrayList(player));
    }

    public ImmutableList<UUID> getUpdatedPlayers() {
        return ImmutableList.copyOf(removedPlayers);
    }


}
