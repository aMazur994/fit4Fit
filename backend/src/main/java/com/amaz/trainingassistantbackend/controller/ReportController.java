package com.amaz.trainingassistantbackend.controller;

import com.amaz.trainingassistantbackend.service.ReportService;
import com.amaz.trainingassistantbackend.report.ShelterReport;
import com.amaz.trainingassistantbackend.report.converter.ShelterReportConverter;
import com.amaz.trainingassistantbackend.report.converter.factory.ShelterReportConverterFactory;
import com.amaz.trainingassistantbackend.report.converter.factory.ShelterReportHttpHeadersFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<?> getShelterReport(@RequestParam(value = "format", defaultValue = "web", required = false) String format) {
        ShelterReportConverter<?> shelterReportConverter = ShelterReportConverterFactory.getInstanceByFormat(format);
        HttpHeaders httpHeaders = ShelterReportHttpHeadersFactory.getHttpHeadersByFormat(format);
        ShelterReport shelterReport = reportService.getShelterReport();

        return new ResponseEntity<>(
                shelterReportConverter.convert(shelterReport),
                httpHeaders,
                HttpStatus.OK
        );
    }
}
