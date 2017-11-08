package pl.manka.app.service;

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
		return SesionStorage.contains(uuid);
	}

	public void add(String uuid) {
		SesionStorage.put(uuid);
	}

	public void setProperty(String uuid, String key, String value) {
		SesionStorage.setPropertyAtKey(uuid, key, value);
	}
	
	public String getProperty(String uuid, String key) {
		return SesionStorage.getPropertyAtKey(uuid, key);
	}
	
}
