package com.carlosfu.connotative.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carlosfu.connotative.bean.ComicCartoon;
import com.carlosfu.connotative.dao.ComicCartoonDao;

import java.util.ArrayList;
import java.util.List;


@Component
public class ComicCartoonService {

	@Autowired
	private ComicCartoonDao comicCartoonDao;
	
	/**
	 * 插入某个漫画
	 * @param cartoon
	 * @return
	 */
	public int insertComicCartoon(ComicCartoon cartoon){
		int result = 0;
		try {
			result = comicCartoonDao.insertComicCartoon(cartoon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取所有漫画
	 * @return
	 */
	public List<ComicCartoon> getAllComicCartoons(){
		try {
			return comicCartoonDao.getAllComicCartoons();
		} catch (Exception e) {
			System.out.println("从db中取出all comic cartoons 存在异常");
			return new ArrayList<ComicCartoon>();
		}
	}
	
	/**
	 * 获取序号为sno的漫画
	 * @param sno
	 * @return
	 */
	public ComicCartoon getComicCartoonBySno(int sno){
		try {
			return comicCartoonDao.getComicCartoonBySno(sno);
		} catch (Exception e) {
			System.out.println("获取漫画" + sno + "失败");
			return new ComicCartoon();
		}
	}
	
	/**
	 * 删除序号为sno的漫画
	 * @param sno
	 * @return
	 */
	public int deleteComicCartoon(int sno){
		int result = 0;
		try {
			result = comicCartoonDao.deleteComicCartoonBySno(sno);
		} catch (Exception e) {
			System.out.println("删除漫画" + sno + "失败");
			return result;
		}
		return result;
	}
}
