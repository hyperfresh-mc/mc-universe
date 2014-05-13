package com.octopod.network.cache;

import java.util.*;

/**
 * @author Octopod
 *         Last updated on 2/26/14
 */
public class NetworkHubCache {

    private static Map<Integer, List<String>> hubMap = new TreeMap<>(new Comparator<Integer>() {

        @Override
        public int compare(Integer a, Integer b) {
            return a.compareTo(b);
        }

    });

    public static void addHub(String serverID, Integer priority) {
        if(!hubMap.containsKey(priority)) {
            hubMap.put(priority, new ArrayList<String>());
        }
        hubMap.get(priority).add(serverID);
    }

    public static String getHub() {
        for(Integer p: hubMap.keySet()) {

            List<String> hubs = hubMap.get(p);

            if(hubs.size() == 1) {
                return hubs.get(0);
            } else {
                Random rand = new Random();
                return hubs.get(rand.nextInt(hubs.size()));
            }

        }
        return null;
    }
}
