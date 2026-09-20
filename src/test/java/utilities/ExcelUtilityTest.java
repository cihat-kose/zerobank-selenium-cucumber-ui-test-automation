package utilities;

import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ExcelUtilityTest {
    @Test
    public void concurrentAppendsPreserveEveryScenarioAndBrowser() throws Exception {
        Files.createDirectories(Path.of("target"));
        Path output = Files.createTempDirectory(Path.of("target"), "excel-test-")
                .resolve("nested/results.xlsx");
        ExcelUtility.writeResult(output.toString(), "existing", "PASSED", "edge");
        var jobs = new ArrayList<Callable<Void>>();
        for (int i = 0; i < 20; i++) {
            final int number = i;
            jobs.add(() -> {
                ExcelUtility.writeResult(output.toString(), "scenario-" + number, "PASSED",
                        number % 2 == 0 ? "edge" : "firefox");
                return null;
            });
        }
        try (var executor = Executors.newFixedThreadPool(4)) {
            for (var result : executor.invokeAll(jobs)) result.get();
        }
        try (var input = Files.newInputStream(output); var workbook = WorkbookFactory.create(input)) {
            var sheet = workbook.getSheetAt(0);
            Assert.assertEquals(sheet.getPhysicalNumberOfRows(), 21);
            var names = new HashSet<String>();
            for (var row : sheet) {
                String name = row.getCell(0).getStringCellValue();
                Assert.assertTrue(names.add(name), "Duplicate result: " + name);
                Assert.assertEquals(row.getCell(1).getStringCellValue(), "PASSED");
                String expectedBrowser = name.equals("existing") ||
                        Integer.parseInt(name.substring("scenario-".length())) % 2 == 0 ? "edge" : "firefox";
                Assert.assertEquals(row.getCell(2).getStringCellValue(), expectedBrowser);
            }
        }
    }

}
