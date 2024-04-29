package com.darkwiki.communication.dataObjects;

public record StationData(
        String id,
        String timestamp,
        ClimateData climateData
) {
}
