package com.tj.ex.dto;

import java.sql.Date;

public class GalleryDto {
	private int gNum;
	private String cId;
	private String gTitle;
	private String gContent;
	private String gfileName;
	private int gHit;
	private int gGroup;
	private int gStep;
	private int gIndent;
	private String gIp;
	private Date gDate;

	public GalleryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GalleryDto(int gNum, String cId, String gTitle, String gContent, String gfileName, int gHit, int gGroup,
			int gStep, int gIndent, String gIp, Date gDate) {
		super();
		this.gNum = gNum;
		this.cId = cId;
		this.gTitle = gTitle;
		this.gContent = gContent;
		this.gfileName = gfileName;
		this.gHit = gHit;
		this.gGroup = gGroup;
		this.gStep = gStep;
		this.gIndent = gIndent;
		this.gIp = gIp;
		this.gDate = gDate;
	}

	public int getgNum() {
		return gNum;
	}

	public void setgNum(int gNum) {
		this.gNum = gNum;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getgTitle() {
		return gTitle;
	}

	public void setgTitle(String gTitle) {
		this.gTitle = gTitle;
	}

	public String getgContent() {
		return gContent;
	}

	public void setgContent(String gContent) {
		this.gContent = gContent;
	}

	public String getGfileName() {
		return gfileName;
	}

	public void setGfileName(String gfileName) {
		this.gfileName = gfileName;
	}

	public int getgHit() {
		return gHit;
	}

	public void setgHit(int gHit) {
		this.gHit = gHit;
	}

	public int getgGroup() {
		return gGroup;
	}

	public void setgGroup(int gGroup) {
		this.gGroup = gGroup;
	}

	public int getgStep() {
		return gStep;
	}

	public void setgStep(int gStep) {
		this.gStep = gStep;
	}

	public int getgIndent() {
		return gIndent;
	}

	public void setgIndent(int gIndent) {
		this.gIndent = gIndent;
	}

	public String getgIp() {
		return gIp;
	}

	public void setgIp(String gIp) {
		this.gIp = gIp;
	}

	public Date getgDate() {
		return gDate;
	}

	public void setgDate(Date gDate) {
		this.gDate = gDate;
	}

	@Override
	public String toString() {
		return "GalleryDto [gNum=" + gNum + ", cId=" + cId + ", gTitle=" + gTitle + ", gContent=" + gContent
				+ ", gfileName=" + gfileName + ", gHit=" + gHit + ", gGroup=" + gGroup + ", gStep=" + gStep
				+ ", gIndent=" + gIndent + ", gIp=" + gIp + ", gDate=" + gDate + "]";
	}

}
