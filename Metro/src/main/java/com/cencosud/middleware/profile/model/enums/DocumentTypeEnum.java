package com.cencosud.middleware.profile.model.enums;

public enum DocumentTypeEnum {
    DNI("dni"),
    RUT("rut"),
    FOREIGN_CARD("carnet de extranjeria");

    private String description;

    DocumentTypeEnum(String description) {
        this.description = description;
    }

    public String description() {
        return description;
    }
}
