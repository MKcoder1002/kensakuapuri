package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.FavoriteDAO;
import model.Product;
import model.User;

@WebServlet("/FavoriteListServlet")
public class FavoriteListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private FavoriteDAO favoriteDao = new FavoriteDAO(); // DAOインスタンス化

    public FavoriteListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ログインユーザー取得
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("LoginServlet"); // 未ログインならログインページへ
            return;
        }

        int userId = loginUser.getId(); // UserクラスのgetUserid()を使用

        try {
            // お気に入り一覧取得
            List<Product> favoriteList = favoriteDao.findByUserId(userId);

            // JSPに渡す
            request.setAttribute("favoriteList", favoriteList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/favoriteList.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "お気に入りの取得に失敗しました");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // POSTでもGETと同じ処理
    }
}
