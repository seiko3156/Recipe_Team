package com.ezenb1.recipe.dto;

import java.sql.Timestamp;

public class QnaVO {

	private Integer qseq;
	private String id;
	private String qsubject;
	private Timestamp qnadate;
	private String qcontent;
	private String secret;
	private String replyQna;
	private Integer rep;
	private String qnapass;
	
	
	public String getQnapass() {
		return qnapass;
	}
	public void setQnapass(String qnapass) {
		this.qnapass = qnapass;
	}
	public Integer getQseq() {
		return qseq;
	}
	public void setQseq(Integer qseq) {
		this.qseq = qseq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQsubject() {
		return qsubject;
	}
	public void setQsubject(String qsubject) {
		this.qsubject = qsubject;
	}
	public Timestamp getQnadate() {
		return qnadate;
	}
	public void setQnadate(Timestamp qnadate) {
		this.qnadate = qnadate;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public String getReplyQna() {
		return replyQna;
	}
	public void setReplyQna(String replyQna) {
		this.replyQna = replyQna;
	}
	public Integer getRep() {
		return rep;
	}
	public void setRep(Integer rep) {
		this.rep = rep;
	}
	
	
	
	
}
