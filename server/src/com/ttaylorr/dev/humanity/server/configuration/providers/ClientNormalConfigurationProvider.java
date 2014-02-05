package com.ttaylorr.dev.humanity.server.configuration.providers;

import java.util.HashMap;
import java.util.Set;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.ttaylorr.dev.humanity.server.configuration.ConfigurationProvider;

public class ClientNormalConfigurationProvider implements ConfigurationProvider {

	private HashMap<String, String> data;
	private String file;

	public ClientNormalConfigurationProvider(String file) {
		this.file = file;
		data = new HashMap<>();
		readFromFile();
	}

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

	public void setServerIp(String ip) {
		data.put(ConfigurationProvider.SERVER_IP_KEY, ip);
	}

	public void setServerPort(int port) {
		data.put(ConfigurationProvider.SERVER_PORT_KEY, port + "");
	}
}
