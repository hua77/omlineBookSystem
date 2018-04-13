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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     ICustomerService customerService=new CustomerServiceImp();
    public RegisterServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password=request.getParameter("password");
		String country = request.getParameter("country");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String street1 = request.getParameter("street1");
		//String street2 = request.getParameter("street2");
		String zip = request.getParameter("zip");
		//String homephone = request.getParameter("homephone");
		//String officephone = request.getParameter("officephone");
		String cellphone = request.getParameter("cellphone");
		String email = request.getParameter("email");
		/*if(!password2.equals(password)) {
			try {
				throw new CustomerException("ȷ�����������������������... ...");
			} catch (CustomerException e) {
				e.printStackTrace();
			}
		}*/
		Customer customer=new Customer();
		customer.setName(username);
		customer.setPassword(password);
		customer.setZip(zip);
		customer.setAddress(country+province+"-"+city+"-"+street1+"/");
		System.out.println("llllllllllllllll"+customer.getAddress());
		customer.setTelephone(cellphone);
		customer.setEmail(email);
		String url="";
		try {
			customerService.register(customer);
			url= "/login.jsp";
		} catch (CustomerException e) {
			e.printStackTrace();
			url="/register.jsp";
		}
		System.out.println(request.getContextPath());
		response.sendRedirect(request.getContextPath()+url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
