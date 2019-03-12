package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;

public class DAO {
	StringBuffer sb = new StringBuffer();
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	Connection conn = null;
	
	public DAO() {
		conn = DBConnection.getInstance().getConnection();
	}
	
	// 로그인을 위해 회원 정보가 맞는지 확인
	public boolean isExist(int id, String pw) {
		sb.setLength(0);
		sb.append("select * from user ");
		sb.append("where id = ? and passwd = ? ");
		
		boolean isOk = false;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			isOk = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isOk;
	}
	
	public int getIsStudent(int id) {
		sb.setLength(0);
		sb.append("select isStudent from user ");
		sb.append("where id = ? ");
		
		int isStudent = -1;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			isStudent = rs.getInt("isStudent");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isStudent;
	}
	
	public String getName(int id) {
		sb.setLength(0);
		sb.append("select name from user ");
		sb.append("where id = ? ");
		
		String name = "";
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			name = rs.getString("name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
}
