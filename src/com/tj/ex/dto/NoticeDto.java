package com.tj.ex.dto;

import java.sql.Date;

public class NoticeDto {
	private int nNum;
	private String aId;
	private String nTitle;
	private String nContent;
	private Date nDate;

	public NoticeDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeDto(int nNum, String aId, String nTitle, String nContent, Date nDate) {
		super();
		this.nNum = nNum;
		this.aId = aId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nDate = nDate;
	}

	public int getnNum() {
		return nNum;
	}

	public void setnNum(int nNum) {
		this.nNum = nNum;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	@Override
	public String toString() {
		return "NoticeDto [nNum=" + nNum + ", aId=" + aId + ", nTitle=" + nTitle + ", nContent=" + nContent + ", nDate="
				+ nDate + "]";
	}

}
