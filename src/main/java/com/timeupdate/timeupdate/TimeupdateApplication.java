package com.timeupdate.timeupdate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;

import java.time.LocalTime;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TimeupdateApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// DataFormatter df = new DataFormatter();
		String filename = "/home/mrdeepesh/Downloads/report.xlsx";
		try (FileInputStream file = new FileInputStream(new File(filename))) {
			Workbook workbook = WorkbookFactory.create(file);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			Row row;
			System.out.println("last row number is=========" + sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				int empId = (int) row.getCell(0).getNumericCellValue();
				// String empName = row.getCell(1).getStringCellValue();
				// String empEmail = row.getCell(2).getStringCellValue();
				LocalTime empName = row.getCell(1).getLocalDateTimeCellValue().toLocalTime();

				LocalTime empEmail = row.getCell(2).getLocalDateTimeCellValue().toLocalTime();
				// DateFormat dff = new SimpleDateFormat("HH:mm:ss");

				String sql = "insert into employee " + "values('" + empId + "','" + empName + "','" + empEmail + "')";
				System.out.println(sql);

			}

		}
	}

}
