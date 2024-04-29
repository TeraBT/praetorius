package com.darkwiki.communication.dataObjects;

import java.util.List;

public record AccessPointCommunicationData(
    String id,
    List<StationData> stationData,
    List<IntervalData> intervalDta
) {}
