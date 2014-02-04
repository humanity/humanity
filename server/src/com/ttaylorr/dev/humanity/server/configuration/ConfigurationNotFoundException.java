package com.ttaylorr.dev.humanity.server.configuration;

/**
 * This is a runtime exception because reasonably, if the configuration can't
 * figure out what this key is, then something is wrong with the supplied
 * ConfigurationProvider, and this should probably shut down shortly.
 * 
 * @author jtownsend16
 * 
 */
public class ConfigurationNotFoundException extends RuntimeException {

	public ConfigurationNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public ConfigurationNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public ConfigurationNotFoundException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
