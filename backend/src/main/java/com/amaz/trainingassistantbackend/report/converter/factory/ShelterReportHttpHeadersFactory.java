package com.amaz.trainingassistantbackend.report.converter.factory;

import org.springframework.http.HttpHeaders;

public class ShelterReportHttpHeadersFactory {
    public static HttpHeaders getHttpHeadersByFormat(String format) {
        HttpHeaders httpHeaders = new HttpHeaders();

        switch(format) {
            case "web":
                break;
            case "pdf":
                httpHeaders.set("Content-Disposition", "attachment;filename=shelter_report.pdf");
                httpHeaders.set("Content-Type", "application/pdf");
                break;
            case "csv":
                httpHeaders.set("Content-Disposition", "attachment;filename=shelter_report.csv");
                httpHeaders.set("Content-Type", "text/csv");
                break;
            default:
                throw new IllegalArgumentException("Illegal report format - " + format);
        }

        return httpHeaders;
    }
}
