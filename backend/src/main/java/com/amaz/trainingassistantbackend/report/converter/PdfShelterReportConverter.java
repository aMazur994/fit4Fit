package com.amaz.trainingassistantbackend.report.converter;

import com.amaz.trainingassistantbackend.report.ShelterReport;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@EqualsAndHashCode
public class PdfShelterReportConverter implements ShelterReportConverter<byte[]> {
    @Override
    public byte[] convert(ShelterReport shelterReport) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, byteArrayOutputStream);
            pdf.open();

            PdfPTable animalsTable = new PdfPTable(ShelterReport.getReportHeaders().size());
            addHeader(animalsTable);
            //addRows(animalsTable, shelterReport.getAnimals());

            pdf.add(animalsTable);
            addAdditionalData(pdf, shelterReport);

            pdf.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    private void addHeader(PdfPTable table) {
        ShelterReport.getReportHeaders()
                .forEach(cell -> {
                    PdfPCell headerCell = new PdfPCell();

                    headerCell.setBackgroundColor(Color.LIGHT_GRAY);
                    headerCell.setBorderWidth(2);
                    headerCell.setPhrase(new Phrase(cell));

                    table.addCell(headerCell);
                });
    }

    /*private void addRows(PdfPTable table, List<Animal> animals) {
        DateTimeFormatter formatter = ShelterReport.getDateTimeFormatter();
        int no = 1;
        for(Animal animal : animals) {
            table.addCell(Integer.toString(no));
            table.addCell(animal.getName());
            table.addCell(animal.getGender().toString());
            table.addCell(Integer.toString(animal.getAge()));
            table.addCell(animal.getDateOfArrival().format(formatter));
            no++;
        }
    }*/

    private void addAdditionalData(Document pdf, ShelterReport shelterReport) {
        pdf.add(new Paragraph(shelterReport.getOccupancyMessage()));
        pdf.add(new Paragraph(shelterReport.getShelterStatus().toString()));
    }
}
