package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.FavoriteDAO;
import model.User;

@WebServlet("/FavoriteAddServlet")
public class FavoriteAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FavoriteDAO favoriteDao = new FavoriteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("★ FavoriteAddServlet が呼ばれた！");

        // ログイン確認
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            System.out.println("★ ログインしていないためリダイレクト");
            response.sendRedirect("LoginServlet");
            return;
        }

        int userId = loginUser.getId();
        int productId = Integer.parseInt(request.getParameter("productId"));

        System.out.println("★ 受け取った productId = " + productId);

        // 検索条件を受け取る
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        String excluded = request.getParameter("excludedAllergens");

        System.out.println("★ 受け取った検索条件 keyword=" + keyword + ", category=" + category + ", excluded=" + excluded);

        try {
            System.out.println("★ FavoriteDAO.add() を呼び出す");
            favoriteDao.add(userId, productId);
            System.out.println("★ FavoriteDAO.add() 呼び出し完了");
        } catch (SQLException e) {
            System.out.println("★ 例外発生！");
            e.printStackTrace();
        }

        // 検索条件を付けて SearchResultServlet を再表示（URLエンコード済み）
        String url = "SearchResultServlet?"
                + "keyword=" + encode(keyword)
                + "&category=" + encode(category)
                + "&excludedAllergens=" + encode(excluded);

        System.out.println("★ 再検索へ forward: " + url);

        request.getRequestDispatcher(url).forward(request, response);
    }

    // Java 10+ の URLEncoder.encode(String, Charset) を使う安全なヘルパー
    private String encode(String str) {
        return (str == null) ? "" : URLEncoder.encode(str, StandardCharsets.UTF_8);
    }
}
