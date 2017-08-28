package com.refri.model;

public class CookingDTO {
	String cooking;
	String classin;
	String subClass;
	
	@Override
	public String toString() {
		return "CookingDTO [cooking=" + cooking + ", classin=" + classin + ", subclass=" + subClass + "]";
	}
	
	public CookingDTO() {
	}
	public CookingDTO(String cooking, String classin, String subclass) {
		this.cooking = cooking;
		this.classin = classin;
		this.subClass = subclass;
	}
	public String getCooking() {
		return cooking;
	}

	public void setCooking(String cooking) {
		this.cooking = cooking;
	}

	public String getClassin() {
		return classin;
	}

	public void setClassin(String classin) {
		this.classin = classin;
	}

	public String getSubClass() {
		return subClass;
	}

	public void setSubclass(String subclass) {
		this.subClass = subclass;
	}

	
	
	
}
