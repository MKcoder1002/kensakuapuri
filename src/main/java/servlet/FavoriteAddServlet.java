package servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.FavoriteDAO;
import model.User;
/**
 * Servlet implementation class FavoriteAddServlet
 */
@WebServlet("/FavoriteAddServlet")
public class FavoriteAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private FavoriteDAO favoriteDao = new FavoriteDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("LoginServlet");
      
      return;
    }

    int Id = loginUser.getId();
    int productId = Integer.parseInt(request.getParameter("productId"));

    try {
      favoriteDao.add(Id, productId);
    } catch (SQLException e) {
      e.printStackTrace(); // 重複登録などの例外処理
    }

    // 検索結果ページに戻す（必要に応じて調整）
    response.sendRedirect("SearchResultServlet");
  }
}
