package com.op.be.usercard.service;

import java.io.FileInputStream;

import com.op.be.usercard.exception.CryptException;

public interface ExcellService {

	void readExcel(String nick, String set, FileInputStream file2) throws CryptException;

}
