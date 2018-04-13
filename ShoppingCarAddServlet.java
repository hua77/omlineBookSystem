package com.briup.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.briup.bean.Book;
import com.briup.bean.Line;
import com.briup.bean.ShoppingCar;
import com.briup.service.IBookService;
import com.briup.service.imp.BookServiceImp;

@WebServlet("/user/ShoppingCarAddServlet")
public class ShoppingCarAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private IBookService service = new BookServiceImp();   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long id = Long.parseLong(request.getParameter("id"));
		
		
		try {
			Book book = service.findBookById(id);
			System.out.println(book);
			ShoppingCar shoppingCar = (ShoppingCar) request.getSession().getAttribute("shoppingCar");
			if(shoppingCar==null){
				shoppingCar = new ShoppingCar();
				request.getSession().setAttribute("shoppingCar",shoppingCar);
			}
			
			Line line = new Line();
			line.setBook(book);
			line.setNum(1);
			shoppingCar.add(line);
			
			request.getRequestDispatcher("/user/shopcart.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
