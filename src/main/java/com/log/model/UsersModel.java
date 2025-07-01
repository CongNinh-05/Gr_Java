package com.log.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")

public class UsersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;
    String email;
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.user; // Mặc định là USER

    // Constructors
    public UsersModel() {
        // Constructor mặc định cần thiết cho JPA
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass () != o.getClass ()) return false;
        UsersModel that = (UsersModel) o;
        return Objects.equals ( id, that.id ) && Objects.equals ( name, that.name ) && Objects.equals ( email, that.email ) && Objects.equals ( password, that.password ) && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id, name, password, email, role );
    }

    @Override
    public String toString() {
        return "UsersModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }


}


