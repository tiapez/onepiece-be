package com.op.be.usercard.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.op.be.usercard.model.dto.UserCardExcel;

public class readExcel {
	public static void main(String[] args) throws Exception {
		String NAME = "C:\\Users\\matti\\Desktop\\RomanceDawn.xlsx";
		List<UserCardExcel> list = new ArrayList<>();
		String set = "OP01";

		try (FileInputStream file = new FileInputStream(new File(NAME)); Workbook workbook = new XSSFWorkbook(file);) {
			DataFormatter dataFormatter = new DataFormatter();
			Iterator<Sheet> sheets = workbook.sheetIterator();
			while (sheets.hasNext()) {
				Sheet sh = sheets.next();
				Iterator<Row> iterator = sh.iterator();
				while (iterator.hasNext()) {
					Row row = iterator.next();
					Iterator<Cell> cellIterator = row.iterator();

					if (row.getRowNum() > 0) {
						int i = 0;
						UserCardExcel userCard = new UserCardExcel();
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							String cellValue = dataFormatter.formatCellValue(cell);
							switch (i) {
							case 0:
								userCard.setNumber(cellValue);
								break;
							case 1:
								if (cellValue.contains("(parallel)")) {
									userCard.setNumber(userCard.getNumber().concat("a"));
								}
								if (cellValue.contains("(manga)")) {
									userCard.setNumber(userCard.getNumber().concat("b"));
								}
								break;
							case 2:
								userCard.setCond(Integer.parseInt(cellValue));
								break;
							case 3:
								if (cellValue.toLowerCase().contains("jap")) {
									userCard.setCond(userCard.getCond() + 6);
								}
								break;
							case 4:
								userCard.setQty(Integer.parseInt(cellValue));
								break;
							default:
								break;
							}
							i++;
						}
						if (userCard.getQty() > 0) {
							userCard.setSet(set);
							list.add(userCard);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(list.toString());
	}
}
