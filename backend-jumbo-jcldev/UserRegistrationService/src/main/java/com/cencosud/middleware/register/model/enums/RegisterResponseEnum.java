package com.cencosud.middleware.register.model.enums;

/**
 * 
 * 
 * <h1>TokenResponseEnum</h1>
 * <p>
 * Posibles respuestas del servicio de registro
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 1, 2017
 */
public enum RegisterResponseEnum {

	SUCCESS(0, "Success", "Success", 200),
	WEAK_PASSWORD(1, "WeakPassword", "Weak Password", 400),
	INVALID_TOKEN(10, "InvalidToken", "Invalid Token", 400),
	WRONG_CREDENTIALS(6, "WrongCredentials", "Invalid AccessKey", 400),
	BLOCKED_USER(3, "BlockedUser", "User Blocked", 400),
	SERVER_ERROR(4, "Error", "Internal Server Error", 500);
	
	
	private int id;
	private String name;
	private String description;
	private int httpCode;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param httpCode
	 */
	private RegisterResponseEnum(int id, String name, String description, int httpCode) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.httpCode = httpCode;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public int getHttpCode() {
		return httpCode;
	}

	public static RegisterResponseEnum findByName(String name) throws Exception {
		for(RegisterResponseEnum tmpEnum:RegisterResponseEnum.values()) {
			if(tmpEnum.getName().equals(name)) {
				return tmpEnum;
			}
		}
		throw new Exception("Enum no encontrado.");
	}	
}
