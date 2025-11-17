package servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AllergenDAO;
import dao.FavoriteDAO;
import dao.ProductDAO;
import model.Allergen;
import model.Product;
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // ▼ ログインユーザー取得（セッション）
        model.User loginUser = (model.User) req.getSession().getAttribute("loginUser");
        int userId = (loginUser != null) ? loginUser.getId() : -1;

        // パラメータ取得
        String keyword = req.getParameter("keyword");
        String category = req.getParameter("category");
        String excluded = req.getParameter("excludedAllergens");

        List<String> excludeAllergens =
            (excluded != null && !excluded.isEmpty())
            ? Arrays.asList(excluded.split(","))
            : Collections.emptyList();

        // DAO呼び出し
        ProductDAO dao = new ProductDAO();
        List<Product> resultList = dao.search(keyword, category, excludeAllergens,userId);

        // ★★ Favorite 判定を追加 ★★
        if (userId != -1) {
            dao.FavoriteDAO favDao = new FavoriteDAO();

            for (Product p : resultList) {
                boolean fav = false;

                try {
                    fav = favDao.isFavorite(userId, p.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                p.setFavorited(fav); // ← JSP側で ${item.favorited} が使えるようになる！
            }
        }

        // カテゴリ・アレルゲン一覧も再取得
        List<String> categoryList = dao.findAllCategories();
        List<Allergen> allergenList = new AllergenDAO().findAll();

        // JSPへ渡す
        req.setAttribute("resultList", resultList);
        req.setAttribute("categoryList", categoryList);
        req.setAttribute("allergenList", allergenList);

        req.getRequestDispatcher("/WEB-INF/jsp/seachResult.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doGet(req, res);
    }
}
