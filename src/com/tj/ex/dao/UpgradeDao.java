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

import com.tj.ex.dto.UpgradeDto;

public class UpgradeDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public UpgradeDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 글목록
	public ArrayList<UpgradeDto> ulist(int startRow, int endRow) {
		ArrayList<UpgradeDto> dtos = new ArrayList<UpgradeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " 
		        + "    (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE U.AID=A.AID ORDER BY UNum DESC) A)"
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int uNum = rs.getInt("uNum");
				String aId = rs.getString("aId");
				String uTitle = rs.getString("uTitle");
				String uContent = rs.getString("uContent");
				Date uDate = rs.getDate("uDate");
				dtos.add(new UpgradeDto(uNum, aId, uTitle, uContent, uDate));
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
	public int getUpgradeTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM UPGRADE";
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
	
	// 글쓰기(원글쓰기)
	public int uWrite(String aId, String uTitle, String uContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO UPGRADE (UNum, AID, UTitle, UContent)" + "    VALUES (UPGRADE_SEQ.NEXTVAL, ?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, uTitle);
			pstmt.setString(3, uContent);
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

	// 글 상세보기(조회수 up + bid로 dto리턴)
	public UpgradeDto uContentView(int uNum) {
		UpgradeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE U.AID=A.AID AND UNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String uTitle = rs.getString("uTitle");
				String uContent = rs.getString("uContent");
				Date uDate = rs.getDate("uDate");
				dto = new UpgradeDto(uNum, aId, uTitle, uContent, uDate);
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
	public UpgradeDto uModifyView_replyView(int uNum) {
		UpgradeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT U.*, ANAME FROM UPGRADE U, ADMIN A WHERE A.AID=U.AID AND UNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String uTitle = rs.getString("uTitle");
				String uContent = rs.getString("uContent");
				Date uDate = rs.getDate("uDate");
				dto = new UpgradeDto(uNum, aId, uTitle, uContent, uDate);
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
	public int uModify(int uNum, String uTitle, String uContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE UPGRADE SET UTITLE = ?," + "                  UCONTENT = ?,"
				+ "                  UDATE = SYSDATE" + "            WHERE UNum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uTitle);
			pstmt.setString(2, uContent);
			pstmt.setInt(3, uNum);
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

	// 글 삭제하기(uNum로 글 삭제하기)
	public int uDelete(int uNum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM UPGRADE WHERE UNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uNum);
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