package com.cencosud.middleware.profile.model.enums;

public enum GenderEnum {
    MALE("Masculino"),
    FEMALE("Femenino"),
    UNDEFINED("Prefiero no decirlo");

    private String description;

    GenderEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
