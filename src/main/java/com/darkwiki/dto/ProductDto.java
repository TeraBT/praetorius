package com.darkwiki.dto;

import com.darkwiki.model.ProductType;

import java.util.Set;

public record ProductDto(

        Long id,
        String name,
        ProductType productType
) {}