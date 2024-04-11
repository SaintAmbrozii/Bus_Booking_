package com.example.busbooking.utils;

import com.example.busbooking.domain.Bus;
import com.example.busbooking.domain.Ticket;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CsvHelper {

    public static boolean checkIfValidFile(MultipartFile file) {
        String fileExension = "CSV";
        if (file.getOriginalFilename().endsWith(fileExension.toLowerCase()) || file.getOriginalFilename().endsWith(fileExension)) {
            return true;
        }
        return false;

    }

    public static List<Ticket> csvToTickets(InputStream is){
        List<Ticket> tickets = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US);
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(is, Charset.forName("windows-1251"));
        parseAllRecords.forEach(record -> {
            Ticket ticket = new Ticket();
            ticket.setBusId(record.getLong("busId"));
            ticket.setId(Long.parseLong(record.getString("id")));
            ticket.setSeat(Integer.parseInt("seat"));
            ticket.setCost(record.getDouble("cost"));
            ticket.setArrTime(LocalDateTime.parse(record.getString("arrival"),formatter));
            ticket.setDepTime(LocalDateTime.parse(record.getString("departure"),formatter));
            tickets.addAll(Collections.singleton(ticket));
        });
        return tickets;
    }
    public static List<Bus> csvToBuses(InputStream is) {
        List<Bus> buses = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.US);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.US);
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setDelimiterDetectionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords = parser.parseAllRecords(is, Charset.forName("windows-1251"));
        parseAllRecords.forEach(record -> {
            Bus bus = new Bus();
            bus.setId(Long.parseLong(record.getString("id")));
            bus.setBus_number(record.getString("number"));
            bus.setFromlocality(record.getString("fromlocality"));
            bus.setTolocality(record.getString("tolocality"));
            bus.setDate(LocalDate.parse(record.getString("date"),dateFormatter));
            bus.setCapacity(Integer.parseInt("capacity"));
            bus.setArr(LocalDateTime.parse(record.getString("arrival"),formatter));
            bus.setDep(LocalDateTime.parse(record.getString("departure"),formatter));
            buses.addAll(Collections.singleton(bus));
        });
        return buses;
    }
}
