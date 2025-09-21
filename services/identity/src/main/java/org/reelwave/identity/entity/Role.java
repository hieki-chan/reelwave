package org.reelwave.identity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    String id;

    String name;
}
