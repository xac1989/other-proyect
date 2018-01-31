package com.cencosud.middleware.register.model.enums;

/**
 * 
 * 
 * <h1>SendCodeResponseEnum</h1>
 * <p>
 * Errores enciados por el servicio de envio de códigos
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Sep 4, 2017
 */
public enum SendCodeResponseEnum {
	SUCCESS(0, "Success","Success", 200),
	INVALID_TOKEN(10, "Sua autenticação expirou","Invalid Token", 400),
	IVALID_EMAIL(2, "O e-mail informado é inválido", "Invalid Email", 400),
	BLOCKED_USER(3, "Seu login está bloqueado temporariamente.", "User blocked",400),
	SERVER_ERROR(4, "Error", "Internal Server Error", 500),
	EXISTING_EMAIL(5, "Existing Email", "Email already exists.", 409),
	EMAIL_DOESNT_EXIST(7, "Email doesn't ", "Email does not exist", 409);
	
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
	private SendCodeResponseEnum(int id, String name, String description, int httpCode) {
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

	public static SendCodeResponseEnum findByName(String name) throws Exception {
		for(SendCodeResponseEnum tmpEnum:SendCodeResponseEnum.values()) {
			if(tmpEnum.getName().equals(name)) {
				return tmpEnum;
			}
		}
		throw new Exception("Enum no encontrado.");
	}
}
