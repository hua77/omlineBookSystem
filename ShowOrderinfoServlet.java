package com.briup.web.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.service.IOrderService;
import com.briup.service.imp.OrderServiceImp;

@WebServlet("/user/ShowOrderinfoServlet")
public class ShowOrderinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService service = new OrderServiceImp();


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		try {
			Order order = service.findOrderById(id);
			System.out.println("order===="+order);
			System.out.println("order_line====="+order.getLines());
			request.setAttribute("order", order);
			request.getRequestDispatcher("/user/orderinfo.jsp").forward(request, response);
		} catch (OrderException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
