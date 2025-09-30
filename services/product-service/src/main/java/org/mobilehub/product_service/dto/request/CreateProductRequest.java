package org.mobilehub.product_service.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {
    String name;
    String description;
}
