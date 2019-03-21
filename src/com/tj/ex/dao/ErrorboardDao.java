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

import com.tj.ex.dto.ErrorboardDto;

public class ErrorboardDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	
	public ErrorboardDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// 글목록
	public ArrayList<ErrorboardDto> elist(int startRow, int endRow){
		ArrayList<ErrorboardDto> dtos = new ArrayList<ErrorboardDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (select * from ERRORBOARD order by Egroup desc, Estep) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int eNum = rs.getInt("eNum");
				 String cId = rs.getString("cId");
				 String aId = rs.getString("aId");
				 String eTitle = rs.getString("eTitle");
				 String eContent = rs.getString("eContent");
				 String efileName = rs.getString("efileName");
				 int eGroup = rs.getInt("eGroup");
				 int eStep = rs.getInt("eStep");
				 int eIndent = rs.getInt("eIndent");
				 String eIp = rs.getString("eIp");
				 Date eDate = rs.getDate("eDate");
				dtos.add(new ErrorboardDto(eNum, cId, aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp, eDate));
			}
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
		return dtos;
	}
	
	// 글목록
		public ArrayList<ErrorboardDto> myelist(String cId, int startRow, int endRow){
			ArrayList<ErrorboardDto> dtos = new ArrayList<ErrorboardDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM " + 
					"    (SELECT ROWNUM RN, A.* FROM " + 
					"    (select * from ERRORBOARD order by Egroup desc, Estep) A)" + 
					"    WHERE cid = ? and RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, cId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					 int eNum = rs.getInt("eNum");
					 String aId = rs.getString("aId");
					 String eTitle = rs.getString("eTitle");
					 String eContent = rs.getString("eContent");
					 String efileName = rs.getString("efileName");
					 int eGroup = rs.getInt("eGroup");
					 int eStep = rs.getInt("eStep");
					 int eIndent = rs.getInt("eIndent");
					 String eIp = rs.getString("eIp");
					 Date eDate = rs.getDate("eDate");
					dtos.add(new ErrorboardDto(eNum, cId, aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp, eDate));
				}
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
			return dtos;
		}
	
	// 글갯수
	public int getErrorBoardTotCnt() {
		int cnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ERRORBOARD";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
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
	
	// 내가 쓴 글 갯수
		public int getErrorBoardMyTotCnt(String cId) {
			int cnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT COUNT(*) FROM ErrorBoard WHERE CID = ?";
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
	public int eWrite(String cId, String eTitle, String eContent, String efileName,
			String eIp) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ERRORBOARD (ENUM, CID, ETITLE, ECONTENT, EFILENAME,  " + 
				"        EGROUP, ESTEP, EINDENT, EIP)" + 
				"    VALUES (ERRORBOARD_SEQ.NEXTVAL, ?,?,?,?, " + 
				"        ERRORBOARD_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, eTitle);
			pstmt.setString(3, eContent);
			pstmt.setString(4, efileName);
			pstmt.setString(5, eIp);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "원글쓰기성공":"원글쓰기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
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
		return result;
	}
	// 글 상세보기(조회수 up + bid로 dto리턴)
	public ErrorboardDto eContentView(int eNum) {
		ErrorboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM ERRORBOARD WHERE ENUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId = rs.getString("cId");
				 String aId = rs.getString("aId");
				 String eTitle = rs.getString("eTitle");
				 String eContent = rs.getString("eContent");
				 String efileName = rs.getString("efileName");
				 int eGroup = rs.getInt("eGroup");
				 int eStep = rs.getInt("eStep");
				 int eIndent = rs.getInt("eIndent");
				 String eIp = rs.getString("eIp");
				 Date eDate = rs.getDate("eDate");
				dto = new ErrorboardDto(eNum, cId, aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp, eDate);
			}
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
		return dto;
	}
	// 답변글 view + 수정 view (bid로 dto리턴)
	public ErrorboardDto emodifyView_replyView(int eNum) {
		ErrorboardDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM ERRORBOARD WHERE ENUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId = rs.getString("cId");
				 String aId = rs.getString("aId");
				 String eTitle = rs.getString("eTitle");
				 String eContent = rs.getString("eContent");
				 String efileName = rs.getString("efileName");
				 int eGroup = rs.getInt("eGroup");
				 int eStep = rs.getInt("eStep");
				 int eIndent = rs.getInt("eIndent");
				 String eIp = rs.getString("eIp");
				 Date eDate = rs.getDate("eDate");
				dto = new ErrorboardDto(eNum, cId, aId, eTitle, eContent, efileName, eGroup, eStep, eIndent, eIp, eDate);
			}
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
		return dto;
	}
	// 글 수정하기
	public int eModify(int eNum, String eTitle, String eContent, String efileName, 
			String eIp) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ERRORBOARD SET ETITLE = ?," + 
				"                    ECONTENT = ?," + 
				"                    EFILENAME = ?," + 
				"                    EIP = ?," + 
				"                    EDATE = SYSDATE" + 
				"            WHERE ENUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, eTitle);
			pstmt.setString(2, eContent);
			pstmt.setString(3, efileName);
			pstmt.setString(4, eIp);
			pstmt.setInt(5, eNum);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글수정성공":"글수정실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
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
		return result;
	}
	// 글 삭제하기(bid로 글 삭제하기)
	public int eDelete(int eNum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ERRORBOARD WHERE ENUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eNum);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "글삭제성공":"글삭제실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
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
		return result;
	}	
	// step a
	private void ePreReplyStepA(int eGroup, int eStep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ERRORBOARD SET ESTEP = ESTEP+1  " + 
				"    WHERE EGROUP = ? AND ESTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, eGroup);
			pstmt.setInt(2, eStep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
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
	}
	// 답변글 쓰기
	public int ereply(String aId, String eTitle, String eContent, String efileName,
			int eGroup, int eStep, int eIndent, String eIp) {
		ePreReplyStepA(eGroup, eStep); 
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ERRORBOARD (ENUM, AID, ETITLE, ECONTENT, EFILENAME," + 
				"        EGROUP, ESTEP, EINDENT, EIP)" + 
				"    VALUES (ERRORBOARD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aId);
			pstmt.setString(2, eTitle);
			pstmt.setString(3, eContent);
			pstmt.setString(4, efileName);
			pstmt.setInt(5, eGroup);
			pstmt.setInt(6, eStep+1);
			pstmt.setInt(7, eIndent+1);
			pstmt.setString(8, eIp);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "답변쓰기성공":"답변쓰기실패");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
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
		return result;
	}
	
	// 글 삭제하기(qNum로 글 삭제하기)
				public int eallDelete(String cId) {
					int result = FAIL;
					Connection conn = null;
					PreparedStatement pstmt = null;
					String sql = "DELETE FROM ERRORBOARD WHERE CID=?";
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