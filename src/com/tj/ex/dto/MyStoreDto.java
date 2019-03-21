package com.tj.ex.dto;

public class MyStoreDto {
	private int sItem;
	private int iNum;
	private String cId;
	private String iPictrue;
	private String iName;

	public MyStoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyStoreDto(int sItem, int iNum, String cId, String iPictrue, String iName) {
		super();
		this.sItem = sItem;
		this.iNum = iNum;
		this.cId = cId;
		this.iPictrue = iPictrue;
		this.iName = iName;
	}

	public int getsItem() {
		return sItem;
	}

	public void setsItem(int sItem) {
		this.sItem = sItem;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getiPictrue() {
		return iPictrue;
	}

	public void setiPictrue(String iPictrue) {
		this.iPictrue = iPictrue;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	@Override
	public String toString() {
		return "MyStoreDto [sItem=" + sItem + ", iNum=" + iNum + ", cId=" + cId + ", iPictrue=" + iPictrue + ", iName="
				+ iName + "]";
	}

}
