package com.carlosfu.connotative.dao;

import java.util.List;

import com.carlosfu.connotative.bean.ComicCartoon;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import net.paoding.rose.jade.annotation.SQLParam;

@DAO
public interface ComicCartoonDao {

	public final static String TABLE_NAME = "ComicCartoon";
	public final static String INSERT_ATTRIBUTES = "(sno,count,url,icon,size,date)";
	public final static String SELECT_ATTRIBUTES = "id,sno,count,url,icon,size as fileSize,date";
	
	
	@SQL("insert into " + TABLE_NAME + INSERT_ATTRIBUTES + " values(:cartoon.sno,:cartoon.count,:cartoon.url,:cartoon.icon,:cartoon.fileSize,now())")
	public int insertComicCartoon(@SQLParam("cartoon")ComicCartoon cartoon);

	
	@SQL("select " + SELECT_ATTRIBUTES + " from " + TABLE_NAME + " where sno=:sno")
	public ComicCartoon getComicCartoonBySno(@SQLParam("sno") int sno);
	
	@SQL("delete from " + TABLE_NAME + " where sno=:sno") 
	public int deleteComicCartoonBySno(@SQLParam("sno") int sno);
	
	@SQL("update " + TABLE_NAME + " set count=:cartoon.count, url=:cartoon.url, icon=:cartoon.icon where sno=:sno") 
	public int updateComicCartoonBySno(@SQLParam("sno") int sno, @SQLParam("cartoon")ComicCartoon cartoon);
	
	@SQL("select " + SELECT_ATTRIBUTES + " from " + TABLE_NAME + " order by sno desc")
	public List<ComicCartoon> getAllComicCartoons();
	
	@SQL("select count(sno)" + SELECT_ATTRIBUTES + " from " + TABLE_NAME)
	public int getAllComicCartoonsCount();
	
}
