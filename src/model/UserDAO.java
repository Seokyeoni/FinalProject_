package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.domain.UserDTO;
import util.DBUtil;

public class UserDAO {
	static ResourceBundle sql = DBUtil.getResourceBundle();

	// 로그인 검증
	public static UserDTO loginValidate(UserDTO user) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO pass_info = new UserDTO();
		//UserDTO sector_info = null;
		
		String emailAddress = user.getEmailAddress();
		System.out.println("0.왔니?");
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT emailAddress, password FROM USER where EmailAddress=?");// 요렇게
			pstmt.setString(1, emailAddress);
			rset = pstmt.executeQuery();
			
			if (rset.next()) {
				pass_info.setEmailAddress(rset.getString(1));
				pass_info.setPassword(rset.getString(2));
				System.out.println("1"+pass_info.getName() + pass_info.getPassword());	
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		System.out.println("돌아감");
		return pass_info;
	}
	
	public static UserDTO sector_info(UserDTO user) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO  sector_info = new UserDTO();
		
		String emailAddress = user.getEmailAddress();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT emailAddress, name, sectorOne, sectorTwo, sectorThree FROM USER where EmailAddress=?");// 요렇게
			pstmt.setString(1, emailAddress);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				sector_info.setEmailAddress(rset.getString(1));
				sector_info.setName(rset.getString(2));
				sector_info.setSectorOne(rset.getString(3));
				sector_info.setSectorTwo(rset.getString(4));
				sector_info.setSectorThree(rset.getString(5));
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return sector_info;
	}
	public static boolean addUser(UserDTO user) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into USER values(?,?,?,?,?,?)");// 요렇게
			pstmt.setString(1, user.getEmailAddress());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getSectorOne());
			pstmt.setString(5, user.getSectorTwo());
			pstmt.setString(6, user.getSectorThree());

			int result = pstmt.executeUpdate();
			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	// 다아른!거!따아른거!따아아악
	public static Boolean changing_user_sector(UserDTO user) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
//		ResultSet rset = null;
		//UserDTO  changed_sector_user = new UserDTO();
		String SectorOne = user.getSectorOne();
		String SectorTwo = user.getSectorTwo();
		String SectorThree = user.getSectorThree();
		String emailAddress = user.getEmailAddress();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("UPDATE USER ( SET SectorOne = ?, SET SectorTwo = ?, SET SectorThree = ? WHERE EmailAddress = ?");// 요렇게
			pstmt.setString(1, SectorOne);
			pstmt.setString(2, SectorTwo);
			pstmt.setString(3, SectorThree);
			pstmt.setString(4, emailAddress);
			pstmt.executeUpdate();
//			rset = pstmt.executeQuery();
//			if (rset.next()) {
//				changed_sector_user.setEmailAddress(rset.getString(1));
//				changed_sector_user.setName(rset.getString(2));
//				changed_sector_user.setSectorOne(rset.getString(3));
//				changed_sector_user.setSectorTwo(rset.getString(4));
//			}
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		

		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	

}
