package com.amaz.trainingassistantbackend.report.converter;

import com.amaz.trainingassistantbackend.report.ShelterReport;

public interface ShelterReportConverter<T> {
    T convert(ShelterReport shelterReport);
}
