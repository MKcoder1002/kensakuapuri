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
 * Servlet implementation class FavoriteRemoveServlet
 */
@WebServlet("/FavoriteRemoveServlet")
public class FavoriteRemoveServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private FavoriteDAO favoriteDao = new FavoriteDAO();

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("LoginServlet");
      return;
    }

    int userId = loginUser.getId();
    int productId = Integer.parseInt(request.getParameter("productId"));

    try {
      favoriteDao.remove(userId, productId);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    response.sendRedirect("FavoriteListServlet"); // お気に入り一覧に戻る
  }
}
