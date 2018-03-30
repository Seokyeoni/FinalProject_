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

import model.StockDAO;

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
		}

	}
}
