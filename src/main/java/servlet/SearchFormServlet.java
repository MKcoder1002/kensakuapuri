package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AllergenDAO;
import dao.ProductDAO;
import model.Allergen;

/**
 * Servlet implementation class SearchFormServlet
 */
@WebServlet("/SearchFormServlet")
public class SearchFormServlet extends HttpServlet {
	  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		AllergenDAO allergenDao = new AllergenDAO();
	    List<Allergen> allergenList = allergenDao.findAll();

	    ProductDAO productDao = new ProductDAO();
	    List<String> categoryList = productDao.findAllCategories();

	    req.setAttribute("allergenList", allergenList);
	    req.setAttribute("categoryList", categoryList);
	    req.getRequestDispatcher("/WEB-INF/jsp/searchForm.jsp").forward(req, res);
	  }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
