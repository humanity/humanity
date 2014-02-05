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
	
	public static final String SERVER_PORT_KEY = "severport";
	public static final String SERVER_IP_KEY = "serverip";
	public static final String MAX_PLAYERS_KEY = "maxplayers";
	public static final String MIN_PLAYERS_KEY = "minplayers"; // does this even apply to C.A.H.?
	public static final String CARDS_TO_WIN = "cardstowin";
	public static final String CARD_CSAR_MODE = "csarmode"; //selection process, anything configurable about the card csar?
	public static final String CLIENT_NAME_KEY = "clientname";
	public static final String SERVER_OWNER_NAME_KEY="serverownername";
	public static final String GAME_NAME = "gamename"; // allows for future custom games to customize this easily. 
}  
