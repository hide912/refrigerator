package com.refri.model;

import java.sql.Date;

public class InventoryDTO {
	String ingredient;
	Date importdate;
	int stock;
	String unit;
	Date outputdate;
	String location;
	public InventoryDTO(){}
	public InventoryDTO(String ingredient, Date importdate, int stock, String unit, Date outputdate, String location) {
		super();
		this.ingredient = ingredient;
		this.importdate = importdate;
		this.stock = stock;
		this.unit = unit;
		this.outputdate = outputdate;
		this.location = location;
	}
	public String getIngredient() {
		return ingredient;
	}
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}
	public Date getImportdate() {
		return importdate;
	}
	public void setImportdate(Date importdate) {
		this.importdate = importdate;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Date getOutputdate() {
		return outputdate;
	}
	public void setOutputdate(Date outputdate) {
		this.outputdate = outputdate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importdate == null) ? 0 : importdate.hashCode());
		result = prime * result + ((ingredient == null) ? 0 : ingredient.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((outputdate == null) ? 0 : outputdate.hashCode());
		result = prime * result + stock;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryDTO other = (InventoryDTO) obj;
		if (importdate == null) {
			if (other.importdate != null)
				return false;
		} else if (!importdate.equals(other.importdate))
			return false;
		if (ingredient == null) {
			if (other.ingredient != null)
				return false;
		} else if (!ingredient.equals(other.ingredient))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (outputdate == null) {
			if (other.outputdate != null)
				return false;
		} else if (!outputdate.equals(other.outputdate))
			return false;
		if (stock != other.stock)
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[재료=").append(ingredient).append(", 반입기간=").append(importdate)
				.append(", 재고=").append(stock).append(", 단위=").append(unit).append(", 유효기간=")
				.append(outputdate).append(", 냉장고 위치=").append(location).append("]");
		return builder.toString();
	}
}
