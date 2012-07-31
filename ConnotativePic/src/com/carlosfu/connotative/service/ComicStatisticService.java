package com.carlosfu.connotative.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;


public class ComicStatisticService {
	private static ComicStatisticService instance = new ComicStatisticService();
	private ComicStatisticService(){
		
	}
	
	public static ComicStatisticService getInstance(){
		return instance;
	}

	public void statisticComic(String uuid,Integer sno,String version, HttpServletRequest request) {
//		System.out.println("--------------------");
//		String ip = request.getRemoteHost();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		System.out.println(uuid + " " + today +  " " + sno +  " " + version);
	}
	
	
}
