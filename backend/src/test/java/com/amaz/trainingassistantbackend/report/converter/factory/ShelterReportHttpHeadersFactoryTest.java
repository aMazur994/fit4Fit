package com.amaz.trainingassistantbackend.report.converter.factory;

import org.junit.Test;
import org.springframework.http.HttpHeaders;

import java.util.Objects;

import static org.junit.Assert.*;

public class ShelterReportHttpHeadersFactoryTest {
    @Test
    public void getHttpHeadersByWebFormat() {
        assertEquals(new HttpHeaders(), ShelterReportHttpHeadersFactory.getHttpHeadersByFormat("web"));
    }

    @Test
    public void getHttpHeadersByPdfFormat() {
        HttpHeaders pdf = ShelterReportHttpHeadersFactory.getHttpHeadersByFormat("pdf");

        assertEquals("attachment;filename=shelter_report.pdf", Objects.requireNonNull(pdf.get("Content-Disposition")).get(0));
        assertEquals("application/pdf", Objects.requireNonNull(pdf.get("Content-Type")).get(0));
    }

    @Test
    public void getHttpHeadersByCsvFormat() {
        HttpHeaders csv = ShelterReportHttpHeadersFactory.getHttpHeadersByFormat("csv");

        assertEquals("attachment;filename=shelter_report.csv", Objects.requireNonNull(csv.get("Content-Disposition")).get(0));
        assertEquals("text/csv", Objects.requireNonNull(csv.get("Content-Type")).get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getHttpHeadersByIllegalFormat() {
        ShelterReportHttpHeadersFactory.getHttpHeadersByFormat("abc");
    }
}