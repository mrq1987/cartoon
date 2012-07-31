package com.carlosfu.connotative.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.carlosfu.connotative.bean.ComicCartoon;

public class JsonUtil {
	
	public static String comicListToJsonArr(List<ComicCartoon> comicCartoons){
		JSONArray comicCartoonsArr = new JSONArray();
        JSONObject obj;

        if (comicCartoons != null) {
        	for(ComicCartoon comicCartoon : comicCartoons){
        		obj = comicCartoon.build();
        		comicCartoonsArr.put(obj);
        	}
        } else {
            return "[]";
        }
        return comicCartoonsArr.toString();
	}
	
}
