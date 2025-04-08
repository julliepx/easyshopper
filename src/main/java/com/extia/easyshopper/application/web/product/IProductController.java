package com.extia.easyshopper.application.web.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.hibernate.validator.constraints.UUID;

import java.util.List;

@Tag(name = "Products", description = "Operations to manage products")
@RequestMapping("/products")
public interface IProductController {
    @Operation(summary = "Create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully!"),
            @ApiResponse(responseCode = "400", description = "Some required property is missing."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to create the product.")
    })
    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest request);
    @Operation(summary = "Retrieve a product by an ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully!"),
            @ApiResponse(responseCode = "400", description = "The given ID is not an UUID."),
            @ApiResponse(responseCode = "404", description = "The product was not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to create the product.")
    })
    @GetMapping("/{productId}")
    ResponseEntity<ProductResponse> getProduct(@PathVariable @UUID String productId);
    @Operation(summary = "Retrieve all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully!"),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to create the product.")
    })
    @GetMapping
    ResponseEntity<List<ProductResponse>> getProducts();
    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully!"),
            @ApiResponse(responseCode = "400", description = "Some required property is missing."),
            @ApiResponse(responseCode = "404", description = "The product was not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to create the product.")
    })
    @PutMapping("/{productId}")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable @UUID String productId, @RequestBody @Valid ProductRequest request);
    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully!"),
            @ApiResponse(responseCode = "400", description = "The given ID is not an UUID."),
            @ApiResponse(responseCode = "404", description = "The product was not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error ocurred while trying to create the product.")
    })
    @DeleteMapping("/{productId}")
    ResponseEntity<Void> deleteProduct(@PathVariable @UUID String productId);
}
