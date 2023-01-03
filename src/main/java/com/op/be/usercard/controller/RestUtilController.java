package com.op.be.usercard.controller;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.op.be.usercard.exception.CryptException;
import com.op.be.usercard.service.ExcellService;

@RestController
@RequestMapping("api/excel")
public class RestUtilController {

	@Autowired
	ExcellService excellService;
	
	@PostMapping("/importCardList")
	public void importCardList(@RequestParam("nick") String nick,@RequestParam("file") MultipartFile file) throws IOException, CryptException{
		excellService.readExcel(nick,"OP01", (FileInputStream) file.getInputStream());
	}
}
