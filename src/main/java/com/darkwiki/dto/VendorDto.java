package com.darkwiki.dto;

import com.darkwiki.model.Vendor;

import java.util.Set;

public record VendorDto(
        Long id,
        String name,
        Set<ProductDto> productDtoSet
) {}
