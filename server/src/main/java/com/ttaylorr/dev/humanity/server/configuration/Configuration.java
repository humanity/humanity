package com.ttaylorr.dev.humanity.server.configuration;

import com.ttaylorr.dev.humanity.server.configuration.providers.DefaultConfigurationProvider;

/**
 * I imagine one of these would be used for the Sever and Client, and they would exchange little data, because the data that is subject to
 * exchange is all required to even make the connection, thus proving both Client and Server share it.
 *
 * @author jtownsend16
 */
public class Configuration {
    private final ConfigurationProvider provider;
    static private final ConfigurationProvider defaults;

    static {
        defaults = new DefaultConfigurationProvider();
    }

    public Configuration(ConfigurationProvider provider) {
        this.provider = provider;
    }

    private String getByKeyInternal(String key) {
        if (provider.hasKey(ConfigurationProvider.SERVER_PORT_KEY)) {
            return provider.getByKey(key);
        } else if (defaults.hasKey(key)) {
            return defaults.getByKey(key);
        } else {
            throw new ConfigurationNotFoundException("Key " + key + " not found in either configuraiton provider.");
        }
    }

    public String getServerIp() {
        return getByKeyInternal(ConfigurationProvider.SERVER_IP_KEY);
    }

    public int getServerPort() {
        return Integer.parseInt(getByKeyInternal(ConfigurationProvider.SERVER_PORT_KEY));
    }

    public int getMaxPlayers() {
        return Integer.parseInt(getByKeyInternal(ConfigurationProvider.MAX_PLAYERS_KEY));
    }

}
