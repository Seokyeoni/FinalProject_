package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Service;
import model.StockDAO;
import model.domain.UserDTO;

@WebServlet("/cont")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String command = request.getParameter("command");

//		if (command.equals("search")) {
//			try {
//				ArrayList<String[]> stock = StockDAO.selectAll();
//				request.setAttribute("stock", stock);
//				request.getRequestDispatcher("testTable1.jsp").forward(request, response);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} else 
		if (command.equals("login")) {
			LoginValidate(request, response);
		} else if (command.equals("sign_in")) {
			sign_in(request, response);
		}

	}
	//로그인 하면서 섹터 세개 뽑아와야됨! 
	private void LoginValidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "showerror.jsp";
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		UserDTO user = new UserDTO(emailAddress, password);
		try {
			UserDTO sector_info = Service.loginValidate(user);
			if (sector_info != null) {
				url = "papa_dash_m.jsp";
				String sec1 = sector_info.getSectorOne();
				String sec2 = sector_info.getSectorTwo();
				
				ArrayList<String[]> sector1 = StockDAO.selectSectorByMonth(sec1);
				String sec1_name = StockDAO.selectSectorByYear(sec1).get(0)[0] ;
				String sec1_ror =  StockDAO.selectSectorByYear(sec1).get(0)[1] ;
				
				ArrayList<String[]> sector2 = StockDAO.selectSectorByMonth(sec2);
				String ym1 = "";
				String rtn1 = "";
				String ym2 = "";
				String rtn2 = "";
				
				for (Object s : sector1) {
					String[] s1 = (String[]) s;
					//System.out.println(Arrays.asList(s1).toString());
					ym1 += "'" + s1[1] + "',";
					rtn1 += "'" + s1[2] + "',";
				}
				for (Object s : sector2) {
					String[] s2 = (String[]) s;
					//System.out.println(Arrays.asList(s1).toString());
					ym2 += "'" + s2[1] + "',";
					rtn2 += "'" + s2[2] + "',";
				}
				//System.out.println(sample.keySet());
				request.setAttribute("sec1_name", sec1_name);
				request.setAttribute("sec1_ror", sec1_ror);
				request.setAttribute("ym1", ym1);
				request.setAttribute("rtn1", rtn1);
				request.setAttribute("ym2", ym2);
				request.setAttribute("rtn2", rtn2);
				
				
				
//				HttpSession session = request.getSession();
//				session.setAttribute("sector_info", sector_info);

			} else {
				request.setAttribute("errMsg", "아이디와 비밀번호를 다시 확인해주세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());

		}
		request.getRequestDispatcher(url).forward(request, response);
	}
	public void sign_in(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "showError.jsp";
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String[] sector = request.getParameterValues("sector");
		String sectorOne = sector[0];
		String sectorTwo = sector[1];
		String sectorThree = sector[2];
	
		
		UserDTO user = new UserDTO(emailAddress, password, name, sectorOne, sectorTwo, sectorThree);
		try {
			url = "showError.jsp";
			boolean result = Service.addUser(user);
			if (result) {
				request.setAttribute("user", user);
				request.setAttribute("successMsg", "가입 완료");
				url = "log.html";
				request.getSession();
				request.setAttribute("emailAddress", emailAddress);
				request.setAttribute("name", name);
			} else {
				request.setAttribute("errorMsg", "다시 시도하세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
