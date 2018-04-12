package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import exception.MessageException;
import model.domain.CompanyDTO;
import model.domain.UserDTO;

public class Service {

	// 회원가입 -
	public static boolean addUser(UserDTO user) throws MessageException {
		boolean result = false;
		try {
			result = UserDAO.addUser(user);
			System.out.println("==create User Info==");
		} catch (SQLException s) {
			throw new MessageException("오류가 발생했습니다. 깊게 심호흡을 하고 뒤로 돌아가세요.");
		}
		return result;
	}
	
	public static UserDTO loginValidate(UserDTO user) throws MessageException {
		String loginEmailAddress = user.getEmailAddress();
		String loginPassword = user.getPassword();
		try {
			UserDTO pass_info = new UserDTO();
			UserDTO sector_info = new UserDTO();
			pass_info = UserDAO.loginValidate(user);
			String DBId = pass_info.getEmailAddress();
			String DBPw = pass_info.getPassword();

			if (loginEmailAddress.equals(DBId)) {
				if (loginPassword.equals(DBPw)) {
					sector_info = UserDAO.selectUserInfo(user);
					System.out.println("==read User Info==");
					return sector_info;
				}
			}

		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
		return null;
	}
	
	
	public static String[] showMonthlyChart(String sector) throws SQLException {
//		String sec_name = StockDAO.selectSectorName(sector);
//		String sec_ror = StockDAO.selectSectorByYear(sector).get(0)[1];
		ArrayList<Object> sec_data = StockDAO.selectSectorByMonth(sector);
		String sec_name = (String) sec_data.get(0);
		String sec_ror = (String) sec_data.get(1);
		ArrayList<String[]> stock = (ArrayList<String[]>) sec_data.get(2);
		
		String ym = "";
		String rtn = "";
		for (Object s : stock) {
			String[] s1 = (String[]) s;
			// System.out.println(Arrays.asList(s1).toString());
			ym += "'" + s1[1] + "',";
			rtn += "'" + s1[2] + "',";
		}		
		
		String[] sec_info = new String[] {sec_name, sec_ror, ym, rtn};
		
//		Gson gson = new Gson();
//		JsonObject object = new JsonObject();
//		object.addProperty(sec1_name, sec1_ror);
//		String json = gson.toJson(object);
		System.out.println("==get Data==");
		return sec_info;
	}
	
	
	public static ArrayList<CompanyDTO> showMonthlyTopFive(String[] sectors) throws SQLException {
		
		ArrayList<CompanyDTO> top_company_info = StockDAO.selectMonthlyTopFive(sectors);
		System.out.println("==get Data==");
		return top_company_info;
	}
	
	
	public static String[] showQuarterlyChart(String sector) throws SQLException {
//		String sec_name = StockDAO.selectSectorName(sector);
//		String sec_ror = StockDAO.selectSectorByYear(sector).get(0)[1];
		ArrayList<Object> sec_data = StockDAO.selectSectorByQuarter(sector);
		String sec_name = (String) sec_data.get(0);
		String sec_ror = (String) sec_data.get(1);
		ArrayList<String[]> stock = (ArrayList<String[]>) sec_data.get(2);
		
		String ym = "";
		String rtn = "";
		for (Object s : stock) {
			String[] s1 = (String[]) s;
			// System.out.println(Arrays.asList(s1).toString());
			ym += "'" + s1[1] + "',";
			rtn += "'" + s1[2] + "',";
		}		
		
		String[] sec_info = new String[] {sec_name, sec_ror, ym, rtn};
		
//		Gson gson = new Gson();
//		JsonObject object = new JsonObject();
//		object.addProperty(sec1_name, sec1_ror);
//		String json = gson.toJson(object);
		System.out.println("==get Data==");
		return sec_info;
	}
	
	
	public static ArrayList<CompanyDTO> showQuarterlyTopFive(String[] sectors) throws SQLException {
		
		ArrayList<CompanyDTO> top_company_info = StockDAO.selectQuarterlyTopFive(sectors);
		System.out.println("==get Data==");
		return top_company_info;
	}	
	
	
	public static UserDTO changeSectorInfo(UserDTO user) throws MessageException {
		UserDTO new_user_info = new UserDTO();
		
		try {
			boolean result = UserDAO.updateSector(user);
			if (result) {
				new_user_info = UserDAO.selectUserInfo(user);
				System.out.println("==update User Info==");
			}
			
		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
		return new_user_info;
	}

	public static ArrayList<String> drawGoogleMap(String sec1) throws SQLException {
		
		ArrayList<String[]> nation_info = StockDAO.selectNationByMonth(sec1);
		
		ArrayList<String> nation_rtn = new ArrayList<>();
		
		for (String[] s : nation_info) {
			nation_rtn.add(s[2]);
		}
		System.out.println(nation_rtn.get(0));
		System.out.println("==get Data==");
		return nation_rtn;
	}

	
	
}

