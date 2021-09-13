package com.amdocs;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.amdocs.Feedback;

public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {
			request.getRequestDispatcher("insertFeedback.jsp").forward(request, response);
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (((String) session.getAttribute("usertype")).equalsIgnoreCase("user")) {

			Feedback feedback;
			try {
				feedback = new Feedback(Integer.parseInt((String) session.getAttribute("userid")),
						request.getParameter("feedback_message"));
				request.setAttribute("message1", "Your Feedback is Submitted ");
				request.setAttribute("message2", "Feedback Id :" + feedback.getFeedback_id());
				request.getRequestDispatcher("resultFeedback.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException e) {
				request.setAttribute("message1", e.getMessage());
				request.getRequestDispatcher("resultFeedback.jsp").forward(request, response);
				e.printStackTrace();
			}
		} else if (((String) session.getAttribute("usertype")).equalsIgnoreCase("admin")) {
			response.sendRedirect(request.getContextPath() + "/admin");
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}