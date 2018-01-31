package com.cencosud.mobile.model.profile.jumbo;

import java.util.Map;

import com.cencosud.mobile.dto.profile.jumbo.FavoritesDto;
import com.cencosud.mobile.model.category.UserConfiguration;

/**
 * 
 * 
 * <h1>UserProfileDto</h1>
 * <p>
 * </p>
 * @author fernando.castro
 * @version 1.0
 * @since Jun 19, 2017
 */
public class UserProfileHomolog extends JumboUserProfileHomolog {
	private String id;
	private String displayName;
	private Map<String, String> attributes;
	private FavoritesDto favorites;
	private UserConfiguration configurations;
	private String document;
	private String documentType;
	private String fullName;
	private String birthDate;
	private String sex;
	private String phone;

	private String profilePicture;
	private String socialLoginVendor;
	private String email;
	private String familyName;
	private String givenName;

	public UserProfileHomolog() {
	}

	public UserProfileHomolog(String id, String displayName, Map<String, String> attributes, FavoritesDto favorites,
			UserConfiguration configurations, String document, String documentType, String fullName, String birthDate,
			String sex, String phone) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.attributes = attributes;
		this.favorites = favorites;
		this.configurations = configurations;
		this.document = document;
		this.documentType = documentType;
		this.fullName = fullName;
		this.birthDate = birthDate;
		this.sex = sex;
		this.phone = phone;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	/**
	 * @return the favorites
	 */
	public FavoritesDto getFavorites() {
		return favorites;
	}

	/**
	 * @param favorites the favorites to set
	 */
	public void setFavorites(FavoritesDto favorites) {
		this.favorites = favorites;
	}

	/**
	 * @return the configurations
	 */
	public UserConfiguration getConfigurations() {
		return configurations;
	}

	/**
	 * @param configurations the configurations to set
	 */
	public void setConfigurations(UserConfiguration configurations) {
		this.configurations = configurations;
	}

	/**
	 * @return the document
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @return the documentType
	 */
	public String getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSocialLoginVendor() {
        return socialLoginVendor;
    }

    public void setSocialLoginVendor(String socialLoginVendor) {
        this.socialLoginVendor = socialLoginVendor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfileDto [id=");
		builder.append(id);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", attributes=");
		builder.append(attributes);
		builder.append(", favorites=");
		builder.append(favorites);
		builder.append(", configurations=");
		builder.append(configurations);
		builder.append(", document=");
		builder.append(document);
		builder.append(", documentType=");
		builder.append(documentType);
		builder.append(", fullName=");
		builder.append(fullName);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}
	
}
