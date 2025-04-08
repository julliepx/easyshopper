package com.extia.easyshopper.domain.service.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.application.dto.response.ProductResponse;
import com.extia.easyshopper.domain.exception.NotFoundException;
import com.extia.easyshopper.domain.mapper.IProductMapper;
import com.extia.easyshopper.domain.model.Product;
import com.extia.easyshopper.infrastructure.repository.product.IProductRepository;
import com.extia.easyshopper.stubs.IProductStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService productService;
    @Mock
    private IProductMapper productMapper;
    @Mock
    private IProductRepository productRepository;
    private Product product;
    private List<Product> productList;
    private ProductResponse productResponse;
    private List<ProductResponse> productResponseList;
    private ProductRequest productRequest;

    @BeforeEach
    public void setup() {
        this.product = IProductStub.buildProduct();
        this.productList = IProductStub.buildProductList();
        this.productRequest = IProductStub.buildProductRequest();
        this.productResponse = IProductStub.buildProductResponse();
        this.productResponseList = IProductStub.buildProductResponseList();
    }

    @Test
    public void shouldCreateAProductSuccessfully() {
        when(this.productMapper.productRequestToProduct(productRequest)).thenReturn(product);
        when(this.productRepository.save(any(Product.class))).thenReturn(product);
        when(this.productMapper.productToProductResponse(product)).thenReturn(productResponse);

        ProductResponse response = this.productService.createProduct(productRequest);

        assertAll("createProduct",
                () -> assertEquals(productRequest.name(), response.name()),
                () -> assertEquals(productRequest.description(), response.description()),
                () -> assertEquals(productRequest.imageUrl(), response.imageUrl()));
        verify(this.productRepository).save(any(Product.class));
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenGetProductByNonExistingId() {
        String invalidId = "INVALID_ID";
        when(this.productRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.productService.getProduct(invalidId));
        verify(this.productRepository).findById(invalidId);
    }

    @Test
    public void shouldGetProductByIdSuccessfully() {
        String productId = "VALID_ID";
        when(this.productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(this.productMapper.productToProductResponse(product)).thenReturn(productResponse);

        ProductResponse response = this.productService.getProduct(productId);

        assertEquals(response.id(), product.getId());
        verify(this.productRepository).findById(productId);
    }

    @Test
    public void shouldGetAllProductsSuccessfully() {
        when(this.productRepository.findAll()).thenReturn(productList);
        when(this.productMapper.productListToProductResponseList(productList)).thenReturn(productResponseList);

        List<ProductResponse> response = this.productService.getProducts();

        assertAll("getProducts",
                () -> assertEquals(response.get(0).id(), productList.get(0).getId()),
                () -> assertEquals(response.get(1).id(), productList.get(1).getId()));
        verify(this.productRepository).findAll();
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUpdateNonExistingProduct() {
        String invalidId = "INVALID_ID";
        when(this.productRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> this.productService.updateProduct(invalidId, productRequest));
        verify(this.productRepository).findById(invalidId);
    }

    @Test
    public void shouldUpdateAProductSuccessfully() {
        String productId = "VALID_ID";
        when(this.productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(this.productMapper.updateProduct(productRequest, product)).thenReturn(product);
        when(this.productRepository.save(any(Product.class))).thenReturn(product);
        when(this.productMapper.productToProductResponse(product)).thenReturn(productResponse);

        ProductResponse response = this.productService.updateProduct(productId, productRequest);

        assertAll("updateProduct",
                () -> assertEquals(productRequest.name(), response.name()),
                () -> assertEquals(productRequest.description(), response.description()),
                () -> assertEquals(productRequest.imageUrl(), response.imageUrl()));
        verify(this.productRepository).save(any(Product.class));
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenDeletingNonExistingProduct() {
        String invalidId = "INVALID_ID";
        when(this.productRepository.existsById(invalidId)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> this.productService.deleteProduct(invalidId));
        verify(this.productRepository).existsById(invalidId);
    }

    @Test
    public void shouldDeleteAProductSuccessfully() {
        String productId = "VALID_ID";
        when(this.productRepository.existsById(productId)).thenReturn(true);

        this.productService.deleteProduct(productId);

        verify(this.productRepository).deleteById(productId);
    }
}
