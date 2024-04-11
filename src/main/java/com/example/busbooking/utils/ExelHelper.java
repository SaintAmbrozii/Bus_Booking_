package com.example.busbooking.utils;

import com.example.busbooking.domain.Bus;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String[] HEADERs = {"id", "number", "fromLocality", "toLocality", "date", "capacity", "arrival", "departure"};
    public static String BUS_SHEET = "Buses";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Bus> excelToBuses(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(BUS_SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Bus> buses = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Bus bus = new Bus();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            bus.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            bus.setBus_number(currentCell.getStringCellValue());
                            break;

                        case 2:
                            bus.setFromlocality(currentCell.getStringCellValue());
                            break;

                        case 3:
                            bus.setTolocality(currentCell.getStringCellValue());
                            break;
                        case 4:
                            bus.setCapacity(currentCell.getColumnIndex());
                            break;

                        case 5:
                            bus.setDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;

                        case 6:
                            bus.setDep(currentCell.getLocalDateTimeCellValue());
                            break;

                        case 7:
                            bus.setArr(currentCell.getLocalDateTimeCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                buses.add(bus);
            }

            workbook.close();

            return buses;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
