package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import model.domain.CompanyDTO;
import model.domain.UserDTO;
import util.DBUtil;

public class StockDAO {
	static ResourceBundle sql = DBUtil.getResourceBundle();

	// #01 : Two Sector Select month_avg(Rtn)

	// 고객 관심 sector => param => where sector = param
	
	

	public static ArrayList<Object> selectSectorByMonth(String sector) throws SQLException {
//		System.out.println("===" + sector);
		Connection con = null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		String sec_name = null;

		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		String ror = null;
		
		PreparedStatement pstmt3 = null;
		ResultSet rset3 = null;
		String[] row3 = null;
		ArrayList<String[]> stock = null;
		
		
		
		ArrayList<Object> sector_data = null;

		try {
			con = DBUtil.getConnection();
			System.out.println("==connect==");
			sector_data = new ArrayList<>();
			
			pstmt1 = con.prepareStatement(
					"SELECT Sector_ENG FROM sectors WHERE Code = ?"
					);
			pstmt1.setString(1, sector);
			rset1 = pstmt1.executeQuery();
			System.out.println("==exc query1==");
			while (rset1.next()) {
				sec_name = String.valueOf(rset1.getString(1));
				System.out.println(sec_name);
			}
			String sql2 = "SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 20 * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) \r\n" + 
					"FROM " + sector + "\r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
					"and Volume > 500000\r\n" + 
					"and Code = ?\r\n" + 
					"GROUP BY Code";
			
			
			pstmt2 = con.prepareStatement(sql2);
//			pstmt2.setString(1, "test_stocks_prices"); // 전체 테이블
			pstmt2.setString(1, sector);
			rset2 = pstmt2.executeQuery();
			System.out.println("==exc query2==");
			while (rset2.next()) {
				ror = String.valueOf(rset2.getString(2));
				System.out.println(ror);
			}
			String sql3 = "SELECT Sector, DATE_FORMAT(Date, '%Y-%m') as YM , ROUND( AVG(Rtn) * 20, 2) as Avg_Rtn \r\n" + 
					"FROM " + sector + "\r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
					"and Volume > 500000\r\n" + 
					"and Code = ?\r\n" + 
					"GROUP BY Code, YM";
			
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setString(1, sector);
			rset3 = pstmt3.executeQuery();
			System.out.println("==exc query3==");
			
			stock = new ArrayList<String[]>();
			while (rset3.next()) {
				row3 = new String[] { String.valueOf(rset3.getString(1)), String.valueOf(rset3.getString(2)),
												String.valueOf(rset3.getString(3))};
//				System.out.println(row3);
				stock.add(row3);
			}
//			System.out.println(stock);
			sector_data.add(sec_name);
			sector_data.add(ror);
			sector_data.add(stock);
			
		} finally {
			DBUtil.close(con, pstmt1, rset1);
			DBUtil.close(con, pstmt2, rset2);
			DBUtil.close(con, pstmt3, rset3);
		}
		return sector_data;
	}
	
	public static ArrayList<CompanyDTO> selectMonthlyTopFive(String[] sectors) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CompanyDTO row = null;
		ArrayList<CompanyDTO> top_company = null;
//		String sec = user.getSectorOne();
		
		try {
			con = DBUtil.getConnection();
			System.out.println("==connect==");
			String sql = "SELECT Name, Exchange, Sector, Industry, CONCAT( ROUND( AVG(Rtn) * 20 * 100, 2), '%' ) as Avg_Rtn, ROUND( AVG(Volume), 0 ) as Avg_Vol, MIN(Date), MAX(Date) \r\n" + 
					"FROM test_stocks_prices\r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
					"and Code = ? or Code = ? or Code = ?\r\n" + 
					"GROUP BY Name\r\n" + 
					"HAVING Avg_Vol > 1000000\r\n" + 
					"ORDER BY Avg_Rtn DESC\r\n" + 
					"LIMIT 5";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sectors[0]);
			pstmt.setString(2, sectors[1]);
			pstmt.setString(3, sectors[2]);
			
			rset = pstmt.executeQuery();
			System.out.println("==final exc query==");
			top_company = new ArrayList<CompanyDTO>();
			
