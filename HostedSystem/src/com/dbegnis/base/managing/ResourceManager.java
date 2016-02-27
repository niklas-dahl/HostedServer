package com.dbegnis.base.managing;

import java.util.HashMap;

public class ResourceManager {

	private static ResourceManager resourceManager;

	private HashMap<String, Object> resources;

	private ResourceManager() {
		resources = new HashMap<>();
	}

	public Object get(String key) {
		return resources.get(key);
	}

	public void put(String key, Object value) {
		resources.put(key, value);
	}

	public void remove(String key) {
		resources.remove(key);
	}

	protected static ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new ResourceManager();
		}
		return resourceManager;
	}

}