package com.amdocs;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			request.getRequestDispatcher("adminRegister.jsp").forward(request, response);
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			response.sendRedirect(request.getContextPath() + "/user");
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			response.sendRedirect(request.getContextPath() + "/user");
		}
		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			Admin user = new Admin();
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);

			try {
				Admin.registerAdmin(user);
				response.sendRedirect(request.getContextPath() + "/admin/admin");
			} catch (ClassNotFoundException | SQLException e) {
				response.sendRedirect(request.getContextPath() + "/logout");
				e.printStackTrace();
			}

		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}