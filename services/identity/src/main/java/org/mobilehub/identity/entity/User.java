package org.mobilehub.identity.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    String id;

    @Column(name = "email")
    String email;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roleSet;
}
