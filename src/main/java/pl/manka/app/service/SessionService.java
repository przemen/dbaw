package pl.manka.app.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.manka.app.repo.SesionStorage;

@Service
public class SessionService {

	@Autowired
	private SesionStorage SesionStorage;

	public SessionService() {
		SesionStorage = new SesionStorage();
	}

	public boolean isLoged(String uuid) {
		return SesionStorage.getSesion().containsKey(uuid);
	}

	public void add(String key) {
		SesionStorage.getSesion().put(key, new HashMap<String, String>());
	}

	public void setProperty(String uuid, String key, String value) {
		SesionStorage.getSesion().get(uuid).put(key, value);
	}

	public String getProperty(String uuid, String key) {
		return SesionStorage.getSesion().get(uuid).get(key);
	}

}
