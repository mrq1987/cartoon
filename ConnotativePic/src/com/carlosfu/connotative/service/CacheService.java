package com.carlosfu.connotative.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carlosfu.connotative.bean.ComicCartoon;
import com.carlosfu.connotative.dao.ComicCartoonDao;

@Component
public class CacheService {
	@Autowired
	private ComicCartoonDao dao;
	//所有漫画得到的list，用作缓存使用
	private List<ComicCartoon> cartoonList;
	//用list组装成map,key是sno
	private Map<Integer, ComicCartoon> cartoonMap;
	
	private static final int RELOAD_DELAY_IN_MINITUE = 1;
	
	private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
	
	/**
	 * spring启动时候加载，并用一个计时器，隔一段时间load一次
	 */
	@PostConstruct
	public void init(){
		load();
		executor.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("----------reload comic cartoons-----------");
				load();
			}
		}, RELOAD_DELAY_IN_MINITUE, TimeUnit.MINUTES);
	}
	
	

	/**
	 * load所有漫画，组装cartoonList,cartoonMap
	 */
	private synchronized void load() {
		List<ComicCartoon> tempList = dao.getAllComicCartoons();
		cartoonList = tempList;
		tempList = null;
		
		Map<Integer, ComicCartoon> tempMap = new HashMap<Integer, ComicCartoon>();
		
		for(ComicCartoon cc : cartoonList){
			int sno = cc.getSno();
			tempMap.put(sno, cc);
		}
		
		cartoonMap = tempMap;
		System.out.println("cartoons size is " + cartoonList.size());
	}

	
	public synchronized List<ComicCartoon> getCartoons() {
		return cartoonList;
	}

	public synchronized Map<Integer, ComicCartoon> getCartoonMap() {
		return cartoonMap;
	}
	
	
}
