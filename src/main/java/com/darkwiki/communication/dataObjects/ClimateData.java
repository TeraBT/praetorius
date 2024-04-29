package com.darkwiki.communication.dataObjects;

public record ClimateData(
        double temperature,
        double humidity,
        double pressure
) {}
