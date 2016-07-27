package com.zenibryum.knolth;

import java.io.IOException;

public class Configs{
	public static String[] Config;
	public static void init() {
		String file_name = "C:/Users/Home/Desktop/Forge/src/main/resources/assets/knolth/textures/gui/config.txt";
		
		try{
			Import file = new Import(file_name);
			Config = file.OpenFile();
		} catch(IOException e) {
			//System.out.println(e.getMessage());
		}
	}
}