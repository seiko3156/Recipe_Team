package com.ezenb1.recipe.dto;

public class IngredientVO {
	
	private Integer rnum; // 레시피 게시물 번호
	private String  iname; // 재료 이름
	private String  quantity; // 수량? 
	private Integer price; // 재료 가격?
	
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public String getIname() {
		return iname;
	}
	public void setIname(String iname) {
		this.iname = iname;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
