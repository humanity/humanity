package com.ttaylorr.dev.humanity.server.configuration.providers;

import java.util.HashMap;
import java.util.Set;

import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;

public class ServerNormalConfigurationProvider implements ConfigurationProvider {

	private HashMap<String, String> data;
	private String file;

	public ServerNormalConfigurationProvider(String file) {
		this.file = file;
		data = new HashMap<>();
		readFromFile();
	}

	/**
	 * TODO use a YAML/whatever parser to handle this, or I can write one for just this project.
	 * 
	 * UnsupportedOperationException looked like a pretty good option for representing an unimplemented method, because the
	 * NotYetImplementedException is a <i>reflection</i> Exception (which doesn't do anything special, but it's for a special purpose), not
	 * a normal one.
	 */
	private void readFromFile() {
		throw new UnsupportedOperationException("ClientNormalConfigurationProvider not implemented yet.");
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
