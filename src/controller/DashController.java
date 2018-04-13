package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Service;
import model.domain.CompanyDTO;
import model.domain.UserDTO;

public class DashController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String command = request.getParameter("command");

		if (command.equals("dash_m")) { 
			LoginValidate_m(request, response);
		} else if (command.equals("dash_q")) {
			LoginValidate_q(request, response);
		} else if (command.equals("dash_c_sec1")) {
			drawSectorOneGoogleMap(request, response);
		} else if (command.equals("dash_c_sec2")) {
			drawSectorTwoGoogleMap(request, response);
		} else if (command.equals("dash_c_sec3")) {
			drawSectorThreeGoogleMap(request, response);
		} else if (command.equals("change_sector")) {
			changeSector(request, response);
		}

	}
	

	private void LoginValidate_m(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());
			
			if (user_info != null) {
				url = "dash_m.jsp";
				String sec1 = user_info.getSectorOne();
//				System.out.println("=====1====" + sec1);
				String sec2 = user_info.getSectorTwo();
				String sec3 = user_info.getSectorThree();
				
				System.out.println("[step02] sector_name: " + sec1);

				String[] sector1_info = Service.showMonthlyChart(sec1);
				String[] sector2_info = Service.showMonthlyChart(sec2);
				String[] sector3_info = Service.showMonthlyChart(sec3);
				
//				System.out.println(sector1_info[2]);
				request.setAttribute("sector1_name", sector1_info[0]);
				request.setAttribute("sector1_ror", sector1_info[1]);
				request.setAttribute("sector1_ym", sector1_info[2]);
				request.setAttribute("sector1_rtn", sector1_info[3]);
				
				request.setAttribute("sector2_name", sector2_info[0]);
				request.setAttribute("sector2_ror", sector2_info[1]);
				request.setAttribute("sector2_ym", sector2_info[2]);
				request.setAttribute("sector2_rtn", sector2_info[3]);
				
				request.setAttribute("sector3_name", sector3_info[0]);
				request.setAttribute("sector3_ror", sector3_info[1]);
				request.setAttribute("sector3_ym", sector3_info[2]);
				request.setAttribute("sector3_rtn", sector3_info[3]);
				
				
				String[] sectors = new String[] {sec1, sec2, sec3};
				ArrayList<CompanyDTO> top_company = Service.showMonthlyTopFive(sectors);
				
				System.out.println("[step03] sector_ror: " + sector1_info[1]);
				
				
				request.setAttribute("top_company", top_company);
				
//				System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);
				session.setAttribute("sector1_name", sector1_info[0]);
				session.setAttribute("sector2_name", sector2_info[0]);
				session.setAttribute("sector3_name", sector3_info[0]);

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}
	
	
	private void LoginValidate_q(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());
			
			if (user_info != null) {
				url = "dash_q.jsp";
				String sec1 = user_info.getSectorOne();
//				System.out.println("=====1====" + sec1);
				String sec2 = user_info.getSectorTwo();
				String sec3 = user_info.getSectorThree();
				
				System.out.println("[step02] sector_name: " + sec1);
				

				String[] sector1_info = Service.showQuarterlyChart(sec1);
				String[] sector2_info = Service.showQuarterlyChart(sec2);
				String[] sector3_info = Service.showQuarterlyChart(sec3);
				
//				System.out.println(sector1_info[2]);
				request.setAttribute("sector1_name", sector1_info[0]);
				request.setAttribute("sector1_ror", sector1_info[1]);
				request.setAttribute("sector1_ym", sector1_info[2]);
				request.setAttribute("sector1_rtn", sector1_info[3]);
				
				request.setAttribute("sector2_name", sector2_info[0]);
				request.setAttribute("sector2_ror", sector2_info[1]);
				request.setAttribute("sector2_ym", sector2_info[2]);
				request.setAttribute("sector2_rtn", sector2_info[3]);
				
				request.setAttribute("sector3_name", sector3_info[0]);
				request.setAttribute("sector3_ror", sector3_info[1]);
				request.setAttribute("sector3_ym", sector3_info[2]);
				request.setAttribute("sector3_rtn", sector3_info[3]);
				
				
				String[] sectors = new String[] {sec1, sec2, sec3};
				ArrayList<CompanyDTO> top_company = Service.showQuarterlyTopFive(sectors);
				System.out.println("[step03] sector_ror: " + sector1_info[1]);
				
				request.setAttribute("top_company", top_company);
				
//				System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);
				session.setAttribute("sector1_name", sector1_info[0]);
				session.setAttribute("sector2_name", sector2_info[0]);
				session.setAttribute("sector3_name", sector3_info[0]);

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}	

	
	private void drawSectorOneGoogleMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sector1_name = (String)session.getAttribute("sector1_name");
		String sector2_name = (String)session.getAttribute("sector2_name");
		String sector3_name = (String)session.getAttribute("sector3_name");
		

		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			
			System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());

			if (user_info != null) {
				url = "dash_c_sec1.jsp";
				String sec = user_info.getSectorOne();
				// System.out.println("=====1====" + sec1);

				System.out.println("[step02] sector_name: " + sec);

				ArrayList<String> nation_rtn = Service.drawGoogleMap(sec);
				// System.out.println(sector1_info[2]);

				if (sec != "s03") {
					System.out.println("[step03] USD_rtn: " + nation_rtn.get(6));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", nation_rtn.get(5));
					request.setAttribute("USD_rtn", nation_rtn.get(6));

				} else if (sec == "s03") { // s03 은 없는 국가 존재
//					System.out.println("[step03] USD_rtn: " + nation_rtn.get(5));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", "0.00");
					request.setAttribute("USD_rtn", nation_rtn.get(5));
				}

				// System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);
				session.setAttribute("sector1_name", sector1_name);
				session.setAttribute("sector2_name", sector2_name);
				session.setAttribute("sector3_name", sector3_name);
				

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);

	}

	
	private void drawSectorTwoGoogleMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sector2_name = (String)session.getAttribute("sector2_name");

		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());

			if (user_info != null) {
				url = "dash_c_sec2.jsp";
				String sec = user_info.getSectorTwo();
				// System.out.println("=====1====" + sec1);

				System.out.println("[step02] sector_name: " + sec);

				ArrayList<String> nation_rtn = Service.drawGoogleMap(sec);
				// System.out.println(sector1_info[2]);

				if (sec != "s03") {
					System.out.println("[step03] USD_rtn: " + nation_rtn.get(6));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", nation_rtn.get(5));
					request.setAttribute("USD_rtn", nation_rtn.get(6));

				} else if (sec == "s03") { // s03 은 없는 국가 존재
					System.out.println("[step03] USD_rtn: " + nation_rtn.get(5));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", 0.00);
					request.setAttribute("USD_rtn", nation_rtn.get(5));
				}

				// System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);
				session.setAttribute("sector2_name", sector2_name);

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);

	}
	
	private void drawSectorThreeGoogleMap(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sector3_name = (String)session.getAttribute("sector3_name");

		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());

			if (user_info != null) {
				url = "dash_c_sec3.jsp";
				String sec = user_info.getSectorThree();
				// System.out.println("=====1====" + sec1);

				System.out.println("[step02] sector_name: " + sec);

				ArrayList<String> nation_rtn = Service.drawGoogleMap(sec);
				// System.out.println(sector1_info[2]);

				if (sec != "s03") {
					System.out.println("[step03] USD_rtn: " + nation_rtn.get(6));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", nation_rtn.get(5));
					request.setAttribute("USD_rtn", nation_rtn.get(6));

				} else if (sec == "s03") { // s03 은 없는 국가 존재
					System.out.println("[step03] USD_rtn: " + nation_rtn.get(5));
					request.setAttribute("CNY_rtn", nation_rtn.get(0));
					request.setAttribute("HKD_rtn", nation_rtn.get(1));
					request.setAttribute("JPY_rtn", nation_rtn.get(2));
					request.setAttribute("KRW_rtn", nation_rtn.get(3));
					request.setAttribute("MXN_rtn", nation_rtn.get(4));
					request.setAttribute("TWD_rtn", 0.00);
					request.setAttribute("USD_rtn", nation_rtn.get(5));
				}

				// System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);
				session.setAttribute("sector3_name", sector3_name);

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);

	}
	
	
	

	private void changeSector(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String url = "showerror.jsp";

		UserDTO user_info = (UserDTO) session.getAttribute("user_info");
		System.out.println("[step01] login_user_info: " + user_info.getEmailAddress());
		System.out.println("[step02] before_sector_name: " + user_info.getSectorOne());
		
		String[] sector = request.getParameterValues("sector");
//		System.out.println("3 :" + sector.length);
		user_info.setSectorOne(sector[0]);
		user_info.setSectorTwo(sector[1]);
		user_info.setSectorThree(sector[2]);
		

		try {
			UserDTO new_user_info = Service.changeSectorInfo(user_info);
			
			
//			System.out.println("13");
			if (new_user_info != null) {
				url = "dash_m.jsp";

				String sec1 = new_user_info.getSectorOne();
				String sec2 = new_user_info.getSectorTwo();
				String sec3 = new_user_info.getSectorThree();
				String[] sectors = new String[] {sec1, sec2, sec3};
				
				System.out.println("[step03] after_sector_name: " + sec1);
				
				String[] sector1_info = Service.showMonthlyChart(sec1);
				String[] sector2_info = Service.showMonthlyChart(sec2);
				String[] sector3_info = Service.showMonthlyChart(sec3);
				
				ArrayList<CompanyDTO> top_company = Service.showMonthlyTopFive(sectors);

				System.out.println("[step04]sector_ror: " + sector1_info[1]);
				
				request.setAttribute("sector1_name", sector1_info[0]);
				request.setAttribute("sector1_ror", sector1_info[1]);
				request.setAttribute("sector1_ym", sector1_info[2]);
				request.setAttribute("sector1_rtn", sector1_info[3]);
				
				request.setAttribute("sector2_name", sector2_info[0]);
				request.setAttribute("sector2_ror", sector2_info[1]);
				request.setAttribute("sector2_ym", sector2_info[2]);
				request.setAttribute("sector2_rtn", sector2_info[3]);
				
				request.setAttribute("sector3_name", sector3_info[0]);
				request.setAttribute("sector3_ror", sector3_info[1]);
				request.setAttribute("sector3_ym", sector3_info[2]);
				request.setAttribute("sector3_rtn", sector3_info[3]);
				
				request.setAttribute("top_company", top_company);
				
//				System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", new_user_info);
				session.setAttribute("sector1_name", sector1_info[0]);
				session.setAttribute("sector2_name", sector2_info[0]);
				session.setAttribute("sector3_name", sector3_info[0]);

			} else {
				request.setAttribute("errorMsg", "다시 시도하세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
