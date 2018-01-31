package com.cencosud.middleware.profile.model;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *	Perfil del usuario
 *  El mismo obtiene datos del login de redes sociales.
 *  Agrega el RutParaPuntosCencosud, el cual le va a permitir canjear puntos por productos. 
 * 
 */
@Document(collection = "user_profile")
public class UserProfile {

	@Id
	private String id;
	private String userProfileId;
	private String displayName;
	private String rutPuntosCencosud;
	private String document;
	private String documentType;
	private Map<String, String> attributes;
	private Favorites favorites;
	private UserConfiguration configurations;

	public UserProfile() {
		super();
	}

	public UserProfile(String id, String userProfileId, String displayName, 
			Map<String, String> attributes, Favorites favorites) {
		super();
		this.id = id;
		this.userProfileId = userProfileId;
		this.displayName = displayName;
		this.attributes = attributes;
		this.favorites = favorites;
	}

	public UserProfile(String id, String userProfileId, String displayName, 
			String document, String documentType, Map<String, String> attributes, 
			Favorites favorites, UserConfiguration configurations) {
		super();
		this.id = id;
		this.userProfileId = userProfileId;
		this.displayName = displayName;
		this.document = document;
		this.documentType = documentType;
		this.attributes = attributes;
		this.favorites = favorites;
		this.configurations = configurations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Favorites getFavorites() {
		return favorites;
	}

	public void setFavorites(Favorites favorites) {
		this.favorites = favorites;
	}

	public UserConfiguration getConfigurations() {
		return configurations;
	}

	public void setConfigurations(UserConfiguration configurations) {
		this.configurations = configurations;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getRutPuntosCencosud() {
		return rutPuntosCencosud;
	}

	public void setRutPuntosCencosud(String rutPuntosCencosud) {
		this.rutPuntosCencosud = rutPuntosCencosud;
	}
}
