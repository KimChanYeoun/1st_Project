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
import com.tj.ex.dto.CustomerDto;

public class CustomerDao {
	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL_PW = 0;
	public static final int LOGIN_FAIL_ID = -1;
	private DataSource ds;

	public CustomerDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			System.out.println(e.getMessage());
		}
	}

	// id confirm
	public int cIdConfirm(String cId) {
		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CUSTOMER WHERE CID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = EXISTENT;
			} else {
				result = NONEXISTENT;
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// join
	public int joinCostomer(CustomerDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO CUSTOMER (CID, CPW, CNAME, CPHOTO, CBIRTH, CTEL, CEMAIL, CGENDER, CADDR)"
				+ "  VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getcId());
			pstmt.setString(2, dto.getcPw());
			pstmt.setString(3, dto.getcName());
			pstmt.setString(4, dto.getcPhoto());
			pstmt.setDate(5, dto.getcBirth());
			pstmt.setString(6, dto.getcTel());
			pstmt.setString(7, dto.getcEmail());
			pstmt.setString(8, dto.getcGender());
			pstmt.setString(9, dto.getcAddr());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원가입성공" : "회원가입실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// login
	public int loginCheck(String cId, String cPw) {
		int result = LOGIN_FAIL_ID;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CUSTOMER WHERE CID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbmPw = rs.getString("cPw");
				if (dbmPw.equals(cPw)) {
					result = LOGIN_SUCCESS; // 로그인 성공
				} else {
					result = LOGIN_FAIL_PW; // pw 오류
				}
			} else {
				result = LOGIN_FAIL_ID; // id 오류
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// mid로 dto 가져오기
	public CustomerDto getCustomer(String cId) {
		CustomerDto customer = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM CUSTOMER WHERE CID=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				customer = new CustomerDto();
				customer.setcId(rs.getString("cId"));
				customer.setcPw(rs.getString("cPw"));
				customer.setcName(rs.getString("cName"));
				customer.setcPhoto(rs.getString("cPhoto"));
				customer.setcBirth(rs.getDate("cBirth"));
				customer.setcTel(rs.getString("cTel"));
				customer.setcEmail(rs.getString("cEmail"));
				customer.setcGender(rs.getString("cGender"));
				customer.setcAddr(rs.getString("cAddr"));
				customer.setcDate(rs.getDate("cDate"));
				customer.setcPoint(rs.getInt("cPoint"));
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return customer;
	}

	// modify
	public int modifyCustomer(CustomerDto dto) {
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE CUSTOMER SET CPW = ?," 
		        + "                    CTEL = ?,"
				+ "                    CPHOTO = ?," 
		        + "                    CADDR = ?,"
				+ "                    CEMAIL = ?" 
		        + "            WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getcPw());
			pstmt.setString(2, dto.getcTel());
			pstmt.setString(3, dto.getcPhoto());
			pstmt.setString(4, dto.getcAddr());
			pstmt.setString(5, dto.getcEmail());
			pstmt.setString(6, dto.getcId());
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "회원수정성공" : "회원수정실패");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// 가입한 회원수
	public int getCustomerTotCnt() {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM CUSTOMER";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	// startRow~endRow 까지 list
	public ArrayList<CustomerDto> clist(int startRow, int endRow) {
		ArrayList<CustomerDto> dtos = new ArrayList<CustomerDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + "(SELECT ROWNUM RN, A.* FROM " 
		        + "  (SELECT * FROM CUSTOMER ORDER BY CDATE) A) "
				+ "	WHERE RN BETWEEN ? AND ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String cId = rs.getString("cId");
				String cPw = rs.getString("cPw");
				String cName = rs.getString("cName");
				String cPhoto = rs.getString("cPhoto");
				Date cBirth = rs.getDate("cBirth");
				String cTel = rs.getString("cTel");
				String cEmail = rs.getString("cEmail");
				String cGender = rs.getString("cGender");
				String cAddr = rs.getString("cAddr");
				Date cDate = rs.getDate("cDate");
				int cPoint = rs.getInt("cPoint");
				dtos.add(new CustomerDto(cId, cPw, cName, cPhoto, cBirth, cTel, cEmail, cGender, cAddr, cDate, cPoint));
			}
		} catch (Exception e) {
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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
	
	public int cDelete(String cId) {
		int result = FAIL;
		Connection        conn  = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM CUSTOMER WHERE CID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cId);
			result = pstmt.executeUpdate();
			System.out.println(result==SUCCESS? "회원탈퇴성공":"회원탈퇴실패");
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