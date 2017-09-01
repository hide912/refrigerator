package com.refri.model;

public class CookingIngDTO {
	String cooking;
	String ingredient;
	int recipenum;
	
	
	
	@Override
	public String toString() {
		return "CookingIngDTO [ cooking = " + cooking + ", ingredient = " + ingredient + ", recipenum = " + recipenum + " ]";
	}

	public CookingIngDTO() {
	
	}

	
	
	public CookingIngDTO(String cooking, int recipenum) {
		this.cooking = cooking;
		this.recipenum = recipenum;
	}

	public CookingIngDTO(String cooking, String ingredient, int recipenum) {
		this.cooking = cooking;
		this.ingredient = ingredient;
		this.recipenum = recipenum;
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

	public int getRecipenum() {
		return recipenum;
	}

	public void setRecipenum(int recipenum) {
		this.recipenum = recipenum;
	}
	
	
	

}
