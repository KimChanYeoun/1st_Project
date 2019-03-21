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

import com.tj.ex.dto.NoticeDto;

public class NoticeDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public NoticeDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 글목록
	public ArrayList<NoticeDto> nlist(int startRow, int endRow) {
		ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + "    (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY nNum DESC) A)"
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int nNum = rs.getInt("nNum");
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nDate = rs.getDate("nDate");
				dtos.add(new NoticeDto(nNum, aId, nTitle, nContent, nDate));
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
	public int getNoticeTotCnt() {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM NOTICE";
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
	
	// 글목록
		public ArrayList<NoticeDto> Hnlist(int startRow, int endRow) {
			ArrayList<NoticeDto> dtos = new ArrayList<NoticeDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM " 
			+ "    (SELECT ROWNUM RN, A.* FROM "
					+ "    (SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE N.AID=A.AID ORDER BY nNum DESC) A)"
					+ "    WHERE RN BETWEEN 1 AND 5";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int nNum = rs.getInt("nNum");
					String aId = rs.getString("aId");
					String nTitle = rs.getString("nTitle");
					String nContent = rs.getString("nContent");
					Date nDate = rs.getDate("nDate");
					dtos.add(new NoticeDto(nNum, aId, nTitle, nContent, nDate));
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

	// 글쓰기
	public int nwrite(String aId, String nTitle, String nContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO NOTICE (nNum, AID, nTitle, nContent)" + "    VALUES (NOTICE_SEQ.NEXTVAL, ?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, nTitle);
			pstmt.setString(3, nContent);
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

	// 글 상세보기(bid로 dto리턴)
	public NoticeDto nContentView(int nNum) {
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE A.AID=N.AID AND NNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nDate = rs.getDate("nDate");
				dto = new NoticeDto(nNum, aId, nTitle, nContent, nDate);
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
	public NoticeDto nModifyView_replyView(int nNum) {
		NoticeDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT N.*, ANAME FROM NOTICE N, ADMIN A WHERE A.AID=N.AID AND NNum=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String aId = rs.getString("aId");
				String nTitle = rs.getString("nTitle");
				String nContent = rs.getString("nContent");
				Date nDate = rs.getDate("nDate");
				dto = new NoticeDto(nNum, aId, nTitle, nContent, nDate);
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
	public int nModify(int nNum, String nTitle, String nContent) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE NOTICE SET NTITLE = ?," 
		        + "                  NCONTENT = ?,"
				+ "                  NDATE = SYSDATE" 
		        + "            WHERE NNum = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nTitle);
			pstmt.setString(2, nContent);
			pstmt.setInt(3, nNum);
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
	public int nDelete(int nNum) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM NOTICE WHERE NNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, nNum);
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