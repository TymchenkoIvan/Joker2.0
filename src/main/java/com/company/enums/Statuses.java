package com.company.enums;


public enum Statuses { NEW("new"), ARCHIVE("archive"), DELETED("deleted");

    private String status;

    Statuses(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
