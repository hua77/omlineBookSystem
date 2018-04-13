package com.briup.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.briup.bean.Customer;
import com.briup.common.exception.CustomerException;
import com.briup.service.ICustomerService;
import com.briup.service.imp.CustomerServiceImp;

@WebServlet("/user/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 ICustomerService service = new CustomerServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String street1 = request.getParameter("street1");
		String zip = request.getParameter("zip");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		if(customer==null){
			response.sendRedirect("login.jsp");
		}else{
			customer.setId(id);
			customer.setName(name);
			customer.setPassword(password);
			customer.setZip(zip);
			customer.setAddress(street1);
			customer.setTelephone(cellphone);
			customer.setEmail(email);
			try {
				service.updateCustomer(customer);
				request.setAttribute("msg","信息修改成功！");
				request.getRequestDispatcher("/user/userinfo.jsp").forward(request, response);
			} catch (CustomerException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
