package com.extia.easyshopper.infrastructure.repository.product;

import com.extia.easyshopper.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, String> {}