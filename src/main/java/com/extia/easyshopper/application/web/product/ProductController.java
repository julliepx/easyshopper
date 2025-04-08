package com.extia.easyshopper.application.web.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import com.extia.easyshopper.domain.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements IProductController {
    @Autowired
    private IProductService productService;

    @Override
    public ResponseEntity<ProductResponse> createProduct(ProductRequest request) {
        return new ResponseEntity<>(this.productService.createProduct(request), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProductResponse> getProduct(String productId) {
        return new ResponseEntity<>(this.productService.getProduct(productId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(String productId, ProductRequest request) {
        return new ResponseEntity<>(this.productService.updateProduct(productId, request), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String productId) {
        this.productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}
