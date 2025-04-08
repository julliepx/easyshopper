package com.extia.easyshopper.domain.service.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import com.extia.easyshopper.domain.exception.NotFoundException;
import com.extia.easyshopper.domain.mapper.IProductMapper;
import com.extia.easyshopper.domain.model.Product;
import com.extia.easyshopper.infrastructure.repository.product.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    @Autowired
    private IProductMapper productMapper;
    @Autowired
    private IProductRepository productRepository;
    private static final String PRODUCT_NOT_FOUND = "The product was not found.";

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = this.productMapper.productRequestToProduct(request);
        return this.productMapper.productToProductResponse(this.productRepository.save(product));
    }

    @Override
    public ProductResponse getProduct(String productId) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));

        return this.productMapper.productToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = this.productRepository.findAll();
        return this.productMapper.productListToProductResponseList(products);
    }

    @Override
    public ProductResponse updateProduct(String productId, ProductRequest request) {
        Product product = this.productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));

        this.productMapper.updateProduct(request, product);

        Product updatedProduct = this.productRepository.save(product);
        return this.productMapper.productToProductResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        if(!this.productRepository.existsById(productId))
            throw new NotFoundException(PRODUCT_NOT_FOUND);

        this.productRepository.deleteById(productId);
    }
}
