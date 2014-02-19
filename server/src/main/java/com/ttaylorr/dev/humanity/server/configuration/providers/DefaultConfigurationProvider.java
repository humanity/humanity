package com.ttaylorr.dev.humanity.server.configuration.providers;

import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;

import java.util.HashMap;
import java.util.Set;

/**
 * Represents a limited array of configuration options, including only the ones
 * that are likely to remain the same for all users, on all servers.
 * <p/>
 * This must be fleshed out.
 *
 * @author jtownsend16
 */
public class DefaultConfigurationProvider implements ConfigurationProvider {

    public DefaultConfigurationProvider() {
    }

    HashMap<String, String> config;

    {
        config = new HashMap<>();
        config.put(ConfigurationProvider.SERVER_IP_KEY, "localhost");
        config.put(ConfigurationProvider.SERVER_PORT_KEY, "7000");
        config.put(ConfigurationProvider.CLIENT_NAME_KEY, "Player");
    }


    @Override
    public String getByKey(String key) {
        return config.get(key);
    }

    @Override
    public Set<String> getKeys() {
        return config.keySet();
    }

    @Override
    public boolean hasKey(String key) {
        return config.containsKey(key);
    }

}
