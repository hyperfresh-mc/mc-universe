package com.octopod.network.cache;

import com.octopod.network.NetworkLogger;
import com.octopod.network.NetworkPlus;
import com.octopod.network.ServerInfo;

import java.util.*;

public class NetworkServerCache {

    private NetworkServerCache() {}

    public static void reset() {
        serverMap.clear();
    }

	private static Map<String, ServerInfo> serverMap = Collections.synchronizedMap(new HashMap<String, ServerInfo>());

	public static boolean addServer(String server, ServerInfo info) {
        boolean isNew = !serverMap.containsKey(server);
        serverMap.put(server, info);
        if(isNew) {
            NetworkPlus.getLogger().debug("Recieved new info from &a" + server);
        } else {
            NetworkPlus.getLogger().debug("Recieved updated info from &a" + server);
        }
        return isNew;
	}

	public static boolean removeServer(String server) {
		if(serverMap.containsKey(server)) {
            NetworkPlus.getLogger().debug("&cRemoved server &a" + server + "&c from cache.");
			serverMap.remove(server);
            return true;
		} else {
			return false;
		}
	}

	public static boolean serverExists(String server) {
		return serverMap.containsKey(server);
	}

    public static ServerInfo getInfo(String server) {
        if(serverExists(server)) {
            return serverMap.get(server);
        } else {
            return null;
        }
    }

	public static Map<String, ServerInfo> getServerMap() {
		return new HashMap<>(serverMap);
	}

}
