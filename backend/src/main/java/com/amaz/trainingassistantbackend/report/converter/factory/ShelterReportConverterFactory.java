package com.amaz.trainingassistantbackend.report.converter.factory;

import com.amaz.trainingassistantbackend.report.converter.CsvShelterReportConverter;
import com.amaz.trainingassistantbackend.report.converter.PdfShelterReportConverter;
import com.amaz.trainingassistantbackend.report.converter.ShelterReportConverter;
import com.amaz.trainingassistantbackend.report.converter.WebShelterReportConverter;

public class ShelterReportConverterFactory {
    public static ShelterReportConverter<?> getInstanceByFormat(String format) {
        switch(format) {
            case "web":
                return new WebShelterReportConverter();
            case "pdf":
                return new PdfShelterReportConverter();
            case "csv":
                return new CsvShelterReportConverter();
            default:
                throw new IllegalArgumentException("Illegal report format - " + format);
        }
    }
}
