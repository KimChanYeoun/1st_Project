package com.tj.ex.dto;

import java.sql.Date;

public class QnADto {
	private int qNum;
	private String cId;
	private String aId;
	private String qTitle;
	private String qContent;
	private int qHit;
	private int qGroup;
	private int qStep;
	private int qIndent;
	private String qIp;
	private Date qDate;

	public QnADto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnADto(int qNum, String cId, String aId, String qTitle, String qContent, int qHit, int qGroup, int qStep,
			int qIndent, String qIp, Date qDate) {
		super();
		this.qNum = qNum;
		this.cId = cId;
		this.aId = aId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qHit = qHit;
		this.qGroup = qGroup;
		this.qStep = qStep;
		this.qIndent = qIndent;
		this.qIp = qIp;
		this.qDate = qDate;
	}

	public int getqNum() {
		return qNum;
	}

	public void setqNum(int qNum) {
		this.qNum = qNum;
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

	public String getqTitle() {
		return qTitle;
	}

	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}

	public String getqContent() {
		return qContent;
	}

	public void setqContent(String qContent) {
		this.qContent = qContent;
	}

	public int getqHit() {
		return qHit;
	}

	public void setqHit(int qHit) {
		this.qHit = qHit;
	}

	public int getqGroup() {
		return qGroup;
	}

	public void setqGroup(int qGroup) {
		this.qGroup = qGroup;
	}

	public int getqStep() {
		return qStep;
	}

	public void setqStep(int qStep) {
		this.qStep = qStep;
	}

	public int getqIndent() {
		return qIndent;
	}

	public void setqIndent(int qIndent) {
		this.qIndent = qIndent;
	}

	public String getqIp() {
		return qIp;
	}

	public void setqIp(String qIp) {
		this.qIp = qIp;
	}

	public Date getqDate() {
		return qDate;
	}

	public void setqDate(Date qDate) {
		this.qDate = qDate;
	}

	@Override
	public String toString() {
		return "QnADto [qNum=" + qNum + ", cId=" + cId + ", aId=" + aId + ", qTitle=" + qTitle + ", qContent="
				+ qContent + ", qHit=" + qHit + ", qGroup=" + qGroup + ", qStep=" + qStep + ", qIndent=" + qIndent
				+ ", qIp=" + qIp + ", qDate=" + qDate + "]";
	}

}