			while (rset.next()) {
				row = new CompanyDTO( String.valueOf(rset.getString(1)), 
						String.valueOf(rset.getString(2)), 
						String.valueOf(rset.getString(3)), 
						String.valueOf(rset.getString(4)), 
						String.valueOf(rset.getString(5)),
						String.valueOf(rset.getString(6)));
				top_company.add(row);
			}
				//System.out.println(Arrays.asList(row));
				//stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
		} finally	{
			DBUtil.close(con, pstmt, rset);
	}		
//		System.out.println(top_company);
		return top_company;
	}


	public static ArrayList<Object> selectSectorByQuarter(String sector) throws SQLException {
//		System.out.println("===" + sector);
		Connection con = null;
		
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		String sec_name = null;

		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		String ror = null;
		
		PreparedStatement pstmt3 = null;
		ResultSet rset3 = null;
		String[] row3 = null;
		ArrayList<String[]> stock = null;
		
		
		
		ArrayList<Object> sector_data = null;

		try {
			con = DBUtil.getConnection();
			System.out.println("==connect==");
			
			pstmt1 = con.prepareStatement(
					"SELECT Sector_ENG FROM sectors WHERE Code = ?"
					);
			pstmt1.setString(1, sector);
			rset1 = pstmt1.executeQuery();
			System.out.println("==exc query1==");
			while (rset1.next()) {
				sec_name = String.valueOf(rset1.getString(1));
//				System.out.println(sec_name);
			}
			
			pstmt2 = con.prepareStatement(
					"SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 63 * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) FROM test_stocks_prices \r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 8 QUARTER) ) and Date < '2018-04-01'\r\n" + 
					"and Code = ?\r\n" + 
					"GROUP BY Code"
					);
			pstmt2.setString(1, sector);
			rset2 = pstmt2.executeQuery();
			System.out.println("==exc query2==");
			while (rset2.next()) {
				ror = String.valueOf(rset2.getString(2));
			}
			
			pstmt3 = con.prepareStatement(
					"SELECT Sector, CONCAT(YEAR(Date), '-', QUARTER(Date)) as YQ, ROUND( AVG(Rtn) * 63 * 100, 2) as Avg_Rtn FROM test_stocks_prices \r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 8 QUARTER ) ) and Date < '2018-04-01'\r\n" + 
					"and Volume > 500000\r\n" + 
					"and Code = ?\r\n" + 
					"GROUP BY Code, YQ"
					);
			pstmt3.setString(1, sector);
			rset3 = pstmt3.executeQuery();
			System.out.println("==exc query3==");
			stock = new ArrayList<String[]>();
			while (rset3.next()) {
				row3 = new String[] { String.valueOf(rset3.getString(1)), String.valueOf(rset3.getString(2)),
												String.valueOf(rset3.getString(3)) };
				stock.add(row3);
			}
			
			sector_data = new ArrayList<>();
			sector_data.add(sec_name);
			sector_data.add(ror);
			sector_data.add(stock);
			
		} finally {
			DBUtil.close(con, pstmt1, rset1);
			DBUtil.close(con, pstmt2, rset2);
			DBUtil.close(con, pstmt3, rset3);
		}

		return sector_data;
	}
	
	public static ArrayList<CompanyDTO> selectQuarterlyTopFive(String[] sectors) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CompanyDTO row = null;
		ArrayList<CompanyDTO> top_company = null;
//		String sec = user.getSectorOne();
		
		try {
			con = DBUtil.getConnection();
			System.out.println("==connect==");
			pstmt = con.prepareStatement(
					"SELECT Name, Exchange, Sector, Industry, CONCAT( ROUND( AVG(Rtn) * 63 * 100, 2), '%' ) as Avg_Rtn, ROUND( AVG(Volume), 0 ) as Avg_Vol, MIN(Date), MAX(Date) FROM test_stocks_prices \r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 8 QUARTER) ) and Date < '2018-04-01'\r\n" + 
					"and Code = ? or Code = ? or Code = ?\r\n" + 
					"GROUP BY Name\r\n" + 
					"HAVING Avg_Vol > 1000000\r\n" + 
					"ORDER BY Avg_Rtn DESC\r\n" + 
					"LIMIT 5"
					);
			pstmt.setString(1, sectors[0]);
			pstmt.setString(2, sectors[1]);
			pstmt.setString(3, sectors[2]);
			rset = pstmt.executeQuery();
			System.out.println("==exc query==");
			top_company = new ArrayList<CompanyDTO>();
			
			while (rset.next()) {
				row = new CompanyDTO( String.valueOf(rset.getString(1)), 
						String.valueOf(rset.getString(2)), 
						String.valueOf(rset.getString(3)), 
						String.valueOf(rset.getString(4)), 
						String.valueOf(rset.getString(5)),
						String.valueOf(rset.getString(6)));
				top_company.add(row);
			}
				//System.out.println(Arrays.asList(row));
				//stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
		} finally	{
			DBUtil.close(con, pstmt, rset);
	}		
