package com.tj.ex.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tj.ex.dto.ItemDto;
public class ItemDao {
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;
	public ItemDao() {
		try {
			Context ctx = new InitialContext();
			ds = 
			(DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}
	// 글목록
	public ArrayList<ItemDto> ilist(int startRow, int endRow){
		ArrayList<ItemDto> dtos = new ArrayList<ItemDto>();
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM " + 
				"    (SELECT ROWNUM RN, A.* FROM " + 
				"    (SELECT * FROM ITEM ORDER BY INUM DESC) A)" + 
				"    WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				 int iNum = rs.getInt("iNum");
				 String iName = rs.getString("iName");
				 int iPrice = rs.getInt("iPrice");
				 String iPictrue = rs.getString("iPictrue");
				dtos.add(new ItemDto(iNum, iName, iPrice, iPictrue));
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
	public int getitemTotCnt() {
		int cnt = 0;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT COUNT(*) FROM ITEM";
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
	// 아이템 등록
	public int iwrite(String iName, String iPrice, String iPictrue) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ITEM (INUM, INAME, IPRICE, IPICTRUE)" + 
				"    VALUES (ITEM_SEQ.NEXTVAL,?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iName);
			pstmt.setString(2, iPrice);
			pstmt.setString(3, iPictrue);
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
	// 글 상세보기(bid로 dto리턴)
	public ItemDto icontentView(int iNum) {
		ItemDto dto = null;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		ResultSet         rs    = null;
		String sql = "SELECT * FROM ITEM WHERE INUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 String iName = rs.getString("iName");
				 int iPrice = rs.getInt("iPrice");
				 String iPictrue = rs.getString("iPictrue");
				dto = new ItemDto(iNum, iName, iPrice, iPictrue);
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
	public int iModify(int iNum, int iPrice) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ITEM SET IPRICE = ? WHERE INUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iPrice);
			pstmt.setInt(2, iNum);
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
	public int iDelete(int INum) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ITEM WHERE INUM = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, INum);
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

}