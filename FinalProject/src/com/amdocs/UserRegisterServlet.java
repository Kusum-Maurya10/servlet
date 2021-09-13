  
package com.amdocs;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("userRegister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Date reg_date = new java.sql.Date(new java.util.Date().getTime());
		String password = request.getParameter("password");
		String photo = request.getParameter("photo");

		User user = new User();
		user.setName(name);
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		user.setReg_date(reg_date);
		user.setPassword(password);
		user.setUpload_photo(photo);

		int s = -1;
		try {
			s = User.registerUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (s != -1) {
			HttpSession session = request.getSession();
			session.setAttribute("userid", s);
			session.setAttribute("usertype", "user");
			request.setAttribute("message", s);
			request.getRequestDispatcher("userHome2.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
}