package com.refri.model;

public class IngredientSaveDTO {

	String ingredient;

	
	public void IngredientSaveDTO(){
		
	}
	
	public String getIngredient() {
		return ingredient;
	}


	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IngredientSaveDTO [ingredient=").append(ingredient).append("]");
		return builder.toString();
	}

	public IngredientSaveDTO(String ingredient) {
		super();
		this.ingredient = ingredient;
	}
	

}
