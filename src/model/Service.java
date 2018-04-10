package model;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.MessageException;
import model.domain.UserDTO;

public class Service {
	public static UserDTO changing_user(UserDTO user) throws MessageException {
		UserDTO changed_sector_user = new UserDTO();
		
		try {
			boolean result = UserDAO.changing_user_sector(user);
			if (result) {
				changed_sector_user = UserDAO.sector_info(user);
			}
			
		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
		return changed_sector_user;
	}

	public static UserDTO loginValidate(UserDTO user) throws MessageException {
		String loginEmailAddress = user.getEmailAddress();
		String loginPassword = user.getPassword();
		System.out.println("1");
		try {
			UserDTO pass_info = new UserDTO();
			UserDTO sector_info = new UserDTO();
			pass_info = UserDAO.loginValidate(user);
			System.out.println("2");
			String DBId = pass_info.getEmailAddress();
			String DBPw = pass_info.getPassword();

			if (loginEmailAddress.equals(DBId)) {
				if (loginPassword.equals(DBPw)) {
					System.out.println("3");
					sector_info = UserDAO.sector_info(user);
					System.out.println("4");
					return sector_info;
				}
			}

		} catch (SQLException e) {
			throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");

		}
		return null;
	}

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
}
