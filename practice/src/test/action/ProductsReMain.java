package test.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import test.model.Products;
import test.repository.ProductsRepository;

public class ProductsReMain implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson = new Gson();
		
		ProductsRepository productsRepository = 
				ProductsRepository.getInstance();
		List<Products> products = productsRepository.findAll();
		
		String productsJson = gson.toJson(products);
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json); charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		pw.println(productsJson);
		
	}
}
