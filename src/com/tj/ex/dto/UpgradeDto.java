package com.tj.ex.dto;

import java.sql.Date;

public class UpgradeDto {
	private int uNum;
	private String aId;
	private String uTitle;
	private String uContent;
	private Date uDate;

	public UpgradeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpgradeDto(int uNum, String aId, String uTitle, String uContent, Date uDate) {
		super();
		this.uNum = uNum;
		this.aId = aId;
		this.uTitle = uTitle;
		this.uContent = uContent;
		this.uDate = uDate;
	}

	public int getuNum() {
		return uNum;
	}

	public void setuNum(int uNum) {
		this.uNum = uNum;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getuTitle() {
		return uTitle;
	}

	public void setuTitle(String uTitle) {
		this.uTitle = uTitle;
	}

	public String getuContent() {
		return uContent;
	}

	public void setuContent(String uContent) {
		this.uContent = uContent;
	}

	public Date getuDate() {
		return uDate;
	}

	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}

	@Override
	public String toString() {
		return "UpgradeDto [uNum=" + uNum + ", aId=" + aId + ", uTitle=" + uTitle + ", uContent=" + uContent
				+ ", uDate=" + uDate + "]";
	}

}
