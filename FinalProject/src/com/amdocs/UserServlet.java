package com.amdocs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			request.setAttribute("message",Integer.parseInt((String) session.getAttribute("userid")));
			request.getRequestDispatcher("userHome.jsp").forward(request, response);
			
		}else if(((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			response.sendRedirect(request.getContextPath() + "/admin");
			
		}else{
			response.sendRedirect(request.getContextPath());
			
		}
	}
	

}
