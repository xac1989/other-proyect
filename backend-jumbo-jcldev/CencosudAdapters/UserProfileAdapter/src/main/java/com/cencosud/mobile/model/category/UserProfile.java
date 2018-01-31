package com.cencosud.mobile.model.category;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class UserProfile {

    private String id;
    private String displayName;
    private Map<String, String> attributes;
    private Favorites favorites;
    private UserConfiguration configurations;
    private String document;
    private String documentType;
    private String fullName;
    private String birthDate;
    private String sex;
    private String phone;

    public UserProfile() {
    }

    public UserProfile(String id, String displayName, Map<String, String> attributes, Favorites favorites,
            UserConfiguration configurations, String document, String documentType, String fullName, String birthDate,
            String sex, String phone) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
