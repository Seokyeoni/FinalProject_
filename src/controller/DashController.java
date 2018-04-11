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
			changeSector(request, response);
		} else if (command.equals("dash_q")) {
			LoginValidate_q(request, response);
		} else if (command.equals("change_sector")) {
			LoginValidate_m(request, response);
		}

	}

	
	

	
	private void LoginValidate_m(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String url = "showerror.jsp";

		try {
			UserDTO user_info = (UserDTO) session.getAttribute("user_info");
			if (user_info != null) {
				url = "dash_m.jsp";
				String sec1 = user_info.getSectorOne();
//				System.out.println("=====1====" + sec1);
				String sec2 = user_info.getSectorTwo();
				String sec3 = user_info.getSectorThree();
				

				String[] sector1_info = Service.showDefaultChart(sec1);
				String[] sector2_info = Service.showDefaultChart(sec2);
				String[] sector3_info = Service.showDefaultChart(sec3);
				
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
				ArrayList<CompanyDTO> top_company = Service.showTopFive(sectors);
				request.setAttribute("top_company", top_company);
				
//				System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);

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
			if (user_info != null) {
				url = "dash_q.jsp";
				String sec1 = user_info.getSectorOne();
//				System.out.println("=====1====" + sec1);
				String sec2 = user_info.getSectorTwo();
				String sec3 = user_info.getSectorThree();
				

				String[] sector1_info = Service.showDefaultChart(sec1);
				String[] sector2_info = Service.showDefaultChart(sec2);
				String[] sector3_info = Service.showDefaultChart(sec3);
				
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
				ArrayList<CompanyDTO> top_company = Service.showTopFive(sectors);
				request.setAttribute("top_company", top_company);
				
//				System.out.println(top_company.get(1).getName());

				session.setAttribute("user_info", user_info);

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

				session.setAttribute("user_info", new_user_info);
				request.setAttribute("successMsg", "수정 완료");

			} else {
				request.setAttribute("errorMsg", "다시 시도하세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
