package com.ttaylorr.dev.humanity.server.configuration.providers;

import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;

import java.util.HashMap;
import java.util.Set;

public class ClientNormalConfigurationProvider implements ConfigurationProvider {

    private HashMap<String, String> data;
    private String file;

    public ClientNormalConfigurationProvider(String file) {
        this.file = file;
        data = new HashMap<>();
        readFromFile();
    }

    public ClientNormalConfigurationProvider(String file, String username) {
        this(file);
        data.put(ConfigurationProvider.CLIENT_NAME_KEY, username);
    }

    /**
     * bring up the configuration-creation GUI on the client side.
     */
    public ClientNormalConfigurationProvider() {
        this(null);
    }

    /**
     * TODO use a YAML/whatever parser to handle this, or I can write one for just this project.
     */
    private void readFromFile() {

//        throw new UnsupportedOperationException("ClientNormalConfigurationProvider not implemented yet.");
    }

    @Override
    public String getByKey(String key) {
        return data.get(key);
    }

    @Override
    public Set<String> getKeys() {
        return data.keySet();
    }

    @Override
    public boolean hasKey(String key) {
        return data.containsKey(key);
    }
}
