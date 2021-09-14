package com.mvc.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mvc.login.bean.LoginBean;

public class LoginDao {
	public String authorizeLogin(LoginBean lb) {
		String username = lb.getUserName();
		String password = lb.getPassword();

		String url = "jdbc:mysql://localhost:3306/db_mvc_login";
		String uname = "root";
		String pass = "Tiger";
		String select = "select * from user where username=? and password=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pass);
			PreparedStatement ps = con.prepareStatement(select);

			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (lb.getUserName().equals(rs.getString("username"))
						&& lb.getPassword().equals(rs.getString("password"))) {
					return "Success";
				}
			}

			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Wrong Username or Password";
	}

}
