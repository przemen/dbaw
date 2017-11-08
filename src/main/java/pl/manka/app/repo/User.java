package pl.manka.app.repo;

import org.springframework.stereotype.Repository;

@Repository
public class User {

	private String login;
	private String hashPassword;

	public User() {

	}

	@Override
	public String toString() {
		return "User [login=" + login + ", hashPassword=" + hashPassword + "]";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

}
