package net.humanity_game.server.game;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.UUID;

public class UUIDMasker {

    private BiMap<UUID, UUID> map;

    public UUIDMasker(HumanityGame game) {
        this(game.getPlayers().size());
    }

    public UUIDMasker(int entries) {
        map = HashBiMap.create(entries);
    }

    public UUIDMasker() {
        map = HashBiMap.create();
    }

    /**
     * Mask a client behind a fake UUID so that clients don't know who's who.
     *
     * @param uuid
     * @return
     */
    public UUID mask(UUID uuid) {
        UUID fresh = UUID.randomUUID();
        map.put(uuid, fresh);
        return fresh;
    }

    /**
     * Get the identity of a masked person client.
     *
     * @param uuid
     * @return
     */
    public UUID reveal(UUID uuid) {
        return map.inverse().get(uuid);
    }

    /**
     * Whether or not the uuid has a secret identity.
     *
     * @param uuid
     * @return
     */
    public boolean isMasked(UUID uuid) {
        return map.containsKey(uuid);
    }

    /**
     * Whether or not this uuid is a secret identity.
     *
     * @param uuid
     * @return
     */
    public boolean isFake(UUID uuid) {
        return map.containsValue(uuid);
    }
}
