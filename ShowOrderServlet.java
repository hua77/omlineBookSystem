package com.briup.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.briup.bean.Customer;
import com.briup.bean.Line;
import com.briup.bean.Order;
import com.briup.common.exception.OrderException;
import com.briup.service.IOrderService;
import com.briup.service.imp.OrderServiceImp;

@WebServlet("/user/ShowOrderServlet")
public class ShowOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IOrderService service = new OrderServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		try {
			if(customer!=null){
				List<Order> list = service.findOrderByCustomerId(customer.getId());
				System.out.println("ddddddddddd"+list);
				HttpSession session = request.getSession();
				session.setAttribute("orders",list);
				request.getRequestDispatcher("/user/order.jsp").forward(request, response);
			}else{
				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}
		} catch (OrderException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
