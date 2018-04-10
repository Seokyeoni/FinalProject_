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

		if (command.equals("search")) {
			try {
				ArrayList<String[]> stock = StockDAO.selectAll();
				request.setAttribute("stock", stock);
				request.getRequestDispatcher("testTable1.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (command.equals("login")) {
			LoginValidate(request, response);
		} else if (command.equals("sign_in")) {
			sign_in(request, response);
		}

	}
	//로그인 하면서 섹터 세개 뽑아와야됨! 
	private void LoginValidate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "login.html";
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		System.out.println(emailAddress);
		System.out.println(password);
		UserDTO user = new UserDTO(emailAddress, password);
		try {
			UserDTO sector_info = Service.loginValidate(user);
			if (sector_info != null) {
				url = "dash_q.html";
				HttpSession session = request.getSession();
				session.setAttribute("sector_info", sector_info);

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
	
		
		UserDTO user = new UserDTO(name, emailAddress, password, sectorOne, sectorTwo, sectorThree);
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
