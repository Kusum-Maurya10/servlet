package com.amdocs;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			response.sendRedirect(request.getContextPath() + "/user");
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			request.setAttribute("message", session.getAttribute("userid"));
			request.getRequestDispatcher("adminHome.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}