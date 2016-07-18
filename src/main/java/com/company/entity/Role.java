package com.company.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@javax.persistence.Entity
@Table(name = "roles")
public class Role extends Entity {

    @Column(nullable = false)
    private String role;

    @OneToMany(mappedBy="role", fetch= FetchType.LAZY)
    private List<User> users = new ArrayList<User>();

    public Role() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
