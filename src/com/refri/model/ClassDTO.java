package com.refri.model;

public class ClassDTO {
	String classin;
	String subClass;
	
	@Override
	public String toString() {
		return "ClassDTO [ 대분류 = " + classin + ", 중분류 = " + subClass + " ]";
	}
	public ClassDTO() {
	}
	public ClassDTO(String classin, String subClass) {
		this.classin = classin;
		this.subClass = subClass;
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

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}
		
	
}
