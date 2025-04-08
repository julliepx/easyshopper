package com.extia.easyshopper.domain.service.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductResponse createProduct(ProductRequest request);
    ProductResponse getProduct(String productId);
    List<ProductResponse> getProducts();
    ProductResponse updateProduct(String productId, ProductRequest request);
    void deleteProduct(String productId);
}
