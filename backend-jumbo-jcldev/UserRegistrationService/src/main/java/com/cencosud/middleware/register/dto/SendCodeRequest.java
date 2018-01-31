package com.cencosud.middleware.register.dto;

/**
 * 
 * <h1>SendCodeRequest</h1>
 * <p>
 * Request para el envio de coódigo
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
public class SendCodeRequest {

	private String email;
	private Boolean userShouldExist;
	
	public SendCodeRequest() {
	}

	public SendCodeRequest(String email, Boolean userShouldExist) {
		super();
		this.email = email;
		this.userShouldExist = userShouldExist;
	}

	/**
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getUserShouldExist() {
		return userShouldExist;
	}

	/**
	 * 
	 * @param userShouldExist
	 */
	public void setUserShouldExist(Boolean userShouldExist) {
		this.userShouldExist = userShouldExist;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SendCodeRequest [email=");
		builder.append(email);
		builder.append(", userShouldExist=");
		builder.append(userShouldExist);
		builder.append("]");
		return builder.toString();
	}
}
