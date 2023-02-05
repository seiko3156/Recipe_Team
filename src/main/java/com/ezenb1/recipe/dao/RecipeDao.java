package com.ezenb1.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenb1.recipe.dto.FavoriteVO;
import com.ezenb1.recipe.dto.InterestVO;
import com.ezenb1.recipe.dto.ProcessimageVO;
import com.ezenb1.recipe.dto.RecipeVO;
import com.ezenb1.recipe.dto.ReplyVO;
import com.ezenb1.recipe.util.Dbman;
import com.ezenb1.recipe.util.Paging;

public class RecipeDao {
	private RecipeDao() {}
	private static RecipeDao itc = new RecipeDao();
	public static RecipeDao getInstance() { return itc; }
	
	Connection con =null;
	PreparedStatement pstmt =null;
	ResultSet rs=null;
	
    // 특정한 아이디로 작성된 게시물만 보여주는 메서드입니다. 마이페이지에서 사용합니다.
    public ArrayList<RecipeVO> selectMyRecipe(String id, Paging paging) {

        ArrayList<RecipeVO> rlist = new ArrayList<RecipeVO>();

        String sql = " select * from ( "
                + " select * from ( "
                + " select rownum as rn, r.*from ((select*from recipe_page_view where id=? order by rnum desc) r)"
                + " ) where rn >=? "
                + " ) where rn <=? ";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setInt(2, paging.getStartNum());
            pstmt.setInt(3, paging.getEndNum());

            rs = pstmt.executeQuery();
            while (rs.next()) {
                RecipeVO rvo = new RecipeVO();
                rvo.setRnum(rs.getInt("rnum"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setId(rs.getString("id"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setTime(rs.getInt("time"));
                rvo.setType(rs.getInt("type"));
                rvo.setViews(rs.getInt("views"));
                rlist.add(rvo);
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

        return rlist;
    }
    
    
    public int insertInterestRecipe(String rnum, String id) {
        int result = 0;
        String sql = "insert into interest (interestnum,rnum , id) values( interestnum.nextVal,?,?)";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(rnum));
            pstmt.setString(2, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

        return result;
    }

    public int insertfavoriteRecipe(String rnum, String id) {
        int result = 0;
        String sql = "insert into favorite (fnum, rnum, id) values( fnum_seq.nextVal,?,?)";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(rnum));
            pstmt.setString(2, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

        return result;
    }

    public ArrayList<RecipeVO> getTypeList() {
        ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
        String sql = "select * from type_page_view";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RecipeVO rvo = new RecipeVO();

                rvo.setRnum(rs.getInt("rnum"));
                rvo.setId(rs.getString("id"));
                rvo.setNick(rs.getString("nick"));
                rvo.setImg(rs.getString("img"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setTime(rs.getInt("time"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setViews(rs.getInt("views"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setType(rs.getInt("type"));
                rvo.setIng(rs.getInt("ing"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setRec(rs.getInt("rec"));
                rvo.setReport(rs.getInt("report"));

                list.add(rvo);
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs);}
        
        return list;
    }

    public ArrayList<RecipeVO> getThemeList() {
        ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
        String sql = "";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                /**
                 * RecipeVO rvo = new RecipeVO();
                 * rvo.setId(sql);
                 * rvo.setContent(sql);
                 * rvo.setIndate(null);
                 * rvo.setIng(null);
                 * rvo.setLikes(null);
                 * rvo.setRec(null);
                 * rvo.setReport(null);
                 * rvo.setRnum(null);
                 * rvo.setSubject(sql);
                 * rvo.setTheme(null);
                 * rvo.setThumbnail(sql);
                 * rvo.setTime(null);
                 * //rvo.setType(null);
                 * rvo.setViews(null);
                 * list.add(rvo);
                 */
            }

        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }
        return list;
    }

    public ArrayList<RecipeVO> getIngList() {
        ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
        String sql = "select * from recipe_page_view";
        con = Dbman.getConnection();

        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RecipeVO rvo = new RecipeVO();
                rvo.setRnum(rs.getInt("rnum"));
                rvo.setId(rs.getString("id"));
                rvo.setNick(rs.getString("nick"));
                rvo.setImg(rs.getString("img"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setTime(rs.getInt("time"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setViews(rs.getInt("views"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setType(rs.getInt("type"));
                rvo.setIng(rs.getInt("ing"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setRec(rs.getInt("rec"));
                rvo.setReport(rs.getInt("report"));

                list.add(rvo);
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

        return list;
    }

    // insertRecipe 한 번에(rvo, pivoList, ingArray, quanArray 매개변수로)
    public void insertRecipeAtOnce(RecipeVO rvo, ArrayList<ProcessimageVO> pivoList, ArrayList<String> ingArray,
            ArrayList<String> quanArray) {
        con = Dbman.getConnection();
        String sql = "insert into recipe(rnum, id, subject, content, thumbnail, time, type, theme)  "
                + "values(recipe_seq.nextVal, ?, ?, ?,?, ?, ?, ?)";
        try {
            // 1. recipe table 삽입
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rvo.getId());
            pstmt.setString(2, rvo.getSubject());
            pstmt.setString(3, rvo.getContent());
            pstmt.setString(4, rvo.getThumbnail());
            pstmt.setInt(5, rvo.getTime());
            pstmt.setInt(6, rvo.getType());
            pstmt.setInt(7, rvo.getTheme());
            pstmt.executeUpdate();

            // 2. 방금 넣은 레시피의 rnum 확인
            sql = "select max(rnum) as maxRnum from recipe";
            int rnum = 0;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rnum = rs.getInt("maxRnum");
            }

            // 3. processImg 테이블 삽입
            sql = "insert into processImg(rnum, iseq, links, description) values(?,?,?,?)";
            int i = 0;
            for (ProcessimageVO pivo : pivoList) {
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.setInt(2, i + 1);
                pstmt.setString(3, pivo.getLinks());
                pstmt.setString(4, pivo.getDescription());
                pstmt.executeUpdate();
                i++;
            }
            // 4. recipe_page 테이블에 레코드 삽입
            sql = "insert into recipe_page(rnum) values(?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();

            // 5. 재료 태그 넣기 (ingTag, recipeTag) (기존 태그가 있는지 확인 후 태그 삽입 - 중복 시 기존 태그 id 불러옴)

            int lastTagId = 0;
            for (i = 0; i < ingArray.size(); i++) {
                sql = "select tag_id as tagId from ingTag where tag=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, ingArray.get(i));
                rs = pstmt.executeQuery();
                if (rs.next()) { // 기존 태그가 존재한다면
                    lastTagId = rs.getInt("tagId"); // 기존 태그 아이디 사용
                } else { // 기존 태그가 없다면
                    sql = "insert into ingTag(tag_id, tag) values(ingTag_seq.nextVal, ?)"; // 이렇게 sql 중첩 가능??
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, ingArray.get(i));
                    pstmt.executeUpdate();

                    sql = "select max(tag_id) as lastTagId from ingTag"; // 방금 삽입한 tag_id 검색
                    pstmt = con.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        lastTagId = rs.getInt("lastTagId");
                    }
                }
                pstmt.close();
                // lastTagId 이용해 recipeTag 테이블에 레코드 추가
                sql = "insert into recipeTag(rnum, tag_id, quantity) values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.setInt(2, lastTagId);
                pstmt.setString(3, quanArray.get(i));
                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

    }

    public int countAll() {
        int count = 0;
        con = Dbman.getConnection();
        String sql = "SELECT COUNT(*) as count FROM recipe_page_view;";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
                System.out.println("countAll에서 실행한 토탈넘버값은" + count);
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }

        return count;
    }

    public int getMyRecipeAllCount(String id) {
        int count = 0;
        con = Dbman.getConnection();
        String sql = "select count(*) as cnt from recipe where id=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cnt");
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }
        
        return count;
    }

    public RecipeVO getDetailView(int rnum) {
        String sql = "select * from recipe_page_view where rnum = ?";
        con = Dbman.getConnection();
        RecipeVO rvo = new RecipeVO();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                rvo.setRnum(rs.getInt("rnum"));
                rvo.setId(rs.getString("id"));
                rvo.setNick(rs.getString("nick"));
                rvo.setImg(rs.getString("img"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setTime(rs.getInt("time"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setViews(rs.getInt("views"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setType(rs.getInt("type"));
                rvo.setIng(rs.getInt("ing"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setRec(rs.getInt("rec"));
                rvo.setReport(rs.getInt("report"));
            }
        } catch (SQLException e) { e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }
        
        return rvo;
    }

    public ArrayList<ProcessimageVO> getDetailProcessImages(String rnum) {
        ArrayList<ProcessimageVO> list = new ArrayList<>();
        String sql = "select * from processImg where rnum = ? order by iseq";
        con = Dbman.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rnum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ProcessimageVO pvo = new ProcessimageVO();
                pvo.setIseq(rs.getInt("iseq"));
                pvo.setLinks(rs.getString("links"));
                pvo.setDescription(rs.getString("description"));
                list.add(pvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return list;
    }

    public String getWriterID(int rnum) {
        String id = "";
        String sql = "select id from recipe where rnum = ?";
        con = Dbman.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

        return id;
    }

    public ArrayList<ReplyVO> getRecipeReply(int rnum) {
        ArrayList<ReplyVO> replyList = new ArrayList<>();
        String sql = "select * from reply where rnum = ?";
        con = Dbman.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ReplyVO replyvo = new ReplyVO();
                replyvo.setRnum(rs.getInt("rnum"));
                replyvo.setId(rs.getString("id"));
                replyvo.setContent(rs.getString("content"));
                replyvo.setReplydate(rs.getTimestamp("replydate"));
                replyvo.setReplyseq(rs.getInt("replyseq"));
                replyList.add(replyvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return replyList;
    }
    
    public int getAllCount(String whatIsthis) {
        // whatIsthis 가 무엇인지에 따라서 조회할 페이지 뷰가 달라집니다.
    	int count = 0;
        
        con = Dbman.getConnection();
        String sql = "select count(*) as cnt from " + whatIsthis + "_page_view";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next())
                count = rs.getInt("cnt");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return count;
    }
	
    public ArrayList<RecipeVO> selectKindAll() {
        ArrayList<RecipeVO> list = new ArrayList<RecipeVO>();
        con = Dbman.getConnection();
        String sql = "select * from recipe_page_view";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RecipeVO rvo = new RecipeVO();
                rvo.setRnum(rs.getInt("rnum"));
                rvo.setId(rs.getString("id"));
                rvo.setNick(rs.getString("nick"));
                rvo.setImg(rs.getString("img"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setTime(rs.getInt("time"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setViews(rs.getInt("views"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setType(rs.getInt("type"));
                rvo.setIng(rs.getInt("ing"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setRec(rs.getInt("rec"));
                rvo.setReport(rs.getInt("report"));

                list.add(rvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

        return list;
    }

    public ArrayList<RecipeVO> selectKindAll(Paging paging, String whatIsthis) {
        ArrayList<RecipeVO> list = new ArrayList<>();
        con = Dbman.getConnection();
        String sql = " select * from ("
                + " select * from ("
                + " select rownum as rn, r.*from "
                + " ((select * from "+whatIsthis+"_page_view) r)"
                + " ) where rn>=?"
                + " ) where rn<=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, paging.getStartNum());
            pstmt.setInt(2, paging.getEndNum());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                RecipeVO rvo = new RecipeVO();
                rvo.setRnum(rs.getInt("rnum"));
                rvo.setId(rs.getString("id"));
                rvo.setNick(rs.getString("nick"));
                rvo.setImg(rs.getString("img"));
                rvo.setSubject(rs.getString("subject"));
                rvo.setContent(rs.getString("content"));
                rvo.setIndate(rs.getTimestamp("indate"));
                rvo.setTime(rs.getInt("time"));
                rvo.setThumbnail(rs.getString("thumbnail"));
                rvo.setViews(rs.getInt("views"));
                rvo.setLikes(rs.getInt("likes"));
                rvo.setType(rs.getInt("type"));
                rvo.setIng(rs.getInt("ing"));
                rvo.setTheme(rs.getInt("theme"));
                rvo.setRec(rs.getInt("rec"));
                rvo.setReport(rs.getInt("report"));
                list.add(rvo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return list;
    }
    
    // 간단하게 조회수를 1 증가시키는 메서드
    public void addRecipeView(int rnum) {
        int views = 0;
        con = Dbman.getConnection();
        String sql = "select views from recipe_page where rnum = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            if (rs.next())
                views = rs.getInt("views") + 1;

            sql = "update recipe_page set views = ? where rnum = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, views);
            pstmt.setInt(2, rnum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
    }
    
    // 마이페이지, 마이레시피에서 삭제를 누를 때 자식요소부터 삭제해야돼서 try catch문의 연속
    public void deleteMyRecipe(int rnum) {
        con = Dbman.getConnection();
        String sql = "delete from recipe where rnum=?";

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
        } catch (Exception e) {
            try {
                sql = "delete from recipeTag where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                sql = "delete from processimg where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                sql = "delete from recipe_page where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            try {
                sql = "delete from favorite where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                sql = "delete from interest where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            try {
                sql = "delete from reply where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
            } catch (Exception e6) {
                e6.printStackTrace();
            }
        } finally {
            try {
                sql = "delete from recipe where rnum=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.executeUpdate();
                ;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            Dbman.close(con, pstmt, rs);
        }
    }
    
    // 해당 유저가 좋아요를 눌렀는지 파악하기 위한 메서드
    public boolean isHeLikeThisRecipe(int rnum, String id) {
        boolean wth = false;
        con = Dbman.getConnection();
        String sql = "select * from interest where rnum = ? and id = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                wth = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return wth;
    }
    
    // 좋아요를 증가시키기 위한 레시피인데 likeyn, interestnum을 먼저 체크하는 메서드다.
    // 이름을 바꾸면 좋을듯
    public void addLikesRecipe(String id, int rnum) {
        String likeyn = "";
        int inum = 0;
        con = Dbman.getConnection();
        String sql = "select likeyn, interestnum from interest where rnum = ? and id = ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.setString(2, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                likeyn = rs.getString("likeyn");
                inum = Integer.parseInt(rs.getString("interestnum"));
            }
            if (likeyn.equals("N")) {
                sql = "update interest set likeyn = 'Y' where id = ? and rnum = ? and interestnum = ?";
            } else if (likeyn.equals("Y")) {
                sql = "update interest set likeyn = 'N' where id = ? and rnum = ? and interestnum = ?";
            } else {
                System.out.println("시스템 오류, 값을 불러오지 못했습니다.");
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setInt(2, rnum);
            pstmt.setInt(3, inum);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

    }

    public void insertLikeRecipe(String id, int rnum) {
        con = Dbman.getConnection();
        String sql = "insert into interest (interestnum, rnum, id, likeyn) values(interestnum_seq.nextVal, ?, ?, 'Y')";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
    }

    public void selectLikeRecipe(int rnum) {
        int likes = 0;
        con = Dbman.getConnection();
        String sql = "select count(*) as cnt from interest where rnum = ? and likeyn='Y'";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                likes = Integer.parseInt(rs.getString("cnt"));
            }
            sql = "update recipe_page set likes = ? where rnum = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, likes);
            pstmt.setInt(2, rnum);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

    }

    public void updateRecipeAtOnce(RecipeVO rvo, ArrayList<ProcessimageVO> pivoList, ArrayList<String> ingArray,
            ArrayList<String> quanArray) {
        con = Dbman.getConnection();
        String sql = "update recipe set subject=?, content=?, thumbnail=?, time=?, type=?, theme=? where rnum=?";
        try {
            // 1. recipe table 수정
            int rnum = rvo.getRnum();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rvo.getSubject());
            pstmt.setString(2, rvo.getContent());
            pstmt.setString(3, rvo.getThumbnail());
            pstmt.setInt(4, rvo.getTime());
            pstmt.setInt(5, rvo.getType());
            pstmt.setInt(6, rvo.getTheme());
            pstmt.setInt(7, rnum);
            pstmt.executeUpdate();

            // 2. rnum에 해당하는 processImg 삭제
            sql = "delete from processImg where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();

            // 3. processImg 테이블 삽입
            int i = 0;
            for (ProcessimageVO pivo : pivoList) {
                sql = "insert into processImg(rnum, iseq, links, description) values(?,?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.setInt(2, i + 1);
                pstmt.setString(3, pivo.getLinks());
                pstmt.setString(4, pivo.getDescription());
                pstmt.executeUpdate();
                i++;
            }

            // 4. recipeTag 테이블에서 rnum으로 레코드 삭제
            sql = "delete from recipeTag where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();

            // 5. 재료 태그 넣기 (ingTag, recipeTag) (기존 태그가 있는지 확인 후 태그 삽입 - 중복 시 기존 태그 id 불러옴)

            int lastTagId = 0;
            for (i = 0; i < ingArray.size(); i++) {
                sql = "select tag_id as tagId from ingTag where tag=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, ingArray.get(i));
                rs = pstmt.executeQuery();
                if (rs.next()) { // 기존 태그가 존재한다면
                    lastTagId = rs.getInt("tagId"); // 기존 태그 아이디 사용
                } else { // 기존 태그가 없다면
                    sql = "insert into ingTag(tag_id, tag) values(ingTag_seq.nextVal, ?)";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, ingArray.get(i));
                    pstmt.executeUpdate();

                    sql = "select max(tag_id) as lastTagId from ingTag"; // 방금 삽입한 tag_id 검색
                    pstmt = con.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        lastTagId = rs.getInt("lastTagId");
                    }
                }
                pstmt.close();
                // lastTagId 이용해 recipeTag 테이블에 레코드 추가
                sql = "insert into recipeTag(rnum, tag_id, quantity) values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, rnum);
                pstmt.setInt(2, lastTagId);
                pstmt.setString(3, quanArray.get(i));
                pstmt.executeUpdate();
                pstmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

    }

    public void deleteRecipeAtOnce(int rnum) {
        con = Dbman.getConnection();
        String sql = "";
        try {

            // 1. processImg 테이블에서 rnum으로 레코드 삭제
            sql = "delete from processImg where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
            // 2. recipeTag 테이블에서 rnum으로 레코드 삭제
            sql = "delete from recipeTag where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
            // 3. recipe_page 테이블에서 rnum으로 레코드 삭제
            sql = "delete from recipe_page where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
            // 4. interest 테이블에서 rnum으로 레코드 삭제
            sql = "delete from interest where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
            // 5. favorite 테이블에서 rnum으로 레코드 삭제
            sql = "delete from favorite where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();
            // 6. recipe 테이블에서 레코드 삭제 (참조되는 테이블이므로 가장 마지막에 삭제)
            sql = "delete from recipe where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }

    }

    public ArrayList<String> getIngTag(int rnum) {
        ArrayList<Integer> idList = new ArrayList<Integer>();
        ArrayList<String> tagList = new ArrayList<String>();
        con = Dbman.getConnection();
        // 1. recipeTag에서 rnum에 해당하는 tag_id 선택
        String sql = "";
        try {
            sql = "select tag_id as tagId from recipeTag where rnum=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int temp = rs.getInt("tagId");
                idList.add(temp);
            }
            pstmt.close();

            for (int j = 0; j < idList.size(); j++) {
                sql = "select tag from ingTag where tag_id=?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, idList.get(j));
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    tagList.add(j, rs.getString("tag"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return tagList;

        /*
         * 2번째 방법 : 해당 메서드에서 하나의 문자열로 받은 후 그대로 리턴 (recipeUpdateForm도 그에 맞게 수정)
         * String sql2 = "select tag, quantity from recipeTag where rnum=?";
         * String str2 = "";
         * try {
         * pstmt = con.prepareStatement(sql2);
         * pstmt.setInt(1, rnum);
         * rs = pstmt.executeQuery();
         * while(rs.next()) {
         * String ing = "#" + rs.getString("tag") + " ";
         * String quan = rs.getString("quantity") + " ";
         * str2 += (ing + quan);
         * }
         * } catch (SQLException e) { e.printStackTrace();
         * } finally { Dbman.close(con, pstmt, rs);
         * }
         * 
         * return str2;
         */

    }

    public ArrayList<String> getQuantity(int rnum) {
        ArrayList<String> list = new ArrayList<String>();
        con = Dbman.getConnection();
        String sql = "select quantity from recipeTag where rnum=?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String str = "";
                str = rs.getString("quantity");
                list.add(str);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
        return list;
    }

    public ArrayList<Integer> selectInterestRnums(String id) {
    	ArrayList<Integer> rnum = new ArrayList<Integer>();
		int a = 0;
		con=Dbman.getConnection();
		String sql="select*from interest where id = ? and likeyn ='Y'";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();			
			while( rs.next() ) {		
			
				a =Integer.parseInt(rs.getString("rnum"));			
				System.out.println("rnum");
				rnum.add(a);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return rnum;
	}

	public ArrayList<RecipeVO> selectInterestRecipe(ArrayList<Integer> rnum) {
		ArrayList<RecipeVO> list = new ArrayList<>();
		
		con=Dbman.getConnection();
		String sql="select*from recipe_page_view where rnum = ?";
		try {
			for(int rnum1:rnum) {
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, rnum1);
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}
	
	
	// 매개변수가 없을 때는 사람들이 Favorite한 전체 목록을 가져옵니다.
	public int getFavoriteAllCount() {
		int count=0;
		String sql="select count(*) as cnt from favorite fuseyn = 'Y' ";
		
		con=Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return count;
	}
	// 아이디를 매개변수로 넣었을 때는 그 아이디로 Y 한 내용들만 가져옵니다.
	public int getFavoriteAllCount(String id) {
		int count=0;
		String sql="select count(*) as cnt from favorite where id=? and fuseyn = 'Y' ";
		
		con=Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return count;
	}
	

	public ArrayList<FavoriteVO> selectFavorite(String id) {
		String sql = "select * from favorite where id=? and fuseyn='Y' ";
		
		ArrayList<FavoriteVO> flist = new ArrayList<FavoriteVO>();
		con = Dbman.getConnection();
		try {
		      pstmt = con.prepareStatement(sql); 
		      pstmt.setString(1, id);
		      rs= pstmt.executeQuery();
		      while(rs.next()) {
		    	 FavoriteVO fvo = new FavoriteVO();
		    	 fvo.setFuseyn(rs.getString("fuseyn"));
		    	 fvo.setFnum(rs.getInt("fnum"));
		    	 fvo.setId(rs.getString("id"));
		    	 fvo.setRnum(rs.getInt("rnum"));
		    	 flist.add(fvo);
		      }
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); } 
		return flist;
	}

    
    
    
    public void checkYourFYN(String[] rnum, String userId) {
		// 검사를 해서 테이블에 없으면 insert 를 먼저 합니다.
		con = Dbman.getConnection();

		String sql = "";
		try {
			for(int i = 0; i<rnum.length; i++) {
				int tempRnum = Integer.parseInt( rnum[i] );
				sql = "select * from favorite where rnum = ? and id = ?";
				pstmt = con.prepareStatement(sql);	
				pstmt.setInt(1, tempRnum );
				pstmt.setString(2, userId);
				rs = pstmt.executeQuery();
				if( !rs.next() ) {
					sql = "insert into favorite(fnum, id, rnum, fuseyn) "
							+ " values(fnum_seq.nextVal, ?, ?, 'Y')";
					pstmt = con.prepareStatement(sql);	
					pstmt.setString(1, userId);
					pstmt.setInt(2,  tempRnum );
					pstmt.executeUpdate();
				}
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}


	public void changeFYN(String rnum, String userId) {
		con = Dbman.getConnection();
		String sql = "select * from favorite where rnum = ? and id =?";
		String status = "";
		int fnum = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(rnum) );
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				fnum = Integer.parseInt( rs.getString("fnum") );
				if( rs.getString("fuseyn").equals("Y") ) {
					System.out.println("받은 fuseyn 값이 Y니까 N으로 바꾸겠습니다~");
					status = "N";
				}else {
					System.out.println("받은 fuseyn 값이 N니까 Y으로 바꾸겠습니다~");
					status = "Y";
				}
			}
			sql = "update favorite set fuseyn = ? where id = ? and rnum = ? and fnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, status);
			pstmt.setString(2, userId);
			pstmt.setInt(3, Integer.parseInt(rnum) );
			pstmt.setInt(4, fnum );
			pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public ArrayList<Integer> selectFavoriteDetail(String id) {
		ArrayList<Integer> frnum = new ArrayList<Integer>();
		int a = 0;
		con=Dbman.getConnection();
		String sql="select*from favorite where id = ? and fuseyn ='Y'";
		try {
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();			
			while( rs.next() ) {		
				a =Integer.parseInt(rs.getString("rnum"));			
				System.out.println("rnum");
				frnum.add(a);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return frnum;
	}
	
	// 마이페이지에서 접근하는 단골 레시피 보기 기능입니다.
	public ArrayList<RecipeVO> selectFavoriteRecipe(ArrayList<Integer> frnum, Paging paging) {
	
		ArrayList<RecipeVO> list = new ArrayList<>();
		
		con=Dbman.getConnection();
		String sql=" select * from ( "
				+ " select * from ( "
				+ " select rownum as rn, r.*from ((select*from recipe_page_view where rnum = ? order by rnum desc) r) "
				+ " ) where rn >=? "
				+ " ) where rn <=? ";
		
		try {
			for(int rnum1:frnum) {
				
			pstmt= con.prepareStatement(sql);
			pstmt.setInt(1, rnum1);			
     		pstmt.setInt(2, paging.getStartNum());
	        pstmt.setInt(3, paging.getEndNum());

			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	// recipeFavoriteAndRecAction 에서 호출하는 전체 단골 레시피 조회 메서드
	public ArrayList<Integer> selectFavoriteRecipeAll() {
		ArrayList<Integer> fAllList = new ArrayList<>();
		con=Dbman.getConnection();
		String sql="select*from recipe_page_view where rnum = ? order by rnum desc ";
		
		try {
			pstmt= con.prepareStatement(sql);		


			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
							
				fAllList.add( Integer.parseInt( rs.getString("") ) );
				
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return fAllList;
	}

	public int getInterestAllCount(String id) {
		int count=0;
		String sql="select count(*) as cnt from interest where id=?";
		
		con=Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return count;
	}

   
	public int getCountsByKey(String table, String field, String key) { // 검색 결과를 충족하는 게시글의 갯수 리턴
		int count = 0;
		con = Dbman.getConnection();
		String sql = "select count(*) as cnt from " + table + " where " + field + " like '%'||?||'%' ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		
		return count;
	}
	
	 
	public ArrayList<RecipeVO> selectListByIng(Paging paging, String key) { // 검색 결과를 충족하는 게시글 리스트 리턴
		ArrayList<RecipeVO> recipeList = new ArrayList<RecipeVO>();
		con = Dbman.getConnection();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, r.* from ("
				+ " (select distinct t.rnum, v.id, v.subject, v.thumbnail, v.nick, v.img, v.likes, v.views, v.time "
				+ " from recipeTag t, recipe_page_view v, ingTag i "
				+ " where t.rnum = v.rnum and t.tag_id = i.tag_id and i.tag like '%'||?||'%' ) r ) "
				+ " ) where rn >=? "
				+ " ) where rn <=? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RecipeVO rvo = new RecipeVO();
				rvo.setRnum(rs.getInt("rnum"));
				rvo.setSubject(rs.getString("subject"));
				rvo.setThumbnail(rs.getString("Thumbnail"));
				rvo.setNick(rs.getString("nick"));
				rvo.setImg(rs.getString("img"));
				rvo.setLikes(rs.getInt("likes"));
				rvo.setViews(rs.getInt("views"));
				rvo.setTime(rs.getInt("time"));
				rvo.setId(rs.getString("id"));
				recipeList.add(rvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		
		return recipeList;
		
	}

	public ArrayList<RecipeVO> selectListBySubCon(String field, Paging paging, String key) {
		ArrayList<RecipeVO> recipeList = new ArrayList<RecipeVO>();
		con = Dbman.getConnection();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, r.* from ("
				+ " (select distinct rnum, id, subject, thumbnail, nick, img, likes, views, time "
				+ " from recipe_page_view "
				+ " where " + field + " like '%'||?||'%' ) r ) "
				+ " ) where rn >=? "
				+ " ) where rn <=? ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				RecipeVO rvo = new RecipeVO();
				rvo.setRnum(rs.getInt("rnum"));
				rvo.setSubject(rs.getString("subject"));
				rvo.setThumbnail(rs.getString("Thumbnail"));
				rvo.setNick(rs.getString("nick"));
				rvo.setImg(rs.getString("img"));
				rvo.setLikes(rs.getInt("likes"));
				rvo.setViews(rs.getInt("views"));
				rvo.setTime(rs.getInt("time"));
				rvo.setId( rs.getString("id"));
				recipeList.add(rvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		
		return recipeList;
	}
	// recipeVO를 받는 형식의 CountReply입니다.
    public ArrayList<Integer> countReply(ArrayList<RecipeVO> list) {
		ArrayList<Integer> count = new ArrayList<>();
		int temp = 0;
		con = Dbman.getConnection();
        String sql = "select count(*) as cnt from reply where rnum = ?";
        try {
        	for(RecipeVO i : list) {
        		pstmt = con.prepareStatement(sql);
                pstmt.setInt( 1, i.getRnum() );
                rs = pstmt.executeQuery();
                if( rs.next() ) {
                    temp = Integer.parseInt( rs.getString("cnt") );
                }
                count.add(temp);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }
		
		return count;
	}
    // int arraylist를 받는 형식의 CountReply입니다.
    public ArrayList<Integer> countReplyByInteger(ArrayList<Integer> list) {
		ArrayList<Integer> count = new ArrayList<>();
		int temp = 0;
		con = Dbman.getConnection();
        String sql = "select count(*) as cnt from reply where rnum = ?";
        try {
        	for(Integer i : list) {
        		pstmt = con.prepareStatement(sql);
                pstmt.setInt( 1, i );
                rs = pstmt.executeQuery();
                if( rs.next() ) {
                    temp = Integer.parseInt( rs.getString("cnt") );
                }
                count.add(temp);
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { Dbman.close(con, pstmt, rs); }
		
		return count;
	}
    
    public ArrayList<InterestVO> selectIView(String id, Paging paging) {
        ArrayList<InterestVO> list =new ArrayList<InterestVO>();
        
         String sql =" select * from ( "
                     + " select * from ( "
                     + " select rownum as rn, r.*from ((select*from fi_view where interestid=? and likeyn='Y' order by interestnum desc) r)"
                     + " ) where rn >=? "
                     + " ) where rn <=? ";
//        String sql = "select*from fi_view where userid=? and likeyn='Y'  ";
        
             con = Dbman.getConnection();

             try {
                 pstmt = con.prepareStatement(sql);
                 pstmt.setString(1, id);
                 pstmt.setInt(2, paging.getStartNum());
                 pstmt.setInt(3, paging.getEndNum());

                 rs = pstmt.executeQuery();
                 while (rs.next()) {
                     InterestVO ivo = new InterestVO();
                     ivo.setRnum(rs.getInt("rnum"));
                     ivo.setSubject(rs.getString("subject"));
                     ivo.setContent(rs.getString("content"));
                     ivo.setId(rs.getString("id"));
                     ivo.setIndate(rs.getTimestamp("indate"));
                     ivo.setInterestid(rs.getString("interestid"));
                     ivo.setViews(rs.getInt("views"));
                     ivo.setFuseyn(rs.getString("fuseyn"));
//                     ivo.setLikeyn(rs.getString("likeyn"));
                     list.add(ivo);
                 }
             } catch (SQLException e) { e.printStackTrace();
             } finally { Dbman.close(con, pstmt, rs); }
     
        return list;
     }

     public ArrayList<InterestVO> selectFView(String id, Paging paging) {
        ArrayList<InterestVO> list =new ArrayList<InterestVO>();
        
         String sql =" select * from ( "
                     + " select * from ( "
                     + " select rownum as rn, r.*from ((select*from fi_view where interestid=? and fuseyn='Y' order by fnum desc) r)"
                     + " ) where rn >=? "
                     + " ) where rn <=? ";
//        String sql = "select*from fi_view where userid=? and likeyn='Y'  ";
        
             con = Dbman.getConnection();

             try {
                 pstmt = con.prepareStatement(sql);
                 pstmt.setString(1, id);
                 pstmt.setInt(2, paging.getStartNum());
                 pstmt.setInt(3, paging.getEndNum());

                 rs = pstmt.executeQuery();
                 while (rs.next()) {
                     InterestVO ivo = new InterestVO();
                     ivo.setRnum(rs.getInt("rnum"));
                     ivo.setSubject(rs.getString("subject"));
                     ivo.setContent(rs.getString("content"));
                     ivo.setId(rs.getString("id"));
                     ivo.setIndate(rs.getTimestamp("indate"));
                     ivo.setInterestid(rs.getString("interestid"));
                     ivo.setViews(rs.getInt("views"));
                     ivo.setFuseyn(rs.getString("fuseyn"));
//                     ivo.setLikeyn(rs.getString("likeyn"));
                     list.add(ivo);
                 }
             } catch (SQLException e) { e.printStackTrace();
             } finally { Dbman.close(con, pstmt, rs); }
     
        return list;
     }

	public void insertFavoriteRecipe(String id, int rnum) {
		con = Dbman.getConnection();
        String sql = "insert into favorite (fnum, rnum, id, fuseyn) values(fnum_seq.nextVal, ?, ?, 'N')";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, rnum);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Dbman.close(con, pstmt, rs);
        }
		
	}




	
}
