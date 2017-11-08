package pl.manka.app.repo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class SesionStorage {
	private Map<String, Map<String, String>> storage;
	
	public SesionStorage() {
		storage = new HashMap<String, Map<String, String>>();
	}
	
	public HashMap<String, String> get (String uuid){
		return (HashMap<String, String>) storage.get(uuid);
	}
	
	public boolean contains(String uuid) {
		return storage.containsKey(uuid);
	}
	
	public void put(String uuid) {
		storage.put(uuid, new HashMap<String, String>());
	}
	
	public void setPropertyAtKey (String uuid, String key, String value) {
		storage.get(uuid).put(key, value);
	}
	
	public String getPropertyAtKey (String uuid, String key) {
		return storage.get(uuid).get(key);
	}
	
	
}
