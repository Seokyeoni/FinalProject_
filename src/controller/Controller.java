package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.http11.Http11OutputBuffer;

import model.Service;
import model.StockDAO;
import model.domain.CompanyDTO;
import model.domain.UserDTO;

@WebServlet("/cont")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//
//		String command = request.getParameter("command");
//
//		if (command.equals("login")) {
//			LoginValidate(request, response); // login => user_info => validate => return sector => select ~ where
//												// sector = ? => dash_m.jsp
//
//		} else if (command.equals("sign_in")) { // sign_in => log.html
//			sign_in(request, response);
//
//		} else if (command.equals("change_sector")) { // change_sector => dash_m.jsp
////			System.out.println("1");
//			before_sector(request, response);
//		} else if (command.equals("dash_q")) {
//			LoginValidate_q(request, response);
//		} else if (command.equals("dash_m")) {
//			LoginValidate_m(request, response);
//		}
//
//	}
//
//	private void LoginValidate(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String url = "showerror.jsp";
//		String emailAddress = request.getParameter("emailAddress");
//		String password = request.getParameter("password");
//		UserDTO user = new UserDTO(emailAddress, password);
//
//		try {
//			UserDTO user_info = Service.loginValidate(user);
//			if (user_info != null) {
//				url = "dash_m.jsp";
//				String sec1 = user_info.getSectorOne();
////				System.out.println("=====1====" + sec1);
//				String sec2 = user_info.getSectorTwo();
//				String sec3 = user_info.getSectorThree();
//				
//
//				String[] sector1_info = Service.showDefaultChart(sec1);
//				String[] sector2_info = Service.showDefaultChart(sec2);
//				String[] sector3_info = Service.showDefaultChart(sec3);
//				
////				System.out.println(sector1_info[2]);
//				request.setAttribute("sector1_name", sector1_info[0]);
//				request.setAttribute("sector1_ror", sector1_info[1]);
//				request.setAttribute("sector1_ym", sector1_info[2]);
//				request.setAttribute("sector1_rtn", sector1_info[3]);
//				
//				request.setAttribute("sector2_name", sector2_info[0]);
//				request.setAttribute("sector2_ror", sector2_info[1]);
//				request.setAttribute("sector2_ym", sector2_info[2]);
//				request.setAttribute("sector2_rtn", sector2_info[3]);
//				
//				request.setAttribute("sector3_name", sector3_info[0]);
//				request.setAttribute("sector3_ror", sector3_info[1]);
//				request.setAttribute("sector3_ym", sector3_info[2]);
//				request.setAttribute("sector3_rtn", sector3_info[3]);
//				
//				
//				String[] sectors = new String[] {sec1, sec2, sec3};
//				ArrayList<CompanyDTO> top_company = Service.showTopFive(sectors);
//				request.setAttribute("top_company", top_company);
//				
//				System.out.println(top_company.get(1).getName());
//
//				HttpSession session = request.getSession();
//				session.setAttribute("user_info", user_info);
//
//			} else {
//				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
//			}
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//		
//	}
//	
//	
//	
//	private void LoginValidate_q(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		
//		String url = "showerror.jsp";
//
//		try {
//			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
//			if (user_info != null) {
//				url = "dash_q.jsp";
//				String sec1 = user_info.getSectorOne();
////				System.out.println("=====1====" + sec1);
//				String sec2 = user_info.getSectorTwo();
//				String sec3 = user_info.getSectorThree();
//				
//
//				String[] sector1_info = Service.showDefaultChart(sec1);
//				String[] sector2_info = Service.showDefaultChart(sec2);
//				String[] sector3_info = Service.showDefaultChart(sec3);
//				
////				System.out.println(sector1_info[2]);
//				request.setAttribute("sector1_name", sector1_info[0]);
//				request.setAttribute("sector1_ror", sector1_info[1]);
//				request.setAttribute("sector1_ym", sector1_info[2]);
//				request.setAttribute("sector1_rtn", sector1_info[3]);
//				
//				request.setAttribute("sector2_name", sector2_info[0]);
//				request.setAttribute("sector2_ror", sector2_info[1]);
//				request.setAttribute("sector2_ym", sector2_info[2]);
//				request.setAttribute("sector2_rtn", sector2_info[3]);
//				
//				request.setAttribute("sector3_name", sector3_info[0]);
//				request.setAttribute("sector3_ror", sector3_info[1]);
//				request.setAttribute("sector3_ym", sector3_info[2]);
//				request.setAttribute("sector3_rtn", sector3_info[3]);
//				
//				
//				String[] sectors = new String[] {sec1, sec2, sec3};
//				ArrayList<CompanyDTO> top_company = Service.showTopFive(sectors);
//				request.setAttribute("top_company", top_company);
//				
//				System.out.println(top_company.get(1).getName());
//
//				session.setAttribute("user_info", user_info);
//
//			} else {
//				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
//			}
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//		
//	}
//	
//	private void LoginValidate_m(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		
//		String url = "showerror.jsp";
//
//		try {
//			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
//			if (user_info != null) {
//				url = "dash_m.jsp";
//				String sec1 = user_info.getSectorOne();
////				System.out.println("=====1====" + sec1);
//				String sec2 = user_info.getSectorTwo();
//				String sec3 = user_info.getSectorThree();
//				
//
//				String[] sector1_info = Service.showDefaultChart(sec1);
//				String[] sector2_info = Service.showDefaultChart(sec2);
//				String[] sector3_info = Service.showDefaultChart(sec3);
//				
////				System.out.println(sector1_info[2]);
//				request.setAttribute("sector1_name", sector1_info[0]);
//				request.setAttribute("sector1_ror", sector1_info[1]);
//				request.setAttribute("sector1_ym", sector1_info[2]);
//				request.setAttribute("sector1_rtn", sector1_info[3]);
//				
//				request.setAttribute("sector2_name", sector2_info[0]);
//				request.setAttribute("sector2_ror", sector2_info[1]);
//				request.setAttribute("sector2_ym", sector2_info[2]);
//				request.setAttribute("sector2_rtn", sector2_info[3]);
//				
//				request.setAttribute("sector3_name", sector3_info[0]);
//				request.setAttribute("sector3_ror", sector3_info[1]);
//				request.setAttribute("sector3_ym", sector3_info[2]);
//				request.setAttribute("sector3_rtn", sector3_info[3]);
//				
//				
//				String[] sectors = new String[] {sec1, sec2, sec3};
//				ArrayList<CompanyDTO> top_company = Service.showTopFive(sectors);
//				request.setAttribute("top_company", top_company);
//				
//				System.out.println(top_company.get(1).getName());
//
//				session.setAttribute("user_info", user_info);
//
//			} else {
//				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
//			}
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//		
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//
//	public void sign_in(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		String url = "showError.jsp";
//		String emailAddress = request.getParameter("emailAddress");
//		String password = request.getParameter("password");
//		String name = request.getParameter("name");
//		String[] sector = request.getParameterValues("sector");
//		String sectorOne = sector[0];
//		String sectorTwo = sector[1];
//		String sectorThree = sector[2];
//
//		UserDTO user = new UserDTO(emailAddress, password, name, sectorOne, sectorTwo, sectorThree);
//		try {
//			url = "showError.jsp";
//			boolean result = Service.addUser(user);
//			if (result) {
//				request.setAttribute("user", user);
//				request.setAttribute("successMsg", "가입 완료");
//				url = "log.html";
//				request.getSession();
//				request.setAttribute("emailAddress", emailAddress);
//				request.setAttribute("name", name);
//			} else {
//				request.setAttribute("errorMsg", "다시 시도하세요");
//			}
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//	}
//
//	private void before_sector(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String url = "showerror.jsp";
//
//		UserDTO chage_sector_user = new UserDTO();
////		System.out.println("2");
//		String[] sector = request.getParameterValues("sector");
////		System.out.println("3 :" + sector.length);
//		chage_sector_user.setEmailAddress(request.getParameter("emailAddress"));
//		chage_sector_user.setSectorOne(sector[0]);
//		chage_sector_user.setSectorTwo(sector[1]);
//		chage_sector_user.setSectorThree(sector[2]);
//
//		try {
//			UserDTO sector_info = Service.changing_user(chage_sector_user);
////			System.out.println("13");
//			if (sector_info != null) {
//				url = "dash_m.jsp";
//
//				request.getSession();
//				request.setAttribute("sector_info", sector_info);
//				request.setAttribute("successMsg", "수정 완료");
//
//			} else {
//				request.setAttribute("errorMsg", "다시 시도하세요");
//			}
//		} catch (Exception s) {
//			request.setAttribute("errorMsg", s.getMessage());
//		}
//		request.getRequestDispatcher(url).forward(request, response);
//
	}

}
