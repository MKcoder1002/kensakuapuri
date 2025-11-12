package servlet;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;
import util.DBUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");

    User user = new User(name, pass);

    try (Connection conn = DBUtil.getConnection()) {
      UserDAO dao = new UserDAO(conn);
      dao.register(user);
    } catch (Exception e) {
      e.printStackTrace();
      // エラー時はエラーページにフォワードするのも可
      request.setAttribute("errorMessage", "登録に失敗しました");
      request.getRequestDispatcher("WEB-INF/jsp/error.jsp").forward(request, response);
      return;
    }

    // 成功時に登録完了ページへフォワード
    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerResult.jsp");
    dispatcher.forward(request, response);
  }
}
