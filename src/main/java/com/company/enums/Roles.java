package com.company.enums;

/**
 * Described roles for User entity.
 *
 * Created by tymchenkoivan on 12.07.2015.
 */
public enum Roles { ADMIN("admin"), USER("user");

    private String role;

    Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}