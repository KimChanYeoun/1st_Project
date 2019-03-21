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

import com.tj.ex.dto.BoardDto;

public class BoardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public BoardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 글목록
	public ArrayList<BoardDto> blist(int startRow, int endRow) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + "    (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE B.CID=C.CID ORDER BY BGROUP DESC, BSTEP) A)"
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int bNum = rs.getInt("bNum");
				String cId = rs.getString("cId");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Date bDate = rs.getDate("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				dtos.add(new BoardDto(bNum, cId, bTitle, bContent, bHit, bGroup, bStep, bIndent, bIp, bDate));
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
	
	// 내가 쓴글 목록
		public ArrayList<BoardDto> myblist(String cId, int startRow, int endRow) {
			ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM " 
			        + "    (SELECT ROWNUM RN, A.* FROM "
					+ "    (SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE B.CID=C.CID ORDER BY BGROUP DESC, BSTEP) A)"
					+ "    WHERE cid = ? and RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int bNum = rs.getInt("bNum");
					String bTitle = rs.getString("bTitle");
					String bContent = rs.getString("bContent");
					Date bDate = rs.getDate("bDate");
					int bHit = rs.getInt("bHit");
					int bGroup = rs.getInt("bGroup");
					int bStep = rs.getInt("bStep");
					int bIndent = rs.getInt("bIndent");
					String bIp = rs.getString("bIp");
					dtos.add(new BoardDto(bNum, cId, bTitle, bContent, bHit, bGroup, bStep, bIndent, bIp, bDate));
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
	public int getBoardTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM BOARD";
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
			public int getBoardMyTotCnt(String cId) {
				int cnt = 0;
				Connection        conn  = null;
				PreparedStatement pstmt = null;
				ResultSet         rs    = null;
				String sql = "SELECT COUNT(*) FROM Board WHERE CID = ?";
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
	public int bwrite(String cId, String bTitle, String bContent, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup, " 
		        + "        bStep, bIndent, bIp) "
				+ "    VALUES (BOARD_SEQ.NEXTVAL, ?,?,?," 
				+ "        BOARD_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setString(4, bIp);
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
	private void bhitUp(int bNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BHIT = BHIT +1 WHERE bNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
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
	public BoardDto bcontentView(int bNum) {
		bhitUp(bNum);
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE C.CID=B.CID AND bNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				Date bDate = rs.getDate("bDate");
				dto = new BoardDto(bNum, cId, bTitle, bContent, bHit, bGroup, bStep, bIndent, bIp, bDate);
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
	public BoardDto bmodifyView_replyView(int bNum) {
		BoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT B.*, CNAME FROM BOARD B, CUSTOMER C WHERE C.CID=B.CID AND bNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String cId = rs.getString("cId");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				String bIp = rs.getString("bIp");
				Date bDate = rs.getDate("bDate");
				dto = new BoardDto(bNum, cId, bTitle, bContent, bHit, bGroup, bStep, bIndent, bIp, bDate);
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
	public int bmodify(int bNum, String bTitle, String bContent, String bIp) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET BTITLE = ?," 
		        + "            BCONTENT = ?,"
				+ "            BIP = ?," 
		        + "            BDATE = SYSDATE" 
				+ "            WHERE bNum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bContent);
			pstmt.setString(3, bIp);
			pstmt.setInt(4, bNum);
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

	// 글 삭제하기(bid로 글 삭제하기)
	public int bdelete(int bNum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE BNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
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
	private void bpreReplyStepA(int bGroup, int bStep) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE BOARD SET bStep = bStep+1 " + "    WHERE bGroup = ? AND bStep>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
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
	public int breply(String cId, String bTitle, String bContent, String bIp, int bGroup, int bStep, int bIndent) {
		bpreReplyStepA(bGroup, bStep); // 답변글 저장전 step A 먼저 실행
		// bgroup, bstep, bindent 원글정보
		// bname, btitle, bcontent, bip 답변글 정보
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO BOARD (bNum, cId, bTitle, bContent, bGroup, " + "        bStep, bIndent, bIp)"
				+ "    VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, bGroup);
			pstmt.setInt(5, bStep + 1);
			pstmt.setInt(6, bIndent + 1);
			pstmt.setString(7, bIp);
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
	public int ballDelete(String cId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM BOARD WHERE CID=?";
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