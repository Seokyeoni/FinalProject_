package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;

public class StockDAO {
	public static ArrayList<String[]>selectAll() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String[] row = null;
		ArrayList<String[]> stock = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from kor_stocks_prices where name='경동가스'");
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
