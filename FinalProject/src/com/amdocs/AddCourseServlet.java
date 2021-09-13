package com.amdocs;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amdocs.Course;

public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			response.sendRedirect(request.getContextPath() + "/user");
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			request.getRequestDispatcher("addCourse.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			response.sendRedirect(request.getContextPath() + "/user");
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			String name = request.getParameter("name");
			String desp = request.getParameter("desp");
			String fees = request.getParameter("fees");
			String resource = request.getParameter("resource");

			try {
				Course c = new Course(name, resource, desp, fees);
				c.addCourse();
				response.sendRedirect(request.getContextPath() + "/admin");
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + "/admin/course");
			}
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}