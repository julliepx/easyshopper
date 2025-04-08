package com.extia.easyshopper.application.web.product;

import com.extia.easyshopper.application.dto.request.ProductRequest;
import com.extia.easyshopper.stubs.IProductStub;
import com.extia.easyshopper.utils.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Sql(statements = "DELETE FROM product", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductControllerTest extends BaseTest {
    @Test
    void shouldReturn400WhenCreatingProductWithEmptyName() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().name("").build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The product name must not be blank."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithEmptyDescription() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().description("").build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The product description must not be blank."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithNullPrice() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().price(null).build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The product price is required."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithNegativePrice() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().price(new BigDecimal(-1)).build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The product price must be positive."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithNullStock() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().stock(null).build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The stock quantity is required."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithNegativeStock() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().stock(-1).build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The stock quantity must be positive."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithNullCategory() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().category(null).build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The product category is required."));
    }

    @Test
    void shouldReturn400WhenCreatingProductWithEmptyImage() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().imageUrl("").build();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(400)
            .body("message", equalTo("The image url must not be blank."));
    }

    @Test
    void shouldReturn201WhenCreatedProductSuccessfully() {
        ProductRequest payload = IProductStub.buildProductRequest();

        given()
            .body(payload)
        .when()
            .post("/products")
        .then()
            .statusCode(201)
                .body("name", equalTo("iPhone"))
                .body("description", equalTo("Apple iPhone"));
    }

    @Test
    void shouldReturn404WhenGetProductByNonExistingId() {
        given()
            .pathParam("productId", UUID.randomUUID())
        .when()
            .get("/products/{productId}")
        .then()
            .statusCode(404)
                .body("message", equalTo("The product was not found."));
    }

    @Test
    void shouldReturn403WhenGetProductByIdDifferentThanUUID() {
        given()
            .pathParam("productId", "NOT_UUID")
        .when()
            .get("/products/{productId}")
        .then()
            .statusCode(400);
    }

    @Test
    void shouldReturn200WhenGetProductByIdSuccessfully() {
        given()
            .pathParam("productId", this.createProductAndGetId())
        .when()
            .get("/products/{productId}")
        .then()
            .statusCode(200)
            .body("name", equalTo("iPhone"))
            .body("description", equalTo("Apple iPhone"));
    }

    @Test
    void shouldReturn200WhenGetAllProductsSuccessfully() {
        this.createProductAndGetId();

        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("[0].name", equalTo("iPhone"))
                .body("[0].description", equalTo("Apple iPhone"));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingProduct() {
        ProductRequest payload = IProductStub.buildProductRequest();

        given()
            .body(payload)
            .pathParam("productId", UUID.randomUUID())
        .when()
            .put("/products/{productId}")
        .then()
            .statusCode(404)
            .body("message", equalTo("The product was not found."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithEmptyName() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().name("").build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The product name must not be blank."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithEmptyDescription() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().description("").build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The product description must not be blank."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithNullPrice() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().price(null).build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The product price is required."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithNegativePrice() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().price(new BigDecimal(-1)).build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The product price must be positive."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithNullStock() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().stock(null).build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The stock quantity is required."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithNegativeStock() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().stock(-1).build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The stock quantity must be positive."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithNullCategory() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().category(null).build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The product category is required."));
    }

    @Test
    void shouldReturn400WhenUpdatingProductWithEmptyImage() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().imageUrl("").build();

        given()
                .pathParam("productId", UUID.randomUUID())
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(400)
                .body("message", equalTo("The image url must not be blank."));
    }

    @Test
    void shouldReturn200WhenUpdatingProductSuccessfully() {
        ProductRequest payload = IProductStub.buildNewProductRequest();

        String productId = this.createProductAndGetId();

        given()
                .pathParam("productId", productId)
                .body(payload)
                .when()
                .put("/products/{productId}")
                .then()
                .statusCode(200)
                .body("name", equalTo(payload.name()))
                .body("description", equalTo(payload.description()));
    }

    @Test
    void shouldReturn404WhenDeletingNonExistentProduct() {
        given()
                .pathParam("productId", UUID.randomUUID())
                .when()
                .delete("/products/{productId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("The product was not found."));
    }

    @Test
    void shouldReturn200WhenDeletingProductSuccessfully() {
        ProductRequest payload = IProductStub.buildProductRequestBuilder().imageUrl("").build();

        String productId = this.createProductAndGetId();

        given()
                .pathParam("productId", productId)
                .when()
                .delete("/products/{productId}")
                .then()
                .statusCode(204);

        given()
                .pathParam("productId", productId)
                .when()
                .get("/products/{productId}")
                .then()
                .statusCode(404)
                .body("message", equalTo("The product was not found."));
    }

    private String createProductAndGetId() {
        ProductRequest payload = IProductStub.buildProductRequest();

        return given()
                    .body(payload)
                .when()
                    .post("/products")
                .then()
                    .statusCode(201)
                    .extract()
                    .path("id");
    }
}
