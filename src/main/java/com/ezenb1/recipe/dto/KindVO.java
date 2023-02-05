package com.ezenb1.recipe.dto;

public class KindVO {

	private Integer type; // 종류별?
	private Integer rec; // 추천별
	private Integer ing; // 재료별 
	private Integer theme; // 테마별
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRec() {
		return rec;
	}
	public void setRec(Integer rec) {
		this.rec = rec;
	}
	public Integer getIng() {
		return ing;
	}
	public void setIng(Integer ing) {
		this.ing = ing;
	}
	public Integer getTheme() {
		return theme;
	}
	public void setTheme(Integer theme) {
		this.theme = theme;
	}
	
	
}
