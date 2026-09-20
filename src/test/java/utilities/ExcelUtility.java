package utilities;

import io.cucumber.java.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelUtility {

    public static ArrayList<ArrayList<String>> getData(String path, String sheetName, int numberOfColumns) {
        ArrayList<ArrayList<String>> table = new ArrayList<>();
        try (FileInputStream input = new FileInputStream(path);
             Workbook workbook = WorkbookFactory.create(input)) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new IllegalArgumentException("Missing sheet: " + sheetName);
            DataFormatter formatter = new DataFormatter();
            for (Row sourceRow : sheet) {
                ArrayList<String> row = new ArrayList<>();
                for (int column = 0; column < numberOfColumns; column++) {
                    row.add(formatter.formatCellValue(sourceRow.getCell(column)));
                }
                table.add(row);
            }
        } catch (IOException exception) {
            throw new java.io.UncheckedIOException("Cannot read Excel data: " + path, exception);
        }
        return table;
    }
    public static void writeToExcel(String path, Scenario scenario, String browserName) {
        writeResult(path, scenario.getName(), scenario.getStatus().toString(), browserName);
    }

    // Serializes the entire read-modify-write operation within this test JVM.
    static synchronized void writeResult(String path, String scenarioName, String status, String browserName) {
        File file = new File(path);
        File parent = file.getAbsoluteFile().getParentFile();
        if (!parent.isDirectory() && !parent.mkdirs() && !parent.isDirectory()) {
            throw new IllegalStateException("Cannot create results directory: " + parent);
        }
        try (Workbook workbook = file.exists() ? readWorkbook(file) : new XSSFWorkbook()) {
            Sheet sheet = workbook.getNumberOfSheets() == 0
                    ? workbook.createSheet("Scenario Results") : workbook.getSheetAt(0);
            int rowIndex = sheet.getPhysicalNumberOfRows() == 0 ? 0 : sheet.getLastRowNum() + 1;
            Row row = sheet.createRow(rowIndex);
            row.createCell(0).setCellValue(scenarioName);
            row.createCell(1).setCellValue(status);
            row.createCell(2).setCellValue(browserName);
            try (FileOutputStream output = new FileOutputStream(file)) {
                workbook.write(output);
            }
        } catch (IOException exception) {
            throw new java.io.UncheckedIOException("Cannot write scenario results to " + path, exception);
        }
    }

    private static Workbook readWorkbook(File file) throws IOException {
        try (FileInputStream input = new FileInputStream(file)) {
            return WorkbookFactory.create(input);
        }
    }
}
