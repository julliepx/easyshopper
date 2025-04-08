package com.extia.easyshopper.stubs;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import com.extia.easyshopper.domain.enums.ProductCategory;
import com.extia.easyshopper.domain.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface IProductStub {
    static ProductRequest buildProductRequest() {
        return ProductRequest.builder()
                .name("iPhone")
                .description("Apple iPhone")
                .price(BigDecimal.TEN)
                .category(ProductCategory.SMARTPHONES)
                .stock(5)
                .imageUrl("https://imageurl.com")
                .build();
    }

    static ProductRequest buildNewProductRequest() {
        return ProductRequest.builder()
                .name("New Phone")
                .description("Brand new right out of the box.")
                .price(BigDecimal.TEN)
                .category(ProductCategory.SMARTPHONES)
                .stock(5)
                .imageUrl("https://imageurl.com")
                .build();
    }

    static ProductRequest.ProductRequestBuilder buildProductRequestBuilder() {
        return ProductRequest.builder()
                .name("iPhone")
                .description("Apple iPhone")
                .price(BigDecimal.TEN)
                .category(ProductCategory.SMARTPHONES)
                .stock(5)
                .imageUrl("https://imageurl.com");
    }

    static Product buildProduct() {
        return Product.builder()
                .name("iPhone")
                .description("Apple iPhone")
                .price(BigDecimal.TEN)
                .category(ProductCategory.SMARTPHONES)
                .stock(5)
                .imageUrl("https://imageurl.com")
                .build();
    }

    static List<Product> buildProductList() {
        return List.of(
                Product.builder()
                    .name("iPhone")
                    .description("Apple iPhone")
                    .price(BigDecimal.TEN)
                    .category(ProductCategory.SMARTPHONES)
                    .stock(5)
                    .imageUrl("https://iphoneimage.com")
                    .build(),
                Product.builder()
                    .name("Samsung")
                    .description("Samsung smartphone")
                    .price(BigDecimal.ONE)
                    .category(ProductCategory.SMARTPHONES)
                    .stock(3)
                    .imageUrl("https://samsungimage.com")
                    .build());
    }

    static ProductResponse buildProductResponse() {
        return ProductResponse.builder()
                .name("iPhone")
                .description("Apple iPhone")
                .price(BigDecimal.TEN)
                .category(ProductCategory.SMARTPHONES)
                .stock(5)
                .imageUrl("https://imageurl.com")
                .build();
    }

    static List<ProductResponse> buildProductResponseList() {
        return List.of(
                ProductResponse.builder()
                        .name("iPhone")
                        .description("Apple iPhone")
                        .price(BigDecimal.TEN)
                        .category(ProductCategory.SMARTPHONES)
                        .stock(5)
                        .imageUrl("https://iphoneimage.com")
                        .build(),
                ProductResponse.builder()
                        .name("Samsung")
                        .description("Samsung smartphone")
                        .price(BigDecimal.ONE)
                        .category(ProductCategory.SMARTPHONES)
                        .stock(3)
                        .imageUrl("https://samsungimage.com")
                        .build());
    }
}
