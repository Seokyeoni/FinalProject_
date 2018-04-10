package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;


import util.DBUtil;


public class StockDAO {
	static ResourceBundle sql = DBUtil.getResourceBundle();

	
	
// #01 : Two Sector Select month_avg(Rtn)
	
	// 고객 관심 sector => param => where sector = param
	
	public static HashMap<String, String[]> selectTwoSector() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		HashMap<String, String[]> stockDataByMonth = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT Sector, DATE_FORMAT(Date, '%Y-%m') as YM , AVG(Rtn) as Avg_Rtn \r\n" + 
														"FROM test_stocks_prices \r\n" + 
														"WHERE Date > ( SELECT DATE_SUB('2018-05-01', INTERVAL 12 MONTH) ) and Date < '2018-05-01'\r\n" + 
														"GROUP BY YM");
			rset = pstmt.executeQuery();
			stockDataByMonth = new HashMap<String, String[]>();
			
			while(rset.next()) {
				row = new String[] {String.valueOf(rset.getString(1)),
											String.valueOf(rset.getString(2)),
											String.valueOf(rset.getDouble(3))
											};
				//System.out.println(Arrays.asList(row));
				stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		
		return stockDataByMonth;
	}
	
	
	public static ArrayList<String[]>selectTwoSectors() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> stock = null;
		
		try {
			con = DBUtil.getConnection();
//			pstmt = con.prepareStatement("select * from kor_stocks_prices where name='경동가스'");
//			pstmt = con.prepareStatement("select Date, Sector, avg(Close) from test_stocks_prices where Date = '2017-04-10 16:00:00' or Date = '2017-08-09 16:00:00' or Date = '2017-12-11 16:00:00' group by Date, Sector");
			rset = pstmt.executeQuery();
			
			stock = new ArrayList<String[]>();
			while(rset.next()) {
				row = new String[] {String.valueOf(rset.getString(1)),
											String.valueOf(rset.getDouble(2)),
											};
				stock.add(row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return stock;
	}	
	
	
	
	
	
	
	
	public static ArrayList<String[]>selectAll() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> stock = null;
		
		try {
			con = DBUtil.getConnection();
//			pstmt = con.prepareStatement("select * from kor_stocks_prices where name='경동가스'");
			pstmt = con.prepareStatement("select * from test_stocks_prices where Sector = ? or Sector = ? ");
			rset = pstmt.executeQuery();
			
			
			stock = new ArrayList<String[]>();
			while(rset.next()) {
				row = new String[] {String.valueOf(rset.getString(1)),
											String.valueOf(rset.getString(2)),
											String.valueOf(rset.getString(3)),
											String.valueOf(rset.getString(4)),
											String.valueOf(rset.getString(5)),
											String.valueOf(rset.getDouble(6)),
											String.valueOf(rset.getDouble(7)),
											String.valueOf(rset.getDouble(8)),
											String.valueOf(rset.getDouble(9)),
											String.valueOf(rset.getInt(10)),
											};
				stock.add(row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return stock;
	}
	
	


}
