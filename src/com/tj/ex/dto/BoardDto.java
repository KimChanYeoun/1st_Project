package com.tj.ex.dto;

import java.sql.Date;

public class BoardDto {
	private int bNum;
	private String cId;
	private String bTitle;
	private String bContent;
	private int bHit;
	private int bGroup;
	private int bStep;
	private int bIndent;
	private String bIp;
	private Date bDate;

	public BoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardDto(int bNum, String cId, String bTitle, String bContent, int bHit, int bGroup, int bStep, int bIndent,
			String bIp, Date bDate) {
		super();
		this.bNum = bNum;
		this.cId = cId;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bStep = bStep;
		this.bIndent = bIndent;
		this.bIp = bIp;
		this.bDate = bDate;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbStep() {
		return bStep;
	}

	public void setbStep(int bStep) {
		this.bStep = bStep;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}

	public String getbIp() {
		return bIp;
	}

	public void setbIp(String bIp) {
		this.bIp = bIp;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	@Override
	public String toString() {
		return "BoardDto [bNum=" + bNum + ", cId=" + cId + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bHit="
				+ bHit + ", bGroup=" + bGroup + ", bStep=" + bStep + ", bIndent=" + bIndent + ", bIp=" + bIp
				+ ", bDate=" + bDate + "]";
	}

}
