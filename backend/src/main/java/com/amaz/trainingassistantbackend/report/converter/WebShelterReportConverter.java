package com.amaz.trainingassistantbackend.report.converter;

import com.amaz.trainingassistantbackend.report.ShelterReport;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class WebShelterReportConverter implements ShelterReportConverter<ShelterReport> {
    @Override
    public ShelterReport convert(ShelterReport shelterReport) {
        return shelterReport;
    }
}
