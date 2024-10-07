package com.project.practice.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
/*
 * Marks the class as a JPA entity.
 * Assigns it a name for the database table
 */
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * Primary key
     * Configured to generate key by autoincrementing based on database
     */
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    public Users() {

    }

    public Users(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Users))
            return false;
        Users users = (Users) obj;
        return Objects.equals(id, users.id) && Objects.equals(username, users.username);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

}