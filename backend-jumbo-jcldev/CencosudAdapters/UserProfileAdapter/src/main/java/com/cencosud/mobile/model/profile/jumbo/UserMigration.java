package com.cencosud.mobile.model.profile.jumbo;

public class UserMigration {

    private Integer code;
    private String message;
    private boolean success;
    private String email;
    private boolean needsMigration;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNeedsMigration() {
        return needsMigration;
    }

    public void setNeedsMigration(boolean needsMigration) {
        this.needsMigration = needsMigration;
    }

    @Override
    public String toString() {
        return "UserMigration [code=" + code + ", message=" + message + ", success=" + success + ", email=" + email
                + ", needsMigration=" + needsMigration + "]";
    }
}
