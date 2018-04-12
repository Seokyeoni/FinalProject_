//package controller;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//import model.StockDAO;
//
//@WebServlet("/pcont1")
//public class PapaController1 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void service(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//
//		String command = request.getParameter("command");
//
//		if (command.equals("input_test")) {
////			try {
////				HashMap<String, String[]> sample = StockDAO.selectTwoSector();
////				request.setAttribute("sample", sample);
////				
////				Gson gson = new Gson();
////				String json = gson.toJson(sample);
////				HttpSession session = request.getSession();
////				session.setAttribute("json", json);
////				
////				request.getRequestDispatcher("input_test.jsp").forward(request, response);
////				
////			} catch (SQLException e) {
////				e.printStackTrace();
////				
////			}
//			
////			try {
////				ArrayList<String[]> sample = StockDAO.selectTwoSectors();
////				request.setAttribute("sample", sample);
////				request.getRequestDispatcher("input_test.jsp").forward(request, response);
////			} catch (SQLException e) {
////				e.printStackTrace();
////				
////			}
//		}	else if (command.equals("dash_m")) {
//			try {
//				String sec1 = "기초 소재";
//				String sec2 = "테스트";
//				ArrayList<String[]> sector1 = StockDAO.selectSectorByMonth(sec1);
//				String sec1_ror =  StockDAO.selectSectorByYear(sec1).get(0)[1] ;
//				
//				ArrayList<String[]> sector2 = StockDAO.selectSectorByMonth(sec2);
//				String ym1 = "";
//				String rtn1 = "";
//				String ym2 = "";
//				String rtn2 = "";
//				
//				for (Object s : sector1) {
//					String[] s1 = (String[]) s;
//					//System.out.println(Arrays.asList(s1).toString());
//					ym1 += "'" + s1[1] + "',";
//					rtn1 += "'" + s1[2] + "',";
//				}
//				for (Object s : sector2) {
//					String[] s2 = (String[]) s;
//					//System.out.println(Arrays.asList(s1).toString());
//					ym2 += "'" + s2[1] + "',";
//					rtn2 += "'" + s2[2] + "',";
//				}
//				//System.out.println(sample.keySet());
//				request.setAttribute("sec1_name", sec1);
//				request.setAttribute("sec1_ror", sec1_ror);
//				request.setAttribute("ym1", ym1);
//				request.setAttribute("rtn1", rtn1);
//				request.setAttribute("ym2", ym2);
//				request.setAttribute("rtn2", rtn2);
//				
//				request.getRequestDispatcher("papa_dash_m.jsp").forward(request, response);
//			} catch (SQLException e) {
//				e.printStackTrace();
//				
//			}
//			
//		}
//
//	}
//}
