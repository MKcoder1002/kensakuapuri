package servlet;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;
import util.DBUtil;

@WebServlet("/Login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    // Userインスタンス（ユーザー情報）の生成
    User user = new User(name, pass);
    // ログイン処理


    boolean isLogin = false;

    try (Connection conn = DBUtil.getConnection()) {
      UserDAO dao = new UserDAO(conn);
      isLogin = dao.login(name, pass); // DB照合
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (isLogin) {
    	  HttpSession session = request.getSession();
    	  session.setAttribute("loginUser", user);
    	  // メインメニュー画面へリダイレクト
    	  response.sendRedirect("mainMenu.jsp");
    	} else {
    	  // ログイン失敗時は結果画面へフォワード
    	  request.setAttribute("errorMessage", "ログインに失敗しました");
    	  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
    	  dispatcher.forward(request, response);
    	}
  }
}
