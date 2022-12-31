package com.op.be.usercard.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.op.be.usercard.model.UserCard;
import com.op.be.usercard.model.dto.UserCardExcel;
import com.op.be.usercard.repository.UserCardRepository;
import com.op.be.usercard.service.ExcellService;
import com.op.be.usercard.service.RestService;

@Service
public class ExcellServiceImpl implements ExcellService{

	@Autowired
	UserCardRepository userCardRepository;
	
	@Autowired
	RestService restService;
	
	
	@Override
	public void readExcel(String nickcr,String set,FileInputStream file) {
		List<UserCardExcel> list = getListFromExcel(set,file);
		System.out.println(nickcr);
		String nick = restService.decodenick(nickcr);
		System.out.println(nick);
		db(list,nick,0);
	}
	
	
	
	public List<UserCardExcel> getListFromExcel(String set,FileInputStream file2) {
		List<UserCardExcel> list = new ArrayList<>();

		try ( Workbook workbook = new XSSFWorkbook(file2);) {
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
								System.out.println(cellValue);
								if (cellValue.toLowerCase().contains("(parallel)")) {
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
								if (cellValue.toLowerCase().contains("jap") && userCard.getCond() != 0) {
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
		return list;
	}

	public void db(List<UserCardExcel> list, String nick, int type) {
		
		boolean flag = false;
		for (UserCardExcel uce : list) {
			if(uce.getCond() == 0) {
				try {
					UserCard uc = userCardRepository.findCardUserClassic2(uce.getNumber(), uce.getSet(), nick).get();
					System.out.println(uc.toString());
					if(type == 0) {
						uc.setQty(uc.getQty()+uce.getQty());				
					}else {
						uc.setQty(uce.getQty());
					}
					System.out.println(uc.toString());
					userCardRepository.save(uc);

				}catch(NoSuchElementException e) {
					try {
						userCardRepository.importUserCardClassic(uce.getNumber(), uce.getSet(), nick, uce.getQty());
					}catch(Exception e2) {
					}
				}
			}
		}
	}
	
	
	
	public void readExcel(String set, File file2) {	
	}
}
