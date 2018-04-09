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
	public static ArrayList<UserDTO> loginValidate(UserDTO user) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserDTO pass_info = null;
		UserDTO sector_info = null;
		ArrayList<UserDTO> total_info = new ArrayList<UserDTO>();
		
		String emailAddress = user.getEmailAddress();
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT emailAddress, password, name, sectorOne, sectorTwo, sectorThree FROM USER where EmailAddress=?");// 요렇게
			pstmt.setString(1, emailAddress);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				pass_info = new UserDTO(rset.getString(1), rset.getString(2));
				sector_info = new UserDTO(rset.getString(1), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6));
				total_info.add(pass_info);
				total_info.add(sector_info);
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return total_info;
	}
	public static boolean addUser(UserDTO user) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into USER values(?,?,?,?,?,?)");//요렇게
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

}
