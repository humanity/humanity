package com.ttaylorr.dev.humanity.server.configuration;

import java.util.Set;

/**
 * Probably a reader for a configuration file, or, for simplicity, a simple default provider.
 *
 * @author Jack
 */
public interface ConfigurationProvider {
    public String getByKey(String key);

    public Set<String> getKeys();

    public boolean hasKey(String key);

    public static final String SERVER_PORT_KEY = "server-port";
    public static final String SERVER_IP_KEY = "server-ip";
    public static final String MAX_PLAYERS_KEY = "max-player";
    public static final String MIN_PLAYERS_KEY = "min-players";
    public static final String CARDS_TO_WIN = "cards-to-win";
    public static final String CLIENT_NAME_KEY = "client-name";
    public static final String SERVER_OWNER_NAME_KEY = "owner-name";
    public static final String GAME_NAME = "game-name";
}  
