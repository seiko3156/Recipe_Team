package com.ezenb1.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenb1.recipe.dto.AddressVO;
import com.ezenb1.recipe.dto.MembersVO;
import com.ezenb1.recipe.util.Dbman;

public class MemberDao {

	private MemberDao () {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() { return itc; }
	
	Connection con =null;
	PreparedStatement pstmt =null;
	ResultSet rs=null;
	
	public ArrayList<AddressVO> selectAddress(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		
		con = Dbman.getConnection();
		String sql = "select * from address where dong like '%'||?||'%' ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressVO avo = new AddressVO();
				
				avo.setZip_num(rs.getString("zip_num"));
		    	avo.setSido(rs.getString("sido"));
		    	avo.setGugun(rs.getString("gugun"));
		    	avo.setDong(rs.getString("dong"));
		    	avo.setZip_code(rs.getString("zip_code"));
		    	avo.setBunji(rs.getString("bunji"));
				
				list.add(avo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return list;
	}


	public MembersVO getMember(String id) {
		MembersVO mvo = null;
		String sql = "select * from members where id=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  id);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				mvo = new MembersVO();
				mvo.setId( rs.getString("id") );
				mvo.setPwd(rs.getString("pwd"));
		        mvo.setName(rs.getString("name"));
		        mvo.setNick(rs.getString("nick"));
		        mvo.setEmail(rs.getString("email"));
		        mvo.setZip_num(rs.getString("zip_num"));
		        mvo.setAddress1(rs.getString("address1"));
		        mvo.setAddress2(rs.getString("address2"));
		        mvo.setPhone(rs.getString("phone"));
		        mvo.setUseyn(rs.getString("useyn"));
		        mvo.setIndate(rs.getTimestamp("indate"));
		        mvo.setImg(rs.getString("img"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);   }		
		return mvo;
	}

	public int insertMember(MembersVO mvo) {
        int result =0;
        String url="insert into members(id, pwd, name, phone, email, nick, address1, address2, zip_num, img, useyn) "
              + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        con= Dbman.getConnection();
        
        try {
           pstmt=con.prepareStatement(url);
           pstmt.setString(1, mvo.getId());      
           pstmt.setString(2, mvo.getPwd());
           pstmt.setString(3, mvo.getName());
           pstmt.setString(4, mvo.getPhone());
           pstmt.setString(5, mvo.getEmail());
           pstmt.setString(6, mvo.getNick());
           pstmt.setString(7, mvo.getAddress1());
           pstmt.setString(8, mvo.getAddress2());
           pstmt.setString(9, mvo.getZip_num());
           pstmt.setString(10, mvo.getImg());
           pstmt.setString(11, mvo.getUseyn());
           result=pstmt.executeUpdate();
           
        } catch (SQLException e) {
           e.printStackTrace();
        } finally {
           Dbman.close(con, pstmt, rs);
        }
        return result;
	}

	public void updateMember(MembersVO mvo) {
		String sql="update members set pwd=?, name=?, phone=?, email=?, nick=?, "
				+ "address1=?, address2=?, zip_num=?, img=?, useyn=?, indate=? where id=?";
		con = Dbman.getConnection();
		
		try {
			pstmt= con.prepareStatement(sql);

			pstmt.setString(1,mvo.getPwd());
			pstmt.setString(2,mvo.getName());
			pstmt.setString(3,mvo.getPhone());
			pstmt.setString(4,mvo.getEmail());
			pstmt.setString(5,mvo.getNick());
			pstmt.setString(6,mvo.getAddress1());
			pstmt.setString(7,mvo.getAddress2());
			pstmt.setString(8,mvo.getZip_num());
			pstmt.setString(9,mvo.getImg());
			pstmt.setString(10,mvo.getUseyn());
			pstmt.setTimestamp(11,mvo.getIndate());
			pstmt.setString(12,mvo.getId() );
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Dbman.close(con, pstmt, rs);
		}
	}


	public void withDrawalMember(String id) {
		String sql="Update members set useyn='N' where id = ?";
		con=Dbman.getConnection();
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
	}
	

	public MembersVO findId(String name, String phone) {
		MembersVO mvo = null;
		con = Dbman.getConnection();
		String sql = "select * from members where name=? and phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mvo = new MembersVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setEmail(rs.getString("email"));
				mvo.setNick(rs.getString("nick"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress1(rs.getString("address1"));
				mvo.setAddress2(rs.getString("address2"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getTimestamp("indate"));
				mvo.setImg(rs.getString("img"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return mvo;
	}


	public MembersVO findPwd(String name, String phone, String id) {
		MembersVO mvo = null;
		con = Dbman.getConnection();
		String sql = "select * from members where name=? and phone=? and id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mvo = new MembersVO();
				mvo.setId(rs.getString("id"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setEmail(rs.getString("email"));
				mvo.setNick(rs.getString("nick"));
				mvo.setZip_num(rs.getString("zip_num"));
				mvo.setAddress1(rs.getString("address1"));
				mvo.setAddress2(rs.getString("address2"));
				mvo.setUseyn(rs.getString("useyn"));
				mvo.setIndate(rs.getTimestamp("indate"));
				mvo.setImg(rs.getString("img"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return mvo;
	}


	public void insertRecipeReply( String id, int rnum, String reply) {
		System.out.println(id);
		System.out.println(rnum);
		System.out.println(reply);
		String sql = "insert into reply(replyseq, id, rnum, content)"
				+ " values(reply_seq.nextval, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id );
			pstmt.setInt(2,  rnum );
			pstmt.setString(3, reply );
			pstmt.executeUpdate();
			System.out.println("덧글이 업데이트가 잘 되었다!");
		} catch (SQLException e) {e.printStackTrace(); 
		} finally { Dbman.close(con, pstmt, rs);  }
	}


	public int updatePwd(String id, String pwd) {
		int result = 0;
		con = Dbman.getConnection();
		String sql = "update members set pwd=? where id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);
		}
		return result;
	}


	public void deleteRecipeReply(String id, int rnum, String reply, String replyseq) {
		  System.out.println(id);
	      System.out.println(rnum);
	      System.out.println(reply);
	      System.out.println("1   " + replyseq);
	      String sql = "delete from reply where replyseq=?";
	      con = Dbman.getConnection();
	      try {
	         pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, replyseq );
//	         pstmt.setInt(2,  rnum );
//	         pstmt.setString(3, reply );
	         pstmt.executeUpdate();
	         System.out.println("덧글이 삭제가 잘 되었다!");
	      } catch (SQLException e) {e.printStackTrace(); 
	      } finally { Dbman.close(con, pstmt, rs);  }
	      }

}
