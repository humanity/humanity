package com.ttaylorr.dev.humanity.server.configuration;

import java.util.Set;

/**
 * Probably a reader for a configuration file, or, for simplicity, a simple default provider.
 * 
 * @author Jack
 * 
 */
public interface ConfigurationProvider {
	public String getByKey(String key);

	public Set<String> getKeys();

	public boolean hasKey(String key);
}
