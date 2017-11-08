package pl.manka.app.service;


import org.springframework.stereotype.Service;

@Service
public class Sha256{

	public static String sha256(String input) {
		return  org.apache.commons.codec.digest.DigestUtils.sha256Hex(input);   
    }
}
