package com.ezenb1.recipe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenb1.recipe.dto.QnaVO;
import com.ezenb1.recipe.util.Dbman;
import com.ezenb1.recipe.util.Paging;

public class QnaDao {
	
	// 싱글톤
	private QnaDao() {}
	private static QnaDao ist = new QnaDao();
	public static QnaDao getInstance() { return ist; }
	
	// 데이터 연결
	Connection con = null;
	// 쿼리에 파라미터를 전달
	PreparedStatement pstmt = null;
	// 결과값
	ResultSet rs = null;
	
	// 모든 글 조회
	public ArrayList<QnaVO> selectQna(Paging paging) {
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
	
		// ?표에 값을 넣기 위한 과정
		// 가변적 동적으로 변하는 값은 키 밸류로 보냄. 어떤 값이 들어갈지 모름
		//
		String sql = "select *from ("
				+ " select*from ("
				// 모든 파일 조회시 비밀글-1, 공개글-0 내림차순(최신글)로 정렬 하기 
				+ " select rownum as rn, q.*from ((select * from qna order by qseq desc) q)"
				+ ") where rn>=? "  //1
				+ ") where rn<=? "; //2
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getStartNum() );
			pstmt.setInt(2, paging.getEndNum() );
			// 쿼리 실행해라
			rs = pstmt.executeQuery();
			// 다음값이 있으면 넘어가라
			while(rs.next() ) {
				QnaVO qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq") );
				qvo.setQsubject(rs.getString("qsubject") );
				qvo.setQcontent(rs.getString("qcontent") );
				qvo.setId(rs.getString("id"));
				qvo.setQnadate(rs.getTimestamp("qnadate"));
				qvo.setQnapass(rs.getString("qnapass"));
				qvo.setSecret(rs.getString("secret"));
				qvo.setReplyQna(rs.getString("replyQna"));
				qvo.setRep(rs.getInt("rep"));
				list.add(qvo);
			}	
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	}
		return list;
	}

	public int getMyCount(String id) { // 내가 쓴 QnaList를 구하는 메서드
		int count = 0;
		// 내가 쓴글 몇개?
		String sql="select count(*) as cnt from qna where id=?";
		con = Dbman.getConnection();
		try {
			// ? 한개 id에 대한
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			// execute 실행
			rs = pstmt.executeQuery();
			// 
			if ( rs.next() ) count = rs.getInt("cnt");
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	
		}

		return count;
	}

	public QnaVO getQna(String qseq) {
		QnaVO qvo = new QnaVO();
		// qseq 시퀀스 값
		String sql = "select * from qna where qseq =?";
		// 연결
		con = Dbman.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			// ? 한개 qseq 에 관한것 문자로 오기때문에 숫자로 변환
			pstmt.setString(1, qseq);
			// 쿼리 실행
			rs = pstmt.executeQuery();

			if(rs.next() ){
				qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setId(rs.getString("id"));
				qvo.setQsubject(rs.getString("qsubject"));
				qvo.setQnadate(rs.getTimestamp("qnadate"));
				qvo.setQcontent(rs.getString("qcontent"));
				qvo.setSecret(rs.getString("secret"));
				qvo.setReplyQna(rs.getString("replyqna"));
				qvo.setRep(rs.getInt("rep"));
				qvo.setQnapass(rs.getString("qnapass"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return qvo;
	}
	
	public void insertQna(QnaVO q) {
		String secret=q.getSecret();
		String sql="";
		con = Dbman.getConnection();
		
		try {		
			if(secret==null) {
				sql ="insert into qna (qseq, qsubject, qcontent, id) values (qseq_seq.nextval, ?, ?, ? )";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, q.getQsubject() );
				pstmt.setString(2, q.getQcontent() );
				pstmt.setString(3, q.getId());			
			} else if (secret.equals("1")) {
				sql ="insert into qna (qseq, qsubject, qcontent, id,qnapass,secret) values (qseq_seq.nextval, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, q.getQsubject() );
				pstmt.setString(2, q.getQcontent() );
				pstmt.setString(3, q.getId());
				pstmt.setString(4, q.getQnapass());
				pstmt.setString(5, q.getSecret());
			}
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	
		}
	}

	public void updateQna(QnaVO qvo) {
		
		con = Dbman.getConnection();
		String sql = "update Qna set qsubject=?, qcontent=?, qnadate=sysdate where qseq=?";
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qvo.getQsubject() );
			pstmt.setString(2, qvo.getQcontent() );
			pstmt.setInt(3, qvo.getQseq() );
		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}
	}

	public void deleteQna(String qseq) {
		String sql = "delete from Qna where qseq=?";
		con = Dbman.getConnection();	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(qseq) );
			pstmt.executeUpdate();
			
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}
	}

	public QnaVO getQna(int qseq) {
		QnaVO qvo = null;
		
		String sql = "select * from qna where qseq=?";
		con = Dbman.getConnection();	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qseq);
			rs=pstmt.executeQuery();
			if( rs.next() ) {
				qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setId(rs.getString("id"));
				qvo.setQsubject(rs.getString("qsubject"));
				qvo.setQnadate(rs.getTimestamp("qnadate"));
				qvo.setQcontent(rs.getString("qcontent"));
				qvo.setQnapass(rs.getString("qnapass"));
				qvo.setReplyQna(rs.getString("replyqna"));
				qvo.setRep(rs.getInt("rep"));
			}		
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);}

		return qvo;
	}

	public int getAllCount() {
		int count = 0;
		String sql="select count(*) as cnt from qna";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if ( rs.next() ) count = rs.getInt("cnt");
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return count;
	}

	public ArrayList<QnaVO> selectQna(String id, Paging paging) {
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		// 조회 	// 모든 행번호를 조회
		String sql = "select *from ("
				+ " select*from ("
				+ " select rownum as rn, q.*from ((select * from qna where id=? order by qseq desc) q)"
				+ ") where rn>=? " 
				+ ") where rn<=? ";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, paging.getStartNum() );
			pstmt.setInt(3, paging.getEndNum() );
			rs = pstmt.executeQuery();
			while(rs.next() ) { // qnapass 내용 추가
				QnaVO qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq") );
				qvo.setQsubject(rs.getString("qsubject") );
				qvo.setQcontent(rs.getString("qcontent") );
				qvo.setId(rs.getString("id"));
				qvo.setQnapass(rs.getString("qnapass"));
				qvo.setQnadate(rs.getTimestamp("qnadate"));
				qvo.setReplyQna(rs.getString("replyQna"));
				qvo.setRep(rs.getInt("rep"));
				qvo.setSecret(rs.getString("secret"));
				list.add(qvo);
			}	
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	}
		return list;
	}
	
	
}