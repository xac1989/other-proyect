package com.cencosud.middleware.register.model;

public class UserProfileData {
	private String firstName;
	private String lastName;
	private String email;
	private String needsMigration;
	
	
	public UserProfileData() {
	}
	
	

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public UserProfileData(String firstName, String lastName, String email, String needsMigration) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.needsMigration = needsMigration;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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


	public String isNeedsMigration() {
		return needsMigration;
	}



	public void setNeedsMigration(String needsMigration) {
		this.needsMigration = needsMigration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfileData [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", needsMigration=");
		builder.append(needsMigration);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
