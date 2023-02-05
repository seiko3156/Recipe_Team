package com.ezenb1.recipe.dto;

import java.sql.Timestamp;

public class ReplyVO {

	
	private Integer replyseq;
	private String id;
	private Integer rnum;
	private String content;
	private Timestamp replydate;
	
	public Integer getReplyseq() {
		return replyseq;
	}
	public void setReplyseq(Integer replyseq) {
		this.replyseq = replyseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getReplydate() {
		return replydate;
	}
	public void setReplydate(Timestamp replydate) {
		this.replydate = replydate;
	}
	
	
}
