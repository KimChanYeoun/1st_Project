package com.tj.ex.dto;

import java.sql.Date;

public class ErrorboardDto {
	private int eNum;
	private String cId;
	private String aId;
	private String eTitle;
	private String eContent;
	private String efileName;
	private int eGroup;
	private int eStep;
	private int eIndent;
	private String eIp;
	private Date eDate;

	public ErrorboardDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorboardDto(int eNum, String cId, String aId, String eTitle, String eContent, String efileName, int eGroup,
			int eStep, int eIndent, String eIp, Date eDate) {
		super();
		this.eNum = eNum;
		this.cId = cId;
		this.aId = aId;
		this.eTitle = eTitle;
		this.eContent = eContent;
		this.efileName = efileName;
		this.eGroup = eGroup;
		this.eStep = eStep;
		this.eIndent = eIndent;
		this.eIp = eIp;
		this.eDate = eDate;
	}

	public int geteNum() {
		return eNum;
	}

	public void seteNum(int eNum) {
		this.eNum = eNum;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String geteTitle() {
		return eTitle;
	}

	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}

	public String geteContent() {
		return eContent;
	}

	public void seteContent(String eContent) {
		this.eContent = eContent;
	}

	public String getEfileName() {
		return efileName;
	}

	public void setEfileName(String efileName) {
		this.efileName = efileName;
	}

	public int geteGroup() {
		return eGroup;
	}

	public void seteGroup(int eGroup) {
		this.eGroup = eGroup;
	}

	public int geteStep() {
		return eStep;
	}

	public void seteStep(int eStep) {
		this.eStep = eStep;
	}

	public int geteIndent() {
		return eIndent;
	}

	public void seteIndent(int eIndent) {
		this.eIndent = eIndent;
	}

	public String geteIp() {
		return eIp;
	}

	public void seteIp(String eIp) {
		this.eIp = eIp;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	@Override
	public String toString() {
		return "ErrorboardDto [eNum=" + eNum + ", cId=" + cId + ", aId=" + aId + ", eTitle=" + eTitle + ", eContent="
				+ eContent + ", efileName=" + efileName + ", eGroup=" + eGroup + ", eStep=" + eStep + ", eIndent="
				+ eIndent + ", eIp=" + eIp + ", eDate=" + eDate + "]";
	}

}
