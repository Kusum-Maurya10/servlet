package com.amdocs;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie ck=new Cookie("user_id","");
		response.addCookie(ck);
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("loginid");
		String password = request.getParameter("password");
				
		try {
			login LU = new login(userid , password);
			Cookie ck;
			HttpSession session=request.getSession();
			
			switch(LU.loginUser()) {
				case 0:
					ck= new Cookie("user_id",userid);
					response.addCookie(ck);
			        session.setAttribute("userid",userid);
			        session.setAttribute("usertype","user");
					response.sendRedirect("user");
				    break;
				 case 1:
					 ck= new Cookie("user_id",userid);
					 response.addCookie(ck);
					 session.setAttribute("userid",userid);
				     session.setAttribute("usertype","admin");
					 response.sendRedirect("admin");
				    break;
				 case 2:
					 request.setAttribute("message","Incorrect Password For User");
					 request.getRequestDispatcher("login.jsp").forward(request, response);
					 break;
				 case 3:
					 request.setAttribute("message","Incorrect Password For Admin");
					 request.getRequestDispatcher("login.jsp").forward(request, response);
					 break;
				 default:
					 response.sendRedirect("user-register");  
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("user-register");
		}

	}

}