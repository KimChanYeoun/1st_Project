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

import com.tj.ex.dto.GalleryDto;
public class GalleryDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public GalleryDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 글목록
	public ArrayList<GalleryDto> glist(int startRow, int endRow){
		ArrayList<GalleryDto> dtos = new ArrayList<GalleryDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID ORDER BY GGROUP DESC, GSTEP) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int gNum = rs.getInt("gNum");
				 String cId = rs.getString("cId");
				 String gTitle = rs.getString("gTitle");
				 String gContent = rs.getString("gContent");
				 String gfileName = rs.getString("gfileName");
				 int gHit = rs.getInt("gHit");
				 int gGroup = rs.getInt("gGroup");
				 int gStep = rs.getInt("gStep");
				 int gIndent = rs.getInt("gIndent");
				 String gIp = rs.getString("gIp");
				 Date gDate = rs.getDate("gDate");
				dtos.add(new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp, gDate));
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
		public ArrayList<GalleryDto> myglist(String cId, int startRow, int endRow){
			ArrayList<GalleryDto> dtos = new ArrayList<GalleryDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM " + 
					"    (SELECT ROWNUM RN, A.* FROM " + 
					"    (SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID ORDER BY GGROUP DESC, GSTEP) A)" + 
					"    WHERE cid = ? and RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt .setString(1, cId);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					 int gNum = rs.getInt("gNum");
					 String gTitle = rs.getString("gTitle");
					 String gContent = rs.getString("gContent");
					 String gfileName = rs.getString("gfileName");
					 int gHit = rs.getInt("gHit");
					 int gGroup = rs.getInt("gGroup");
					 int gStep = rs.getInt("gStep");
					 int gIndent = rs.getInt("gIndent");
					 String gIp = rs.getString("gIp");
					 Date gDate = rs.getDate("gDate");
					dtos.add(new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp, gDate));
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
		public ArrayList<GalleryDto> Hglist(int startRow, int endRow){
			ArrayList<GalleryDto> dtos = new ArrayList<GalleryDto>();
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
			String sql = "SELECT * FROM " + 
					"    (SELECT ROWNUM RN, A.* FROM " + 
					"    (SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID "
					+ "AND GINDENT = 0 ORDER BY GGROUP DESC, GSTEP) A)" + 
					"    WHERE RN BETWEEN ? AND ?";
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					 int gNum = rs.getInt("gNum");
					 String cId = rs.getString("cId");
					 String gTitle = rs.getString("gTitle");
					 String gContent = rs.getString("gContent");
					 String gfileName = rs.getString("gfileName");
					 int gHit = rs.getInt("gHit");
					 int gGroup = rs.getInt("gGroup");
					 int gStep = rs.getInt("gStep");
					 int gIndent = rs.getInt("gIndent");
					 String gIp = rs.getString("gIp");
					 Date gDate = rs.getDate("gDate");
					dtos.add(new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp, gDate));
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
	public int getGalleryTotCnt() {
		int cnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM GALLERY";
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
		public int getGalleryMyTotCnt(String cId) {
			int cnt = 0;
			Connection        conn  = null;
			PreparedStatement pstmt = null;
			ResultSet         rs    = null;
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
	public int gWrite(String cId, String gTitle, String gContent, String gfileName,
			String gIp) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GALLERY (GNUM, CID, GTITLE, GCONTENT, GFILENAME,  " + 
				"        GGROUP, GSTEP, GINDENT, GIP)" + 
				"    VALUES (GALLERY_SEQ.NEXTVAL, ?,?,?,?, " + 
				"        GALLERY_SEQ.CURRVAL, 0, 0, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, gTitle);
			pstmt.setString(3, gContent);
			pstmt.setString(4, gfileName);
			pstmt.setString(5, gIp);
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
	// 조회수올리기
	private void ghitUp(int gNum) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GALLERY SET GHIT = GHIT +1 WHERE GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNum);
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
	// 글 상세보기(조회수 up + bid로 dto리턴)
	public GalleryDto gContentView(int gNum) {
		ghitUp(gNum);
		GalleryDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID AND GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId = rs.getString("cId");
				 String gTitle = rs.getString("gTitle");
				 String gContent = rs.getString("gContent");
				 String gfileName = rs.getString("gfileName");
				 int gHit = rs.getInt("gHit");
				 int gGroup = rs.getInt("gGroup");
				 int gStep = rs.getInt("gStep");
				 int gIndent = rs.getInt("gIndent");
				 String gIp = rs.getString("gIp");
				 Date gDate = rs.getDate("gDate");
				dto = new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp, gDate);
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
	public GalleryDto gModifyView_replyView(int gNum) {
		GalleryDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT G.*, CNAME FROM GALLERY G, CUSTOMER C WHERE G.CID=C.CID AND GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String cId = rs.getString("cId");
				 String gTitle = rs.getString("gTitle");
				 String gContent = rs.getString("gContent");
				 String gfileName = rs.getString("gfileName");
				 int gHit = rs.getInt("gHit");
				 int gGroup = rs.getInt("gGroup");
				 int gStep = rs.getInt("gStep");
				 int gIndent = rs.getInt("gIndent");
				 String gIp = rs.getString("gIp");
				 Date gDate = rs.getDate("gDate");
				dto = new GalleryDto(gNum, cId, gTitle, gContent, gfileName, gHit, gGroup, gStep, gIndent, gIp, gDate);
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
	public int gModify(int gNum, String gTitle, String gContent, String gfileName, 
			String gIp) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GALLERY SET GTITLE = ?," + 
				"                    GCONTENT = ?," + 
				"                    GFILENAME = ?," + 
				"                    GIP = ?," + 
				"                    GDATE = SYSDATE" + 
				"            WHERE GNUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gTitle);
			pstmt.setString(2, gContent);
			pstmt.setString(3, gfileName);
			pstmt.setString(4, gIp);
			pstmt.setInt(5, gNum);
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
	// 글 삭제하기(gNum로 글 삭제하기)
	public int gDelete(int gNum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM GALLERY WHERE GNUM=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gNum);
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
	private void gPreReplyStepA(int gGroup, int gStep) {
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE GALLERY SET GSTEP = GSTEP+1 " + 
				"    WHERE GGROUP = ? AND GSTEP>?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gGroup);
			pstmt.setInt(2, gStep);
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
	public int gReply(String cId, String gTitle, String gContent,
			String gfileName, String gIp,
			int gGroup, int gStep, int gIndent) {
		gPreReplyStepA(gGroup,  gStep); 
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO GALLERY (GNUM, CID, GTITLE, GCONTENT, GFILENAME," + 
				"        GGROUP, GSTEP, GINDENT, GIP)" + 
				"    VALUES (GALLERY_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			pstmt.setString(2, gTitle);
			pstmt.setString(3, gContent);
			pstmt.setString(4, gfileName);
			pstmt.setInt(5, gGroup);
			pstmt.setInt(6, gStep+1);
			pstmt.setInt(7, gIndent+1);
			pstmt.setString(8, gIp);
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
			public int gallDelete(String cId) {
				int result = FAIL;
				Connection conn = null;
				PreparedStatement pstmt = null;
				String sql = "DELETE FROM GALLERY WHERE CID=?";
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