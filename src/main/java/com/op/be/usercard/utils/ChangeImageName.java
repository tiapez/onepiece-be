package com.op.be.usercard.utils;

import java.io.File;

public class ChangeImageName {

	public static void main(String[] args) {
		File dir = new File("C:\\Users\\mpezzolla\\Desktop\\workspace\\onepiece-fe\\src\\assets\\Img\\OP02");

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().contains("a")) {
				files[i].renameTo(new File(files[i].getPath().replace("a.", ".")));
			}
			if (files[i].getName().contains("b")) {
				files[i].renameTo(new File(files[i].getPath().replace("b.png", "a.png")));
			}
			if (files[i].getName().contains("c")) {
				files[i].renameTo(new File(files[i].getPath().replace("c.png", "b.png")));
			}
		}
	}

}
