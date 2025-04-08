package com.extia.easyshopper.domain.mapper;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import com.extia.easyshopper.domain.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IProductMapper {
    Product productRequestToProduct(ProductRequest request);
    ProductResponse productToProductResponse(Product product);
    List<ProductResponse> productListToProductResponseList(List<Product> products);
    Product updateProduct(ProductRequest request, @MappingTarget Product product);
}
