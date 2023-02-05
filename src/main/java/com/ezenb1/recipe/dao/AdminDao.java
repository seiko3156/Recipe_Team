package com.ezenb1.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenb1.recipe.dto.AdminVO;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.dto.ProcessimageVO;
import com.ezenb1.recipe.dto.QnaVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.dto.ReplyVO;
import com.ezenb1.recipe.util.Dbman;
import com.ezenb1.recipe.util.Paging;

public class AdminDao {
	   
   private AdminDao() {}
   private static AdminDao instance = new AdminDao();
   public static AdminDao getInstance() { return instance;}
   
   Connection con = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   public int getAllCount(String tableName, String fieldName, String key) {
      
      int count = 0;
      con = Dbman.getConnection();
      String sql = "select count(*) as cnt from " + tableName + " where " + fieldName + " like '%'||?||'%'";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         rs = pstmt.executeQuery();
         if(rs.next())
            count = rs.getInt("cnt");
      } catch (SQLException e) { e.printStackTrace();
      } finally { Dbman.close(con, pstmt, rs);
      }
      return count;
   }
   public ArrayList<MembersVO> selectMember(Paging paging, String key) {
      ArrayList<MembersVO> list = new ArrayList<MembersVO>();
      String sql = " select * from ("
            + " select * from ("
            + " select rownum as rn, m.* from "
            + "(( select * from members where name like '%'||?||'%' order by indate desc ) m)"
            + " ) where rn>=?"
            + " ) where rn<=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setInt(2,  paging.getStartNum() );
         pstmt.setInt(3,  paging.getEndNum() );
         rs = pstmt.executeQuery();
         while (rs.next()) {
            MembersVO mvo = new MembersVO();
            mvo.setId(rs.getString("id"));
            mvo.setPwd(rs.getString("pwd"));
            mvo.setName(rs.getString("name"));
            mvo.setPhone(rs.getString("phone"));
            mvo.setEmail(rs.getString("email"));
            mvo.setNick(rs.getString("nick"));
            mvo.setAddress1(rs.getString("address1"));
            mvo.setZip_num(rs.getString("zip_num"));
            mvo.setIndate(rs.getTimestamp("indate"));
            mvo.setImg(rs.getString("img"));
            mvo.setUseyn(rs.getString("useyn"));
            list.add(mvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   
   public ArrayList<ReplyVO> selectReply(Paging paging, String key) {
      ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
      String sql = " select * from ("
            + " select * from ("
            + " select rownum as rn, r.* from "
            + "(( select * from reply where id like '%'||?||'%' order by replydate desc ) r)"
            + " ) where rn>=?"
            + " ) where rn<=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setInt(2,  paging.getStartNum() );
         pstmt.setInt(3,  paging.getEndNum() );
         rs = pstmt.executeQuery();
         while (rs.next()) {
            ReplyVO rvo = new ReplyVO();
            rvo.setReplyseq(rs.getInt("replyseq"));   
            rvo.setId(rs.getString("id"));   
            rvo.setRnum(rs.getInt("rnum"));
            rvo.setContent(rs.getString("content"));
            rvo.setReplydate(rs.getTimestamp("replydate"));         
            list.add(rvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   public void deleteReply(int replyseq) {
      
      String sql = "delete from reply where replyseq=?";
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1, replyseq);
            pstmt.executeUpdate();
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); }  
      
   }
   public MembersVO getMember(String id) {
      MembersVO mvo = null;
      String sql = "select * from members where id=?";
      con = Dbman.getConnection();
      try {           
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, id);          
            rs=pstmt.executeQuery();
            if(rs.next()) {
               mvo = new MembersVO();
               mvo.setId(rs.getString("id"));
               mvo.setPwd(rs.getString("pwd"));
               mvo.setName(rs.getString("name"));
               mvo.setPhone(rs.getString("phone"));
               mvo.setEmail(rs.getString("email"));
               mvo.setNick(rs.getString("nick"));
               mvo.setAddress1(rs.getString("address1"));
               mvo.setAddress2(rs.getString("address2"));
               mvo.setZip_num(rs.getString("zip_num"));
               mvo.setIndate(rs.getTimestamp("indate"));
               mvo.setImg(rs.getString("img"));               
            }
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); } 
      
      return mvo;
   }
   public void sleepMember(String useyn,String sleep) {
      String sql="";
      if(useyn.equals("Y")) {
      sql = "update members set useyn ='N' where id=?";
      }else {
      sql = "update members set useyn ='Y' where id=?";   
      }
      
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, sleep);
            pstmt.executeUpdate();
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); } 
      
   }
   public String selectUseyn(String sleep) {
      String sql = "select useyn from members where id=?";
      //select useyn from members where id='scott';
      String useyn = "";
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, sleep);
            rs= pstmt.executeQuery();
            if(rs.next()) {
               useyn=rs.getString("useyn");
            }
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); } 
      return useyn;
   }
   public int getAdminQnaCount(String tableName, String fieldName1, String fieldName2, String key) {
      
      int count = 0;
      con = Dbman.getConnection();
      String sql = "select count(*) as cnt from " + tableName + " where " + fieldName1 + " like '%'||?||'%' or "+fieldName2+" like '%'||?||'%'";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setString(2,  key);
         rs = pstmt.executeQuery();
         if(rs.next())
            count = rs.getInt("cnt");
      } catch (SQLException e) { e.printStackTrace();
      } finally { Dbman.close(con, pstmt, rs);
      }
      return count;
   }
   public ArrayList<QnaVO> selectQna(Paging paging, String key) {
      
      ArrayList<QnaVO> list = new ArrayList<QnaVO>();
      String sql = " select * from ("
            + " select * from ("
            + " select rownum as rn, q.* from "
            + "(( select * from qna where qsubject like '%'||?||'%' or qcontent like '%'||?||'%' order by qseq desc ) q)"
            + " ) where rn>=?"
            + " ) where rn<=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setString(2,  key);
         pstmt.setInt(3,  paging.getStartNum() );
         pstmt.setInt(4,  paging.getEndNum() );
         rs = pstmt.executeQuery();
         while (rs.next()) {
            QnaVO qvo = new QnaVO();
            qvo.setQseq(rs.getInt("qseq"));
            qvo.setId(rs.getString("id"));
            qvo.setQsubject(rs.getString("qsubject"));
            qvo.setQnadate(rs.getTimestamp("qnadate"));
            qvo.setQcontent(rs.getString("qcontent"));
            qvo.setSecret(rs.getString("secret"));
            qvo.setReplyQna(rs.getString("replyQna"));
            qvo.setRep(rs.getInt("rep"));                  
            list.add(qvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   public void deleteQna(String dqseq) {
      
      String sql = "delete from qna where qseq=?";
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, dqseq);
            pstmt.executeUpdate();
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); }  
      
   }
   public QnaVO getAdminQna(String qseq) {
      
      QnaVO qvo = null;
      String sql = "select * from qna where qseq=?";
      con = Dbman.getConnection();
      try {           
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, qseq);          
            rs=pstmt.executeQuery();
            if(rs.next()) {
               qvo = new QnaVO();
               qvo.setQseq(rs.getInt("qseq"));
               qvo.setId(rs.getString("id"));
               qvo.setQsubject(rs.getString("qsubject"));
               qvo.setQnadate(rs.getTimestamp("qnadate"));
               qvo.setQcontent(rs.getString("qcontent"));
               qvo.setSecret(rs.getString("secret"));
               qvo.setReplyQna(rs.getString("replyQna"));
               qvo.setRep(rs.getInt("rep"));
                          
            }
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); } 
      
      return qvo;
   }
   public ArrayList<QnaVO> getQnaReplyList(String qseq) {
      ArrayList<QnaVO> list = new ArrayList<QnaVO>();
      String sql = "select * from qna where qseq=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  qseq);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            QnaVO qvo = new QnaVO();
            qvo.setRep(rs.getInt("rep"));
            qvo.setReplyQna(rs.getString("replyQna"));
            list.add(qvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   public void updateReplyQna(int qseq, String replyQna) {
      String sql ="update qna set replyQna='"+ replyQna+"', rep=2 where qseq=?";
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setInt(1,  qseq);
            pstmt.executeUpdate();
            
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); }
      
   }
   
   public AdminVO getAdmin(String id) {
      AdminVO avo = null;
      con = Dbman.getConnection();
      String sql = "select * from admins where aid=?";
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            avo = new AdminVO();
            avo.setAdminId(rs.getString("aid"));
            avo.setAdminPwd(rs.getString("pwd"));
            avo.setAdminPhone(rs.getString("phone"));
         }
      } catch (SQLException e) { e.printStackTrace();
      } finally { Dbman.close(con, pstmt, rs);
      }
      return avo;
   }
   public int getAdminRecipeCount(String tableName, String fieldName1, String fieldName2, String key) {
      
      int count = 0;
      String sql="";
      con = Dbman.getConnection();
      if(tableName.equals("recipe_page_view")) {
         sql = "select count(*) as cnt from " + tableName + " where (" + fieldName1 + " like '%'||?||'%' or "+fieldName2+" like '%'||?||'%')"
               + " and rec='1' ";
      }
      else{
         sql = "select count(*) as cnt from " + tableName + " where " + fieldName1 + " like '%'||?||'%' or "+fieldName2+" like '%'||?||'%'";
      }
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setString(2,  key);
         rs = pstmt.executeQuery();
         if(rs.next())
            count = rs.getInt("cnt");
      } catch (SQLException e) { e.printStackTrace();
      } finally { Dbman.close(con, pstmt, rs);
      }
      return count;
   }
   public ArrayList<RecipeVO> selectRecipe(Paging paging, String key) {
      ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
      String sql = " select * from ("
            + " select * from ("
            + " select rownum as rn, r.* from "
            + "(( select * from recipe_page_view where subject like '%'||?||'%' or content like '%'||?||'%' order by rnum desc ) r)"
            + " ) where rn>=?"
            + " ) where rn<=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setString(2,  key);
         pstmt.setInt(3,  paging.getStartNum() );
         pstmt.setInt(4,  paging.getEndNum() );
         rs = pstmt.executeQuery();
         while (rs.next()) {
            RecipeVO rvo = new RecipeVO();
            rvo.setContent(rs.getString("content"));   
            rvo.setId(rs.getString("id"));
            rvo.setIndate(rs.getTimestamp("indate"));
            rvo.setIng(rs.getInt("ing"));
            rvo.setLikes(rs.getInt("likes"));
            rvo.setRec(rs.getInt("rec"));
            rvo.setReport(rs.getInt("report"));
            rvo.setRnum(rs.getInt("rnum"));
            rvo.setSubject(rs.getString("subject"));
            rvo.setTheme(rs.getInt("theme"));
            rvo.setThumbnail(rs.getString("thumbnail"));
            rvo.setTime(rs.getInt("time"));
            rvo.setType(rs.getInt("type"));
            rvo.setViews(rs.getInt("views"));
            list.add(rvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   
   public void ChangeRecommend(String rnum, int rec) {
      String sql="";
      if(rec==1) {
      sql ="update recipe_page set rec=0 where rnum=?";
      }else {
      sql ="update recipe_page set rec=1 where rnum=?";   
      }
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1,  rnum);
            pstmt.executeUpdate();
            
      } catch (Exception e) { e.printStackTrace();
       } finally { Dbman.close(con, pstmt, rs); }
      
   }
   public int getRec(String rnum) {
      int rec=0;
      String sql = " select * from recipe_page where rnum=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  rnum);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            rec=(rs.getInt("rec"));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return rec;
   }


   
   public void deleteRecipe(String rnum) {
      
      String sql = "delete from recipe where rnum=?";
      con = Dbman.getConnection();
      try {
            pstmt = con.prepareStatement(sql); 
            pstmt.setString(1, rnum);
            pstmt.executeUpdate();         
      } catch (Exception e) { 
            try {
               sql = "delete from recipeTag where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            } catch (SQLException e1) {
               e1.printStackTrace();
            }try {
               sql = "delete from processimg where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            }catch(Exception e2){
               e2.printStackTrace();
            }try {
               sql = "delete from recipe_page where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            }catch(Exception e3){
               e3.printStackTrace();
            }try {
               sql = "delete from favorite where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            }catch(Exception e4){
               e4.printStackTrace();
            }try {
               sql = "delete from interest where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            }catch(Exception e5){
               e5.printStackTrace();
            }try {
               sql = "delete from reply where rnum=?";
               pstmt = con.prepareStatement(sql);
               pstmt.setString(1, rnum);
               pstmt.executeUpdate();
            }catch(Exception e6){
               e6.printStackTrace();
            }
       } finally { 
          try {
            sql = "delete from recipe where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rnum);
            pstmt.executeUpdate();;
         } catch (SQLException e1) {
            e1.printStackTrace();
          } 
          Dbman.close(con, pstmt, rs); 
      
   }
   
      
   }
   public ArrayList<RecipeVO> selectPickRecipe(Paging paging, String key) {
      ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
      String sql = " select * from ("
            + " select * from ("
            + " select rownum as rn, r.* from "
            + "(( select * from recipe_page_view where (subject like '%'||?||'%' or content like '%'||?||'%') and rec='1' order by rnum desc ) r)"
            + " ) where rn>=?"
            + " ) where rn<=?";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1,  key);
         pstmt.setString(2,  key);
         pstmt.setInt(3,  paging.getStartNum() );
         pstmt.setInt(4,  paging.getEndNum() );
         rs = pstmt.executeQuery();
         while (rs.next()) {
            RecipeVO rvo = new RecipeVO();
            rvo.setContent(rs.getString("content"));   
            rvo.setId(rs.getString("id"));
            rvo.setIndate(rs.getTimestamp("indate"));
            rvo.setIng(rs.getInt("ing"));
            rvo.setLikes(rs.getInt("likes"));
            rvo.setRec(rs.getInt("rec"));
            rvo.setReport(rs.getInt("report"));
            rvo.setRnum(rs.getInt("rnum"));
            rvo.setSubject(rs.getString("subject"));
            rvo.setTheme(rs.getInt("theme"));
            rvo.setThumbnail(rs.getString("thumbnail"));
            rvo.setTime(rs.getInt("time"));
            rvo.setType(rs.getInt("type"));
            rvo.setViews(rs.getInt("views"));
            list.add(rvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return list;
   }
   public int getViews() {      
      int viewcnt=0;
      String sql = "select sum(views) \"viewcnt\" from recipe_page";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            viewcnt=(rs.getInt("viewcnt"));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return viewcnt;
   }
   public int getCounts(String column,String nick, String tablename) {
      
      int temp=0;
      String sql = "select count("+column+") as "+nick+" from " + tablename;
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            temp=(rs.getInt(nick));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return temp;
   }
   
   
   public int getadminRec() {
      
      int temp=0;
      String sql = "select count(*) as \"temp\" from recipe_page where rec = 1 ";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            temp=(rs.getInt("temp"));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return temp;
   }
   public int getSleepcnt() {
      
      int temp=0;
      String sql = "select count(*) as \"temp\" from members where useyn= 'N' ";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            temp=(rs.getInt("temp"));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return temp;
   }
   public ArrayList<RecipeVO> bestViewList() {
      ArrayList<RecipeVO> bestViewList = new ArrayList<>();
      RecipeVO rvo =null;
      String sql = "select * from recipe_page_view WHERE ROWNUM <= 3 ORDER BY views desc";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            rvo=new RecipeVO();
            rvo.setSubject(rs.getString("subject"));
            rvo.setId(rs.getString("id"));
            rvo.setViews(rs.getInt("views"));
            rvo.setRnum(rs.getInt("rnum"));
            rvo.setIndate(rs.getTimestamp("indate"));
            bestViewList.add(rvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return bestViewList;
   }
   public ArrayList<ReplyVO> recentReplyList() {
      
      ArrayList<ReplyVO> recentReplyList = new ArrayList<>();
      ReplyVO rpvo =null;
      String sql = "select * from reply ORDER BY replydate desc";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            rpvo=new ReplyVO();
            rpvo.setId(rs.getString("id"));
            rpvo.setRnum(rs.getInt("rnum"));
            rpvo.setContent(rs.getString("content"));
            rpvo.setReplydate(rs.getTimestamp("replydate"));
            
            recentReplyList.add(rpvo);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return recentReplyList;
   }
   public String getQnaUser(String table) {
      
      String temp="";
      String sql = "SELECT id FROM " + table +" GROUP BY id order by count(id) desc";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            temp=(rs.getString("id"));
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return temp;
   }
   public ArrayList<String> getJoinCount() {
      ArrayList<String> joincount = new ArrayList<String>();         
      String sql = "SELECT count(indate) as mem ,TO_CHAR(indate, 'yy/mm/dd')as day FROM members GROUP BY TO_CHAR(indate, 'yy/mm/dd') order by sysdate";
      String temp="";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            temp=(rs.getString("mem"));
            joincount.add(temp);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return joincount;
   }
   public ArrayList<String> getJoinDayCount() {
      ArrayList<String> joindayList = new ArrayList<String>();         
      String sql = "SELECT count(indate) as mem ,TO_CHAR(indate, 'yy/mm/dd')as day FROM members GROUP BY TO_CHAR(indate, 'yy/mm/dd') order by sysdate";
      String temp="";
      con = Dbman.getConnection();
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            temp=(rs.getString("day"));
            joindayList.add(temp);
         }
      } catch (SQLException e) {   e.printStackTrace();
      } finally {  Dbman.close(con, pstmt, rs);  }
      return joindayList;
   }
   
   
}   