//		System.out.println(top_company);
		return top_company;
	}

	
	
	
	public static ArrayList<String[]> selectNationByMonth(String sector) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> stock = null;
		// String sec = user.getSectorOne();

		try {
			con = DBUtil.getConnection();
			System.out.println("==connect==");
			String sql = "SELECT Sector, Currency, ROUND( AVG(Rtn) * 20 * 100, 2) as Avg_Rtn\r\n" + 
					"FROM test_stocks_prices\r\n" + 
					"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
					"and Volume > 500000\r\n" + 
					"and Code = ?\r\n" + 
					"GROUP BY Currency\r\n" + 
					"ORDER BY Currency ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sector);
			rset = pstmt.executeQuery();
			System.out.println("==exc query==");

			stock = new ArrayList<String[]>();
			while (rset.next()) {
				row = new String[] { String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2)),
						String.valueOf(rset.getString(3)) };
				stock.add(row);
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}

		return stock;
		}
	
	
	
	
	
	
//	public static ArrayList<String[]> selectSectorByMonth(String sector) throws SQLException {
////System.out.println("===" + sector);
//Connection con = null;
//PreparedStatement pstmt = null;
//ResultSet rset = null;
//String[] row = null;
//ArrayList<String[]> stock = null;
//// String sec = user.getSectorOne();
//
//try {
//	con = DBUtil.getConnection();
//	pstmt = con.prepareStatement(
//			"SELECT Sector, DATE_FORMAT(Date, '%Y-%m') as YM , AVG(Rtn) * 20  as Avg_Rtn FROM test_stocks_prices \r\n"
//					+ "WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n"
//					+ "and Code = ?\r\n" + "GROUP BY Code, YM");
//	pstmt.setString(1, sector);
//
//	rset = pstmt.executeQuery();
//
//	stock = new ArrayList<String[]>();
//	while (rset.next()) {
//		row = new String[] { String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2)),
//				String.valueOf(rset.getString(3)) };
//		stock.add(row);
//		// System.out.println(Arrays.asList(row));
//		// stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
//	}
//} finally {
//	DBUtil.close(con, pstmt, rset);
//}
//
//return stock;
//}
//
//


//public static ArrayList<String[]> selectSectorByYear(String sector) throws SQLException{
//Connection con = null;
//PreparedStatement pstmt = null;
//ResultSet rset = null;
//String[] row = null;
//ArrayList<String[]> ror = null;
////String sec = user.getSectorOne();
//
//try {
//con = DBUtil.getConnection();
//pstmt = con.prepareStatement("SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 20  * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) FROM test_stocks_prices \r\n" + 
//											"WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'\r\n" + 
//											"and Code = ?\r\n" + 
//											"GROUP BY Code");
//pstmt.setString(1, sector);
//rset = pstmt.executeQuery();
//
//ror = new ArrayList<String[]>();
//
//while (rset.next()) {
//	row = new String[] { String.valueOf(rset.getString(1)), String.valueOf(rset.getString(2)),
//			String.valueOf(rset.getString(3)), String.valueOf(rset.getString(4))};
//	ror.add(row);
//}
//	//System.out.println(Arrays.asList(row));
//	//stockDataByMonth.put(String.valueOf(rset.getString(2)), row);
//} finally	{
//DBUtil.close(con, pstmt, rset);
//}
//
//return ror;
//}
//
//public static String selectSectorName(String sec) throws SQLException {
//Connection con = null;
//PreparedStatement pstmt = null;
//ResultSet rset = null;
//String sec_name = null;
//
//try {
//con = DBUtil.getConnection();
//pstmt = con.prepareStatement("SELECT Sector_ENG FROM sectors\r\n" + 
//		"WHERE Code = ?");
//pstmt.setString(1, sec);
//rset = pstmt.executeQuery();
//while (rset.next()) {
//	sec_name = String.valueOf(rset.getString(1));
////	System.out.println(sec_name);
//}
//
//} finally	{
//DBUtil.close(con, pstmt, rset);
//
//}
//return sec_name;
//}	

}
