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

import com.google.gson.Gson;

import model.StockDAO;

@WebServlet("/pcont1")
public class PapaController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String command = request.getParameter("command");

		if (command.equals("input_test")) {
			try {
				HashMap<String, String[]> sample = StockDAO.selectTwoSector();
				request.setAttribute("sample", sample);
				
				Gson gson = new Gson();
				String json = gson.toJson(sample);
				HttpSession session = request.getSession();
				session.setAttribute("json", json);
				
				request.getRequestDispatcher("input_test.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
//			try {
//				ArrayList<String[]> sample = StockDAO.selectTwoSectors();
//				request.setAttribute("sample", sample);
//				request.getRequestDispatcher("input_test.jsp").forward(request, response);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				
//			}
		}
		else if (command.equals("dash_m")) {
			try {
				HashMap<String, String[]> sample = StockDAO.selectTwoSector();
				request.setAttribute("sample", sample);
				request.getRequestDispatcher("papa_dash_m.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			
		}

	}
}
