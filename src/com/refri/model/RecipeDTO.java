package com.refri.model;

public class RecipeDTO {

	
	String cooking;
	String subname;
	int recipenum;
	int ordernum;
	String ordercook;
	String userid;
	
	
	
	public RecipeDTO(){
		
	}
	
	
	
	
	public String getCooking() {
		return cooking;
	}
	public void setCooking(String cooking) {
		this.cooking = cooking;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public int getRecipenum() {
		return recipenum;
	}
	public void setRecipenum(int recipenum) {
		this.recipenum = recipenum;
	}
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public String getOrdercook() {
		return ordercook;
	}
	public void setOrdercook(String ordercook) {
		this.ordercook = ordercook;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipeDTO [cooking=").append(cooking).append(", subname=").append(subname)
				.append(", recipenum=").append(recipenum).append(", ordernum=").append(ordernum).append(", ordercook=")
				.append(ordercook).append(", userid=").append(userid).append("]");
		return builder.toString();
	}
	public RecipeDTO(String cooking, String subname, int recipenum, int ordernum, String ordercook, String userid) {
		super();
		this.cooking = cooking;
		this.subname = subname;
		this.recipenum = recipenum;
		this.ordernum = ordernum;
		this.ordercook = ordercook;
		this.userid = userid;
	}
	
	
	
	
}
