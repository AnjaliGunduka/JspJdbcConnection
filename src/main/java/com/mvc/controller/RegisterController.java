package com.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.RegisterBean;
import com.mvc.dao.RegisterDao;

public class RegisterController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (request.getParameter("btn_register") != null) {

			String name = request.getParameter("txt_name");
			String mobile = request.getParameter("txt_mobile");
			String email = request.getParameter("txt_email");
			String address = request.getParameter("txt_address");
			String username = request.getParameter("txt_username");
			String password = request.getParameter("txt_password");

			RegisterBean rb = new RegisterBean();
			rb.setName(name);
			rb.setMobile(mobile);
			rb.setEmail(email);
			rb.setAddress(address);
			rb.setUserName(username);
			rb.setPassword(password);

			RegisterDao dao = new RegisterDao();
			String authorize = dao.authorizeRegister(rb);

			if (authorize.equals("SUCCESS")) {
				request.setAttribute("RegisterSuccessMsg", authorize);
				RequestDispatcher rd = request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("RegisterErrorMsg", authorize);
				RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
				rd.forward(request, response);
			}
		}	
	}
}
