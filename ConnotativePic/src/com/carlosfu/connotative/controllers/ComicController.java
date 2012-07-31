package com.carlosfu.connotative.controllers;



import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.carlosfu.connotative.bean.ComicCartoon;
import com.carlosfu.connotative.service.CacheService;
import com.carlosfu.connotative.service.ComicCartoonService;
import com.carlosfu.connotative.service.ComicStatisticService;
import com.carlosfu.connotative.util.JsonUtil;


import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;

public class ComicController {
	@Autowired
	private ComicCartoonService comicCartoonService;
	
	@Autowired
	private CacheService cacheService;
	
	public String downloadComic(Invocation inv, @Param("uuid") String uuid, @Param("sno") Integer sno, @Param("version") String version){
		
		if(null == version){
			version = "1.0";
		}
		ComicStatisticService.getInstance().statisticComic(uuid,sno,version,inv.getRequest());
		//跳转到对应的资源
//		return "r:/WeatherService/comic/test";
		return "r:/ConnotativePic/allpics/" + sno + "/pic.zip";
		//zip比较好 
	}
//	http://localhost/ConnotativePic/comic/addComicCartoon?sno=1&count=20&url=a&icon=b
	public String addComicCartoon(@Param("sno") int sno, @Param("count") int count, @Param("url") String url, @Param("icon") String icon,@Param("size") int size){
		ComicCartoon cc = new ComicCartoon();
		cc.setSno(sno);
		cc.setCount(count);
		cc.setUrl(url);
		cc.setIcon(icon);
		cc.setFileSize(size);
		
		System.out.println(cc.toString());
		
		comicCartoonService.insertComicCartoon(cc);
		return "@result";
	}
	
	/**
	 * 获取序号为sno的漫画的json接口
	 * @param sno
	 * @return
	 */
	public String getOneComicCartoon(@Param("sno") int sno){
		ComicCartoon comicCartoon = cacheService.getCartoonMap().get(sno);
		if(comicCartoon != null){
			JSONObject obj = comicCartoon.build();
			return "@" + obj.toString();
		}else{
			return "@{}";
		}
	}
	
	/**
	 * 获取所有漫画的json接口
	 * @return
	 */
	@Get("")
	public String getAllComicCartoons(){
		List<ComicCartoon> comicCartoons = cacheService.getCartoons();
		
        String jsonArrStr = JsonUtil.comicListToJsonArr(comicCartoons);
        
        return "@" + jsonArrStr;
	}
	
	/**
	 * 获取从startsno序号开始,向上取offset个漫画(sno上升)
	 * @return
	 */
	public String getFromStartSno(@Param("startsno") int startsno, @Param("offset") int offset){
		List<ComicCartoon> comicCartoons = cacheService.getCartoons();
		
		List<ComicCartoon> someCertainComicCartoons = new ArrayList<ComicCartoon>();
		int sum = 0;		
		
		for(ComicCartoon cc : comicCartoons){
			System.out.println(cc);
			System.out.println(cc.getSno());
			if(cc.getSno() >= startsno && sum < offset){
				someCertainComicCartoons.add(cc);
				sum++;
			}
		}
		
		String jsonArrStr = JsonUtil.comicListToJsonArr(someCertainComicCartoons);
		
        return "@" + jsonArrStr; 
	}
	
	/**
	 * 获取从endsno序号开始,向下取offset个漫画(sno向下)
	 * @return
	 */
	public String getFromEndSno(@Param("endsno") int endsno, @Param("offset") int offset){
		List<ComicCartoon> comicCartoons = cacheService.getCartoons();
		
		List<ComicCartoon> someCertainComicCartoons = new ArrayList<ComicCartoon>();
		int sum = 0;		
		
		for(ComicCartoon cc : comicCartoons){
			System.out.println(cc.getSno());
			if(cc.getSno() <= endsno && sum < offset){
				someCertainComicCartoons.add(cc);
				sum++;
			}
		}
		
		String jsonArrStr = JsonUtil.comicListToJsonArr(someCertainComicCartoons);
		
        return "@" + jsonArrStr;
        
        
	}
	
	
	
	
	
	
}
