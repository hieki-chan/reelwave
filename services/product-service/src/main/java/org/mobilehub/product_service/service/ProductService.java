package org.mobilehub.product_service.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mobilehub.cloud_media_service.dto.response.UploadResponse;
import org.mobilehub.product_service.client.CloudMediaServiceClient;
import org.mobilehub.product_service.dto.request.CreateProductRequest;
import org.mobilehub.product_service.dto.request.UpdateProductRequest;
import org.mobilehub.product_service.dto.response.ProductResponse;
import org.mobilehub.product_service.entity.Product;
import org.mobilehub.product_service.entity.ProductStatus;
import org.mobilehub.product_service.mapper.ProductMapper;
import org.mobilehub.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CloudMediaServiceClient cloudMediaServiceClient;

    public ProductResponse createProduct(CreateProductRequest request, List<MultipartFile> files) {
        Product product = productMapper.toProduct(request);
        Product savedProduct = productRepository.save(product);

        // upload images
        List<String> imageUrls = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            List<UploadResponse> uploadResponses =
                    cloudMediaServiceClient.uploadMultipleImages(files, "");

            imageUrls = uploadResponses.stream()
                    .map(UploadResponse::getUrl)
                    .toList();
        }

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
