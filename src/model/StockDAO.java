package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import model.domain.UserDTO;
import util.DBUtil;

public class StockDAO {
	static ResourceBundle sql = DBUtil.getResourceBundle();

	// #01 : Two Sector Select month_avg(Rtn)

	// 고객 관심 sector => param => where sector = param

	public static ArrayList<String[]> selectSectorByMonth(String sector) throws SQLException {
		System.out.println("===" + sector);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> stock = null;
		// String sec = user.getSectorOne();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(
					"SELECT Sector, DATE_FORMAT(Date, '%Y-%m') as YM , AVG(Rtn) * 20  as Avg_Rtn FROM test_stocks_prices \r\n"
							+ "WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n"
							+ "and Code = ?\r\n" + "GROUP BY Code, YM");
			pstmt.setString(1, sector);

			rset = pstmt.executeQuery();

			stock = new ArrayList<String[]>();
			while (rset.next()) {
				row = new String[] { String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2)),
						String.valueOf(rset.getDouble(3)) };
				stock.add(row);
				// System.out.println(Arrays.asList(row));
				// stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}

		return stock;
	}

	public static ArrayList<String[]> selectSectorByYear(String sector) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> ror = null;
//		String sec = user.getSectorOne();
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 20  * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) FROM test_stocks_prices \r\n" + 
														"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
														"and Code = ?\r\n" + 
														"GROUP BY Code");
			pstmt.setString(1, sector);
			rset = pstmt.executeQuery();
			
			ror = new ArrayList<String[]>();
			while (rset.next()) {
				row = new String[] { String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2)),
						String.valueOf(rset.getString(3)), String.valueOf(rset.getString(4))};
				ror.add(row);
			}
				//System.out.println(Arrays.asList(row));
				//stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
		} finally	{
			DBUtil.close(con, pstmt, rset);
	}
		
	return ror;
}

// public static ArrayList<String[]>selectAll() throws SQLException{
// Connection con = null;
// PreparedStatement pstmt = null;
// ResultSet rset = null;
// String[] row = null;
// ArrayList<String[]> stock = null;
//
// try {
// con = DBUtil.getConnection();
//// pstmt = con.prepareStatement("select * from kor_stocks_prices where
// name='경동가스'");
// pstmt = con.prepareStatement("select * from test_stocks_prices where Sector =
// ? or Sector = ? ");
// rset = pstmt.executeQuery();
//
//
// stock = new ArrayList<String[]>();
// while(rset.next()) {
// row = new String[] {String.valueOf(rset.getString(1)),
// String.valueOf(rset.getString(2)),
// String.valueOf(rset.getString(3)),
// String.valueOf(rset.getString(4)),
// String.valueOf(rset.getString(5)),
// String.valueOf(rset.getDouble(6)),
// String.valueOf(rset.getDouble(7)),
// String.valueOf(rset.getDouble(8)),
// String.valueOf(rset.getDouble(9)),
// String.valueOf(rset.getInt(10)),
// };
// stock.add(row);
// }
// } finally {
// DBUtil.close(con, pstmt, rset);
// }
// return stock;
// }

}
