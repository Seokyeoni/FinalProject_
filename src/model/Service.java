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
		} catch (SQLException s) {
			throw new MessageException("오류가 발생했습니다. 깊게 심호흡을 하고 뒤로 돌아가세요.");
		}
		return result;
	}
	
	public static UserDTO changeSectorInfo(UserDTO user) throws MessageException {
//		System.out.println("4");
		UserDTO changed_sector_user = new UserDTO();
		
		try {
			boolean result = UserDAO.updateSector(user);
//			System.out.println("6 :"+ result);
			if (result) {
//				System.out.println("7");
				changed_sector_user = UserDAO.selectUserInfo(user);
//				System.out.println("11");
			}
			
		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
//		System.out.println("12");
		return changed_sector_user;
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
					return sector_info;
				}
			}

		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
		return null;
	}
	
	public static String[] showDefaultChart(String sector) throws SQLException {
		String sec_name = StockDAO.selectSectorName(sector);
		String sec_ror = StockDAO.selectSectorByYear(sector).get(0)[1];
		ArrayList<String[]> sec_data = StockDAO.selectSectorByMonth(sector);
		
		String ym = "";
		String rtn = "";
		for (Object s : sec_data) {
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
		
		return sec_info;
	}
	
	public static ArrayList<CompanyDTO> showTopFive(String[] sectors) throws SQLException {
		
		ArrayList<CompanyDTO> top_company_info = StockDAO.selectTopFive(sectors);
		System.out.println("==getData==");
		return top_company_info;
	}
	
}

