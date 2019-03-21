package com.tj.ex.dto;

import java.sql.Date;

public class CustomerDto {
	private String cId;
	private String cPw;
	private String cName;
	private String cPhoto;
	private Date cBirth;
	private String cTel;
	private String cEmail;
	private String cGender;
	private String cAddr;
	private Date cDate;
	private int cPoint;

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(String cId, String cPw, String cName, String cPhoto, Date cBirth, String cTel, String cEmail,
			String cGender, String cAddr, Date cDate, int cPoint) {
		super();
		this.cId = cId;
		this.cPw = cPw;
		this.cName = cName;
		this.cPhoto = cPhoto;
		this.cBirth = cBirth;
		this.cTel = cTel;
		this.cEmail = cEmail;
		this.cGender = cGender;
		this.cAddr = cAddr;
		this.cDate = cDate;
		this.cPoint = cPoint;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcPw() {
		return cPw;
	}

	public void setcPw(String cPw) {
		this.cPw = cPw;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcPhoto() {
		return cPhoto;
	}

	public void setcPhoto(String cPhoto) {
		this.cPhoto = cPhoto;
	}

	public Date getcBirth() {
		return cBirth;
	}

	public void setcBirth(Date cBirth) {
		this.cBirth = cBirth;
	}

	public String getcTel() {
		return cTel;
	}

	public void setcTel(String cTel) {
		this.cTel = cTel;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcGender() {
		return cGender;
	}

	public void setcGender(String cGender) {
		this.cGender = cGender;
	}

	public String getcAddr() {
		return cAddr;
	}

	public void setcAddr(String cAddr) {
		this.cAddr = cAddr;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public int getcPoint() {
		return cPoint;
	}

	public void setcPoint(int cPoint) {
		this.cPoint = cPoint;
	}

	@Override
	public String toString() {
		return "CustomerDto [cId=" + cId + ", cPw=" + cPw + ", cName=" + cName + ", cPhoto=" + cPhoto + ", cBirth="
				+ cBirth + ", cTel=" + cTel + ", cEmail=" + cEmail + ", cGender=" + cGender + ", cAddr=" + cAddr
				+ ", cDate=" + cDate + ", cPoint=" + cPoint + "]";
	}

}
