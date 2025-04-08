package com.extia.easyshopper.application.dto.response;

import com.extia.easyshopper.domain.enums.ProductCategory;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        ProductCategory category,
        String imageUrl
) {
}
