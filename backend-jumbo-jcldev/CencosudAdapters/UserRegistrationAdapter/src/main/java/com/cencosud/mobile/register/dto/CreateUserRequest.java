package com.cencosud.mobile.register.dto;

public class CreateUserRequest {
	
	private String name;
	private String lastName;
	private String validationCode;
	private String password;
	private String email;
	
	public CreateUserRequest() {
	}

	/**
	 * @param name
	 * @param lastName
	 * @param validationCode
	 * @param password
	 * @param email
	 */
	public CreateUserRequest(String name, String lastName, String validationCode, String password, String email) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.validationCode = validationCode;
		this.password = password;
		this.email = email;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the validationCode
	 */
	public String getValidationCode() {
		return validationCode;
	}

	/**
	 * @param validationCode the validationCode to set
	 */
	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CreateUserRequest [name=");
		builder.append(name);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", validationCode=");
		builder.append(validationCode);
		builder.append(", password=");
		builder.append(password);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}

}
