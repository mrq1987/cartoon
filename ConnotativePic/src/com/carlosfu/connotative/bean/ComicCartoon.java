package com.carlosfu.connotative.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class ComicCartoon {
	private int id;
	// 当前集号
	private int sno;
	// 本集漫画数量
	private int count;
	// 漫画zip包地址
	private String url;
	// 漫画封皮
	private String icon;
	// 文件大小，以字节为单位
	private int fileSize;
	// 插入日期
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "ComicCartoon [id=" + id + ", sno=" + sno + ", count=" + count
				+ ", url=" + url + ", icon=" + icon + ", fileSize=" + fileSize
				+ ", date=" + date + "]";
	}

	public JSONObject build() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONObject obj = new JSONObject();
		try {
			obj.put("sno", this.getSno());
			obj.put("count", this.getCount());
			obj.put("url", this.getUrl());
			obj.put("icon", this.getIcon());
			obj.put("fileSize", this.getFileSize());
			obj.put("date", sdf.format(this.getDate()));
		} catch (JSONException e) {
			System.out.println("comic转换json格式存在问题");
			e.printStackTrace();
		}
		return obj;
	}

}
