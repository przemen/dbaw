package pl.manka.app.repo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class SesionStorage {
	private Map<String, Map<String, String>> sesion;
	
	public SesionStorage() {
		sesion = new HashMap<String, Map<String, String>>();
	}
	

	public Map<String, Map<String, String>> getSesion() {
		return sesion;
	}

	public void setSesion(Map<String, Map<String, String>> sesion) {
		this.sesion = sesion;
	}
	
	
}
