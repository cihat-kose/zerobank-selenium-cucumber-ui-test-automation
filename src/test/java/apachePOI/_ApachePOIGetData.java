package apachePOI;

import org.apache.poi.ss.usermodel.*;

import java.io.*;


public class _ApachePOIGetData {

    public static void main(String[] args) throws IOException {

        String path = "src/test/java/apachePOI/resource/_2DArray.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0); //   Sheet sheet= workbook.getSheet("Sheet1");

//        Row row= sheet.getRow(0);
//        System.out.println("row.getCell(1) = " + row.getCell(1));
//        Cell cell= row.getCell(0);
//        System.out.println("cell = " + cell);

        for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
            for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                System.out.print(sheet.getRow(i).getCell(j) + " ");
            }
            System.out.println();
        }
    }
}
