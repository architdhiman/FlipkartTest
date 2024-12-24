package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<Map<String, String>> getTestData(String sheetName) throws IOException {

		String basePath = System.getProperty("user.dir");

		String relativePath = "testdata.xlsx";

		File file1 = new File(basePath, relativePath);
		String filePath = file1.getAbsolutePath();

		List<Map<String, String>> testData = new ArrayList<>();
		FileInputStream file = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet(sheetName);

		Row headerRow = sheet.getRow(0);
		int rowCount = sheet.getPhysicalNumberOfRows();

		for (int i = 1; i < rowCount; i++) {
			Row currentRow = sheet.getRow(i);
			Map<String, String> rowMap = new HashMap<>();
			for (int j = 0; j < headerRow.getLastCellNum(); j++) {
				String key = headerRow.getCell(j).getStringCellValue();
				String value = currentRow.getCell(j).getStringCellValue();
				rowMap.put(key, value);
			}
			testData.add(rowMap);
		}

		workbook.close();
		file.close();

		return testData;
	}

}
