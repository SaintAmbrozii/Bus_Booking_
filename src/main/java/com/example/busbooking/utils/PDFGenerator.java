package com.example.busbooking.utils;

import com.example.busbooking.domain.Ticket;
import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.util.List;


public class PDFGenerator {



    @SneakyThrows
    public void ticketsGenerate(List<Ticket>tickets, HttpServletResponse response)  {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph("Список пользователей", fontTitle);

        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(8);

        table.setWidthPercentage(100f);
        table.setWidths(new float[] { 1.0f,1.0f,1.0f,1.0f,1.0f});
        table.setSpacingBefore(8);

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(CMYKColor.MAGENTA);
        cell.setPadding(8);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);



        cell.setPhrase(new Phrase("Number", font));
        cell.setPhrase(new Phrase("Seat", font));
        cell.setPhrase(new Phrase("Username", font));
        cell.setPhrase(new Phrase("Surname", font));
        cell.setPhrase(new Phrase("Patronymic", font));

        for (Ticket ticket : tickets) {
            table.addCell(ticket.getNumber());
            table.addCell(ticket.getSeat().toString());



        }

        document.add(table);


        document.close();
    }

}
