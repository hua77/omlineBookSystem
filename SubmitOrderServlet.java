package com.briup.web.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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
import com.briup.bean.ShoppingCar;
import com.briup.common.exception.OrderException;
import com.briup.service.ILineService;
import com.briup.service.IOrderService;
import com.briup.service.imp.LineServiceImp;
import com.briup.service.imp.OrderServiceImp;

@WebServlet("/user/SubmitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IOrderService service = new OrderServiceImp();
	private ILineService service1=new LineServiceImp();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		ShoppingCar shoppingCar = (ShoppingCar) session.getAttribute("shoppingCar");
		Order order = new Order();
		order.setCustomer(customer);
		order.setCost(shoppingCar.getCost());
		Collection<Line> values = shoppingCar.getLines().values();
		Set<Line> set=new HashSet<Line>();
		for(Line l:values) {
			set.add(l);
		}
		order.setLines(set);
		//order.setLines((Set<Line>)shoppingCar.getLines().values());
		order.setOrderDate(new Date());
		try {
			service.confirmOrder(order);
			for(Line l:values) {
				l.setOrder(order);
				service1.saveLine(l);
				System.out.println("ddddddddddd");
			}
			System.out.println("order"+order);
			shoppingCar.clear();
			session.setAttribute("msg", "订单提交成功！");
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		} catch (OrderException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
