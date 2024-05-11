package com.darkwiki.dto;

import java.util.Set;

public record RegionDto(
        Long id,
        String name,
        Set<VendorDto> vendorDtoSet

) {}