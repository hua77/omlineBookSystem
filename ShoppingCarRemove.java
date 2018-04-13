package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.ShoppingCar;

@WebServlet("/user/ShoppingCarRemove")
public class ShoppingCarRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("key"));
		ShoppingCar shoppingCar = (ShoppingCar)request.getSession().getAttribute("shoppingCar");
		shoppingCar.delete(id);
		response.sendRedirect(request.getContextPath()+"/user/shopcart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
