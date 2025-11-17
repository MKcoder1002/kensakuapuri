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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        try (Connection conn = DBUtil.getConnection()) {
            UserDAO dao = new UserDAO(conn);

            // ID を含むユーザー情報を取得
            User loginUser = dao.findByNameAndPass(name, pass);

            if (loginUser != null) {   // ログイン成功
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", loginUser);
                response.sendRedirect("mainMenu.jsp");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ログイン失敗した場合
        request.setAttribute("errorMessage", "ログインに失敗しました");
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/loginResult.jsp");
        dispatcher.forward(request, response);
    }
}
