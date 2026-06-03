package com.amaz.trainingassistantbackend.report.converter;

import com.amaz.trainingassistantbackend.report.ShelterReport;
import lombok.EqualsAndHashCode;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode
public class CsvShelterReportConverter implements ShelterReportConverter<String> {
    @Override
    public String convert(ShelterReport shelterReport) {
        String[] reportHeaders = ShelterReport.getReportHeaders().toArray(new String[0]);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             BufferedWriter csvOut = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream));
             CSVPrinter csvPrinter = new CSVPrinter(csvOut, CSVFormat.DEFAULT.withHeader(reportHeaders))) {
            //addRows(csvPrinter, shelterReport.getAnimals());
            addAdditionalData(csvOut, shelterReport);

            csvOut.flush();
            return byteArrayOutputStream.toString();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    /*private void addRows(CSVPrinter csv, List<Animal> animals)
            throws IOException {
        DateTimeFormatter formatter = ShelterReport.getDateTimeFormatter();
        int no = 1;
        for(Animal animal : animals) {
            csv.printRecord(
                    no,
                    animal.getName(),
                    animal.getGender().toString(),
                    animal.getAge(),
                    animal.getDateOfArrival().format(formatter)
            );
            no++;
        }
    }*/

    private void addAdditionalData(BufferedWriter csv, ShelterReport shelterReport)
            throws IOException {
        csv.newLine();
        csv.write(shelterReport.getOccupancyMessage());
        csv.newLine();
        csv.write(shelterReport.getShelterStatus().toString());
    }
}
