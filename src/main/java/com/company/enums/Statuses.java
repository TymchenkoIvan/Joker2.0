package com.company.enums;

/**
 * Created by tymchenkoivan on 12.07.2015.
 */
public enum Statuses { NEW("new"), ARCHIVE("archive"), DELETED("deleted");

    private String status;

    Statuses(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
