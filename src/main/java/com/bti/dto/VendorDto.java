package com.bti.dto;

import java.util.Set;

public record VendorDto(
        Long id,
        String name,
        Set<ProductDto> productDtoSet
) {}
