package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.QnADto;

public class QnADao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public QnADao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 글목록
	public ArrayList<QnADto> Qlist(int startRow, int endRow) {
		ArrayList<QnADto> dtos = new ArrayList<QnADto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " 
		        + "    (SELECT ROWNUM RN, A.* FROM "
				+ "    (select * from QnA order by Qgroup desc, Qstep) A)" 
		        + "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int qNum = rs.getInt("qNum");
				String cId = rs.getString("cId");
				String aId = rs.getString("aId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				Date qDate = rs.getDate("qDate");
				dtos.add(new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	
	// 글목록
		public ArrayList<QnADto> myQlist(String cId, int startRow, int endRow) {
			ArrayList<QnADto> dtos = new ArrayList<QnADto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM " 
			        + "    (SELECT ROWNUM RN, A.* FROM "
					+ "    (select * from QnA order by Qgroup desc, Qstep) A)" 
			        + "    WHERE cid = ? and RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int qNum = rs.getInt("qNum");
					String aId = rs.getString("aId");
					String qTitle = rs.getString("qTitle");
					String qContent = rs.getString("qContent");
					int qHit = rs.getInt("qHit");
					int qGroup = rs.getInt("qGroup");
					int qStep = rs.getInt("qStep");
					int qIndent = rs.getInt("qIndent");
					String qIp = rs.getString("qIp");
					Date qDate = rs.getDate("qDate");
					dtos.add(new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return dtos;
		}

	// 글갯수
	public int getQnATotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM QnA";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}
	
	// 내가 쓴 글 갯수
	public int getQnAMyTotCnt(String cId) {
		int cnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM QnA WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rs   !=null) {
					rs.close();
				}
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return cnt;
	}


	// 글쓰기(원글쓰기)
	public int qWrite(String cId, String qTitle, String qContent, String qIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QnA (QNum, cId, QTitle, QContent, QGroup, " 
		        + "      QStep, QIndent, QIp)"
				+ "  VALUES (QnA_SEQ.NEXTVAL, ?,?,?, QnA_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, qTitle);
			pstmt.setString(3, qContent);
			pstmt.setString(4, qIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "원글쓰기성공" : "원글쓰기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 조회수올리기
	private void qhitUp(int qNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QnA SET QHIT = QHIT +1 WHERE QNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// 글 상세보기(조회수 up + bid로 dto리턴)
	public QnADto qContentView(int qNum) {
		qhitUp(qNum);
		QnADto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, CNAME FROM QNA Q, CUSTOMER C WHERE C.CID=Q.CID AND qNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String aId = rs.getString("aId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				Date qDate = rs.getDate("qDate");
				dto = new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	
	public QnADto qrContentView(int qNum) {
		qhitUp(qNum);
		QnADto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, ANAME FROM QNA Q, ADMIN A WHERE A.AID=Q.AID and qNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String aId = rs.getString("aId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				Date qDate = rs.getDate("qDate");
				dto = new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	// 답변글 view + 수정 view (bid로 dto리턴)
	public QnADto qModifyView_replyView(int qNum) {
		QnADto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, CNAME FROM QNA Q, CUSTOMER C WHERE C.CID=Q.CID AND qNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String aId = rs.getString("aId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				Date qDate = rs.getDate("qDate");
				dto = new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}

	public QnADto qModifyView(int qNum) {
		QnADto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT Q.*, ANAME FROM QNA Q, ADMIN A WHERE A.AID=Q.AID and qNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String aId = rs.getString("aId");
				String qTitle = rs.getString("qTitle");
				String qContent = rs.getString("qContent");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
				Date qDate = rs.getDate("qDate");
				dto = new QnADto(qNum, cId, aId, qTitle, qContent, qHit, qGroup, qStep, qIndent, qIp, qDate);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return dto;
	}
	// 글 수정하기
	public int qModify(int qNum, String qTitle, String qContent, String qIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QnA SET QTITLE = ?," + "               QCONTENT = ?," + "               QIP = ?,"
				+ "               QDATE = SYSDATE" + "            WHERE QNum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, qTitle);
			pstmt.setString(2, qContent);
			pstmt.setString(3, qIp);
			pstmt.setInt(4, qNum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글수정성공" : "글수정실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 글 삭제하기(qNum로 글 삭제하기)
	public int qDelete(int qNum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM QnA WHERE QNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qNum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글삭제성공" : "글삭제실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// step a
	private void qPreReplyStepA(int qGroup, int qStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE QnA SET QStep = QStep+1 " + "    WHERE QGroup = ? AND QStep>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qGroup);
			pstmt.setInt(2, qStep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	// 답변글 쓰기
	public int qReply(String aId, String qTitle, String qContent, int qGroup, int qStep, int qIndent, String qIp) {
		qPreReplyStepA(qGroup, qStep); // 답변글 저장전 step A 먼저 실행
		// bgroup, bstep, bindent 원글정보
		// bname, btitle, bcontent, bip 답변글 정보
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO QnA (QNum, AId, QTitle, QContent, QGroup,  " 
		        + "        QStep, QIndent, QIp)"
				+ "    VALUES (QNA_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, qTitle);
			pstmt.setString(3, qContent);
			pstmt.setInt(4, qGroup);
			pstmt.setInt(5, qStep + 1);
			pstmt.setInt(6, qIndent + 1);
			pstmt.setString(7, qIp);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답변쓰기성공" : "답변쓰기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	// 글 삭제하기(qNum로 글 삭제하기)
		public int qallDelete(String cId) {
			int result = FAIL;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "DELETE FROM QnA WHERE CID=?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				result = pstmt.executeUpdate();
				System.out.println(result == SUCCESS ? "글삭제성공" : "글삭제실패");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			return result;
		}
}