package com.tj.ex.dto;

public class ItemDto {
	private int iNum;
	private String iName;
	private int iPrice;
	private String iPictrue;

	public ItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemDto(int iNum, String iName, int iPrice, String iPictrue) {
		super();
		this.iNum = iNum;
		this.iName = iName;
		this.iPrice = iPrice;
		this.iPictrue = iPictrue;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public int getiPrice() {
		return iPrice;
	}

	public void setiPrice(int iPrice) {
		this.iPrice = iPrice;
	}

	public String getiPictrue() {
		return iPictrue;
	}

	public void setiPictrue(String iPictrue) {
		this.iPictrue = iPictrue;
	}

	@Override
	public String toString() {
		return "ItemDto [iNum=" + iNum + ", iName=" + iName + ", iPrice=" + iPrice + ", iPictrue=" + iPictrue + "]";
	}

}
