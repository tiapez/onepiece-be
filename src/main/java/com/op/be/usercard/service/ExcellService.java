package com.op.be.usercard.service;

import java.io.File;
import java.io.FileInputStream;

public interface ExcellService {

	void readExcel(String nick, String set, FileInputStream file2);

}
