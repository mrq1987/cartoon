package com.carlosfu.connotative.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.carlosfu.connotative.bean.ComicCartoon;
import com.carlosfu.connotative.service.CacheService;

@LoginRequired
public class HelloController {
	
	@Autowired
	private CacheService service;
	
	public String world(){
		return "@world";
	}
	
	public String test(){
		List<ComicCartoon> list = service.getCartoons();
		return "@" + list.size();
	}
	
	
	
}
