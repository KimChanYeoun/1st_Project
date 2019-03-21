package com.tj.ex.dto;

public class AdminDto {
	private String aId;
	private String aPw;
	private String aName;
	private String aPoint;

	public AdminDto() {
	}

	public AdminDto(String aId, String aPw, String aName, String aPoint) {
		super();
		this.aId = aId;
		this.aPw = aPw;
		this.aName = aName;
		this.aPoint = aPoint;
	}

	public String getaId() {
		return aId;
	}

	public void setaId(String aId) {
		this.aId = aId;
	}

	public String getaPw() {
		return aPw;
	}

	public void setaPw(String aPw) {
		this.aPw = aPw;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaPoint() {
		return aPoint;
	}

	public void setaPoint(String aPoint) {
		this.aPoint = aPoint;
	}

	@Override
	public String toString() {
		return "AdminDto [aId=" + aId + ", aPw=" + aPw + ", aName=" + aName + ", aPoint=" + aPoint + "]";
	}

}
