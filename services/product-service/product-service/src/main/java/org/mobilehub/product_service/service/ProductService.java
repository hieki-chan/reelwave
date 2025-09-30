package org.mobilehub.product_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mobilehub.product_service.dto.request.CreateProductRequest;
import org.mobilehub.product_service.dto.request.UpdateProductRequest;
import org.mobilehub.product_service.dto.response.ProductResponse;
import org.mobilehub.product_service.entity.Product;
import org.mobilehub.product_service.entity.ProductStatus;
import org.mobilehub.product_service.mapper.ProductMapper;
import org.mobilehub.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper  productMapper;

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = productMapper.toProduct(request);
        Product savedProduct = productRepository.save(product);
        return productMapper.toCreateProductResponse(savedProduct);
    }

    public ProductResponse updateProduct(Long id, UpdateProductRequest updateRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        productMapper.updateProduct(product, updateRequest);
        Product updatedProduct = productRepository.save(product);
        return productMapper.toCreateProductResponse(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setStatus(ProductStatus.INACTIVE);
        productRepository.save(product);
    }
}
