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

@WebServlet("/FavoriteToggleServlet")
public class FavoriteToggleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FavoriteDAO favoriteDao = new FavoriteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // ★ ログイン確認
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        int userId = loginUser.getId();
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            // 現在のお気に入り状態をチェック
            boolean isFav = favoriteDao.isFavorite(userId, productId);

            if (isFav) {
                favoriteDao.remove(userId, productId);
                response.getWriter().write("0"); // 削除された
                System.out.println("お気に入り削除: " + productId);
            } else {
                favoriteDao.add(userId, productId);
                response.getWriter().write("1"); // 追加された
                System.out.println("お気に入り追加: " + productId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
