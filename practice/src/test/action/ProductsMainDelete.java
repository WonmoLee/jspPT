package test.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.repository.ProductsRepository;

public class ProductsMainDelete implements Action{
		@Override
		public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			int productId = Integer.parseInt(request.getParameter("productId"));
			
			ProductsRepository productRepository = 
					ProductsRepository.getInstance();
			
			int result = productRepository.findDeleteById(productId);

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println(result+"");
		}
}
