package com.ttaylorr.dev.humanity.server.configuration;

import com.ttaylorr.dev.humanity.server.configuration.providers.DefaultConfigurationProvider;

public class Configuration {
	private final ConfigurationProvider provider;
	static private final ConfigurationProvider defaults;
	static {
		// setup defaults
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

}
