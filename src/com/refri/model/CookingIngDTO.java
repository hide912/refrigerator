package com.refri.model;

public class CookingIngDTO {
	String cooking;
	String ingredient;
	
	@Override
	public String toString() {
		return "CookingIngDTO [ 요리명 = " + cooking + ", 재료명 = " + ingredient + " ]";
	}
	public CookingIngDTO() {
	}
	public CookingIngDTO(String cooking, String ingredient) {
		this.cooking = cooking;
		this.ingredient = ingredient;
	}
	public String getCooking() {
		return cooking;
	}
	public void setCooking(String cooking) {
		this.cooking = cooking;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	
	
}
