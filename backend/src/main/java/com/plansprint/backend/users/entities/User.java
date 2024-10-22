package com.plansprint.backend.users.entities;

import com.plansprint.backend.common.entities.Base;
import com.plansprint.backend.users.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends Base {
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private Role role;
}
