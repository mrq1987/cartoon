package com.carlosfu.service.test;

import java.io.File;

public class Test {
	public static void main(String[] args) {
		File path = new File("./WebContent/allpics");
		String[] fileNames = path.list();
		for(String name : fileNames){
			System.out.println(name);
		}
	}
}
