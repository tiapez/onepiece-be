package com.op.be.usercard.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GetCardListDetails {

	public static void main(String[] args) throws Exception {

		String data = readFileAsString("E:\\OnePieceProject\\New Rep\\onepiece\\st02 raw.txt");

		String setname = "-Worst Generation- \\[ST-02\\]";
		String setnametrunc = setname.substring(0, setname.length() - 11);
		String n = "ST02";
		System.out.println(setnametrunc);

		data = data.replace("\\,", "\\$");
		data = data.replace("'", "\\\'");
		data = data.replace("Trigger[Trigger]", "/Trigger/");
		data = data.replace("Cost", ",");
		data = data.replace("Life", ",");
		data = data.replace(" ,", " Life");
		data = data.replace("Counter", ",");
		data = data.replace("\\[,\\]", "/Counter/");
		data = data.replace("Power", ",");
		data = data.replace("Color", ",");
		data = data.replace("Type", ",");
		data = data.replace("Effect", ",Effect");
		data = data.replace("Attribute", ",");
		data = data.replace(", ", ",");
		data = data.replace(" | ", "\r\n");
		data = data.replace(n, "");
		data = data.replace("CHARACTER", "Character,");
		data = data.replace("EVENT", "Event,");
		data = data.replace("LEADER", "Leader,");
		data = data.replace("STAGE", "Stage,");
		data = data.replace("SR\r", ",Super Rare,");
		data = data.replace("R\r", ",Rare,");
		data = data.replace("SEC\r", ",Rare Secret,");
		data = data.replace("UC\r", ",Uncommon,");
		data = data.replace("C\r", ",Common,");
		data = data.replace("L\r", ",Leader,");
		data = data.replace("Card Set(s)", ",3");
//		    data = data.replace("\r\n", "");
//		    data = data.replace("\n\r", "");
//		    data = data.replace("\r", "");
//		    data = data.replace("\n", "");
		data = data.replace(setname, setnametrunc);
		data = data.replace("➀", "/1/");
		data = data.replace("①", "/1/");
		data = data.replace("➁", "/2/");
		data = data.replace("③", "/3/");
		data = data.replace("➃", "/4/");
		data = data.replace(" ➀ ", "/1/");
		data = data.replace(" ① ", "/1/");
		data = data.replace(" ➁ ", "/2/");
		data = data.replace(" ③ ", "/3/");
		data = data.replace(" ➃ ", "/4/");
		// data = data.replace("-ROMANCE DAWN- \\[OP-01\\]", ")\r\n insert into card
		// (number,rarity,card-type,name,cost,role,power,counter,color,type,effect,set)
		// values(");

//		    data = data.replace("OP-01", "");
//		    data = data.replace("\\[\\]", "");
		data = data.replace("\\[DON!! x1\\]", "/DON!! x1/");
		data = data.replace("\\[DON!! x2\\]", "/DON!! x2/");
		data = data.replace("\\[DON!! x3\\]", "/DON!! x3/");
		data = data.replace("\\[Your Turn\\]", "/Your Turn/");
		data = data.replace("\\[Activate: Main\\]", "/Activate: Main/");
		data = data.replace("\\[On Play\\]", "/On Play/");
		data = data.replace("\\[On K.O.\\]", "/On K.O./");
		data = data.replace("\\[When Attacking\\]", "/When Attacking/");
		data = data.replace("\\[On Opponent Turn\\]", "/On Opponent Turn/");
		data = data.replace("\\[On Block\\]", "/On Block/");
		data = data.replace("\\[Blocker\\]", "/Blocker/");
		data = data.replace("\\[Rush\\]", "/Rush/");
		data = data.replace("\\[Counter\\]", "/Counter/");
		data = data.replace("\\[Main\\]", "/Main/");
		data = data.replace("\\[Banish\\]", "/Banish/");
		data = data.replace("\\[Double Attack\\]", "/Double Attack/");
		data = data.replace("\\[Once Per Turn\\]", "/Once Per Turn/");
		data = data.replace("\\[End of Your Turn\\]", "/End of Your Turn/");
		data = data.replace("\\[Opponent\\\' Turn\\]", "/Opponent\\\' Turn/");
		data = data.replace("DON!! −1", "\\[DON!! −1\\]");
		data = data.replace("DON!! −2", "\\[DON!! −2\\]");
		data = data.replace("DON!! −3", "\\[DON!! −3\\]");
		data = data.replace("DON!! −4", "\\[DON!! −4\\]");
		data = data.replace("DON!! −5", "\\[DON!! −5\\]");
		data = data.replace("DON!! −6", "\\[DON!! −6\\]");
		data = data.replace("-", " ");
		data = data.replace(" -", " ");
		data = data.replace("−", "-");

		String[] asd = data.split(",");
		String rowsValue = "";
		for (String string : asd) {

			if (string.length() < 6) {
				string = string.replace("\r\n", "");
				string = string.replace("\n\r", "");
				string = string.replace("\r", "");
				string = string.replace("\n", "");
			} else {
				if (string.substring(0, 6).equals("Effect")) {
					string = string.replace("\r\n", "@");
					string = string.replace("\n\r", "@");
					string = string.replace("\r", "@");
					string = string.replace("\n", "@");
					string = string.replace("Effect@", "");
				} else {
					string = string.replace("\r\n", "");
					string = string.replace("\n\r", "");
					string = string.replace("\r", "");
					string = string.replace("\n", "");
				}

			}

			if (string.substring(0, (string.length() / 2))
					.equals((string.substring((string.length() / 2), (string.length()))))) {
				string = string.substring(0, string.length() / 2);
			}

			// l'if potrebe essere staccato
			if (string.length() < 3
					&& (string.equals("") || string.equals(" ") || string.equals("  ") || string.equals("   "))) {
				string = "0";
			}

			rowsValue = rowsValue.concat(string + ",");

		}
		rowsValue = rowsValue.replace(" " + setnametrunc.substring(1, setnametrunc.length()) + " ", "\r\n");

		String[] rows = rowsValue.split("\r\n");
		for (int i = 1; i < rows.length; i++) {
			String number1 = rows[i].split(",")[0].replace(" ", "");
			String number2 = rows[i - 1].split(",")[0].replace(" ", "");
			if (number1.equals(number2)) {
				String s1 = number1.concat("a,");
				String s2 = number2;
				for (int j = 1; j < rows[i].split(",").length; j++) {
					s1 = s1.concat(rows[i].split(",")[j] + ",");
					s2 = s2.concat(rows[i - 1].split(",")[j] + ",");
				}
				rows[i] = s1;
				rows[i - 1] = s2;
			}
		}
		for (String string : rows) {
			System.out.println(string);
		}
	}

	public static String readFileAsString(String fileName) throws IOException {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

}
