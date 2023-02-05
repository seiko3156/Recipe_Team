package com.ezenb1.recipe.dto;

import java.sql.Timestamp;

public class InterestVO {

   private Integer rnum;
   private String id;
   private String interestid;
   private String favoriteid;
   private String subject;
   private String content;
   private String fuseyn;
   private Timestamp indate;
   private Integer Views;
   private String likeyn;
   private Integer interestnum;
   private Integer fnum;
   
   public Integer getRnum() {
      return rnum;
   }
   public void setRnum(Integer rnum) {
      this.rnum = rnum;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getInterestid() {
      return interestid;
   }
   public void setInterestid(String interestid) {
      this.interestid = interestid;
   }
   public String getFavoriteid() {
      return favoriteid;
   }
   public void setFavoriteid(String favoriteid) {
      this.favoriteid = favoriteid;
   }
   public String getSubject() {
      return subject;
   }
   public void setSubject(String subject) {
      this.subject = subject;
   }
   public String getContent() {
      return content;
   }
   public void setContent(String content) {
      this.content = content;
   }
   public String getFuseyn() {
      return fuseyn;
   }
   public void setFuseyn(String fuseyn) {
      this.fuseyn = fuseyn;
   }
   public Timestamp getIndate() {
      return indate;
   }
   public void setIndate(Timestamp indate) {
      this.indate = indate;
   }
   public Integer getViews() {
      return Views;
   }
   public void setViews(Integer views) {
      Views = views;
   }
   public String getLikeyn() {
      return likeyn;
   }
   public void setLikeyn(String likeyn) {
      this.likeyn = likeyn;
   }
   public Integer getInterestnum() {
      return interestnum;
   }
   public void setInterestnum(Integer interestnum) {
      this.interestnum = interestnum;
   }
   public Integer getFnum() {
      return fnum;
   }
   public void setFnum(Integer fnum) {
      this.fnum = fnum;
   }
   
   
   
}