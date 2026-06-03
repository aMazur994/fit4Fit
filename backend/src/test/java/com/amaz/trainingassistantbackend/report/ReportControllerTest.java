package com.amaz.trainingassistantbackend.report;

import com.amaz.trainingassistantbackend.controller.ReportController;
import com.amaz.trainingassistantbackend.report.converter.factory.ShelterReportConverterFactory;
import com.amaz.trainingassistantbackend.report.converter.factory.ShelterReportHttpHeadersFactory;
import com.amaz.trainingassistantbackend.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReportControllerTest {
    private ReportService reportService;
    private ReportController reportController;

    private ShelterReport shelterReport;

    @Before
    public void before() {
        shelterReport = new ShelterReport();

        reportService = mock(ReportService.class);
        when(reportService.getShelterReport()).thenReturn(shelterReport);

        reportController = new ReportController(reportService);
    }

    @Test
    public void getShelterReport() {
        ResponseEntity responseEntity = reportController.getShelterReport("web");

        assertEquals(
                ShelterReportConverterFactory.getInstanceByFormat("web").convert(shelterReport),
                responseEntity.getBody()
        );
        assertEquals(
                ShelterReportHttpHeadersFactory.getHttpHeadersByFormat("web"),
                responseEntity.getHeaders()
        );
        assertEquals(
                HttpStatus.OK,
                responseEntity.getStatusCode()
        );
    }
}