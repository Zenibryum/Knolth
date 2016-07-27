package com.zenibryum.knolth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Import {
	private String path;
	
	public Import (String file_path) {
		path = file_path;
	}
	
	public String[] OpenFile() throws IOException {
		//FileReader fr = new FileReader(path);
		//BufferedReader textReader = new BufferedReader(fr);
		BufferedReader textReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path), "UTF-8"));
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			textData[i] = textReader.readLine();
		}
		textReader.close();
		return textData;
	}
	
	int readLines() throws IOException {
		//FileReader file_to_read = new FileReader(path);
		//BufferedReader bf = new BufferedReader(file_to_read);
		BufferedReader bf = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(path), "UTF-8"));
		String aLine;
		int numberOfLines = 0;
		while((aLine = bf.readLine()) != null) {
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}
}
