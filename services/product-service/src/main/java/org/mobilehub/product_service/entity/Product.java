package org.mobilehub.product_service.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.mobilehub.product_service.util.ProductStatusConverter;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column
    String description;

    @Column(name = "status")
    @Convert(converter = ProductStatusConverter.class)
    ProductStatus status =  ProductStatus.ACTIVE;
}