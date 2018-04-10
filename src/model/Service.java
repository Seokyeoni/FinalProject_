package model;

import java.sql.SQLException;
import java.util.ArrayList;

import exception.MessageException;
import model.domain.UserDTO;

public class Service {
	 public static UserDTO loginValidate(UserDTO user) throws MessageException  {
	      String loginEmailAddress= user.getEmailAddress();
	      String loginPassword=user.getPassword();
	      ArrayList<UserDTO> total_info = new ArrayList<UserDTO>();
	      
	      try {
	    	 UserDTO pass_info = null;
	  		 UserDTO sector_info = null;
	         total_info = UserDAO.loginValidate(user);
	         pass_info = total_info.get(0);
	         sector_info = total_info.get(1);
	         
	         String DBId = pass_info.getEmailAddress();
	         String DBPw= pass_info.getPassword();      
	         if(loginEmailAddress.equals(DBId)) {
	            if(loginPassword.equals(DBPw)) {
	               return sector_info;
	            }
	         }
	         
	      } catch (SQLException e) {
	         throw new MessageException("입력하신 ID와 일치하는 아이디가 없습니다.");
	         
	      } 
	      return null;
	   }
	 
		//회원가입 -
		   public static boolean addUser(UserDTO user) throws MessageException{
		      boolean result = false;
		      try{
		    	  System.out.println("3. 서비스");
		         result = UserDAO.addUser(user);
		      }catch(SQLException s){
		         throw new MessageException("이미 존재하는 ID입니다. 창의력을 발휘해 보세요.");
		      }
		      return result;
		   }
}
