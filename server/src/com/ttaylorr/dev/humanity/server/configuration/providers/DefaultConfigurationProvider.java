package com.ttaylorr.dev.humanity.server.configuration.providers;

import java.util.Set;

import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;

/**
 * Represents a limited array of configuration options, including only the ones
 * that are likely to remain the same for all users, on all servers.
 * 
 * This must be fleshed out.
 * 
 * @author jtownsend16
 * 
 */
public class DefaultConfigurationProvider implements ConfigurationProvider {

	public DefaultConfigurationProvider() {
	}

	@Override
	public String getByKey(String key) {
		return null;
	}

	@Override
	public Set<String> getKeys() {
		return null;
	}

	@Override
	public boolean hasKey(String key) {
		return false;
	}

}
