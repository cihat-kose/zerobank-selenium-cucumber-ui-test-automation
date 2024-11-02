package apachePOI;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.*;


public class _ApachePOINewExcelWrite {

    public static void main(String[] args) throws IOException {

        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet= workbook.createSheet("New Sheet");

        Row row = sheet.createRow(2);
        Cell cell = row.createCell(3);
        cell.setCellValue("New cell has created.");

        String path ="src/test/java/apachePOI/resource/_ApachePOINewExcel.xlsx";
        FileOutputStream fileOutputStream=new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();

        System.out.println("The process is over");
    }
}
