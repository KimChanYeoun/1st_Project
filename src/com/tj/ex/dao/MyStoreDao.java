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
import com.tj.ex.dto.ErrorboardDto;
import com.tj.ex.dto.GalleryDto;
import com.tj.ex.dto.MyStoreDto;
import com.tj.ex.dto.QnADto;

public class MyStoreDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public MyStoreDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// 글목록
	public ArrayList<BoardDto> MyBoardlist(String cId, int startRow, int endRow) {
		ArrayList<BoardDto> dtos = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT * FROM BOARD WHERE CID = ? ORDER BY BNUM DESC) A)" 
				+ "    WHERE RN BETWEEN ? AND ?";
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
	public int getMyBoardTotCnt(String cId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM BOARD WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
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
	public ArrayList<GalleryDto> MyGallerylist(String cId, int startRow, int endRow) {
		ArrayList<GalleryDto> dtos = new ArrayList<GalleryDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT * FROM GALLERY WHERE CID = ? ORDER BY gNUM DESC) A)" 
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int gNum = rs.getInt("gNum");
				String gTitle = rs.getString("gTitle");
				String gContent = rs.getString("gContent");
				String gfileName = rs.getString("gfileName");
				Date gDate = rs.getDate("gDate");
				int gHit = rs.getInt("gHit");
				int gGroup = rs.getInt("gGroup");
				int gStep = rs.getInt("gStep");
				int gIndent = rs.getInt("gIndent");
				String gIp = rs.getString("gIp");
				dtos.add(new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp,
						gDate));
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
	public int getMyGalleryTotCnt(String cId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM GALLERY WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
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
	public ArrayList<QnADto> MyQnAlist(String cId, int startRow, int endRow) {
		ArrayList<QnADto> dtos = new ArrayList<QnADto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT * FROM QnA WHERE CID = ? ORDER BY qNUM DESC) A)" 
				+ "    WHERE RN BETWEEN ? AND ?";
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
				Date qDate = rs.getDate("qDate");
				int qHit = rs.getInt("qHit");
				int qGroup = rs.getInt("qGroup");
				int qStep = rs.getInt("qStep");
				int qIndent = rs.getInt("qIndent");
				String qIp = rs.getString("qIp");
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
	public int getMyQnATotCnt(String cId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
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
	public ArrayList<ErrorboardDto> MyErrorlist(String cId, int startRow, int endRow) {
		ArrayList<ErrorboardDto> dtos = new ArrayList<ErrorboardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "    (SELECT * FROM ERRORBOARD WHERE CID = ? ORDER BY eNUM DESC) A)" 
				+ "    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int eNum = rs.getInt("eNum");
				String aId = rs.getString("aId");
				String eTitle = rs.getString("eTitle");
				String eContent = rs.getString("eContent");
				String efileName = rs.getString("efileName");
				Date eDate = rs.getDate("eDate");
				int eGroup = rs.getInt("eGroup");
				int eStep = rs.getInt("eStep");
				int eIndent = rs.getInt("eIndent");
				String eIp = rs.getString("eIp");
				dtos.add(new ErrorboardDto(eNum, cId, aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp,
						eDate));
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
	public int getMyErrorTotCnt(String cId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ERRORBOARD WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
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

	public int buyitem(int iNum, String cId) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MYSTORE (SITEM, INUM, CID) VALUES(MYSTORE_SEQ.NEXTVAL, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
			pstmt.setString(2, cId);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "구매성공" : "구매실패");
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

	// 글목록
	public ArrayList<MyStoreDto> Myitemlist(String cId, int startRow, int endRow) {
		ArrayList<MyStoreDto> dtos = new ArrayList<MyStoreDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT M.INUM, INAME, iPictrue FROM ITEM I, MYSTORE M" + 
				"    WHERE M.CID = ? AND I.INUM=M.INUM ORDER BY INUM DESC) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int sItem = rs.getInt("sItem");
				int iNum = rs.getInt("iNum");
				String iPictrue = rs.getString("iPictrue");
				String iName = rs.getString("iName");
				dtos.add(new MyStoreDto(sItem, iNum, cId, iPictrue, iName));
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
	public int getMyitemTotCnt(String cId) {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM MYSTORE";
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
}