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
import dao.ProductDAO;
import model.Allergen;
import model.Product;

@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");

    // パラメータ取得
    String keyword = req.getParameter("keyword");
    String category = req.getParameter("category");
    String excluded = req.getParameter("excludedAllergens");
    List<String> excludeAllergens = (excluded != null && !excluded.isEmpty())
        ? Arrays.asList(excluded.split(","))
        : Collections.emptyList();


    // DAO呼び出し
    ProductDAO dao = new ProductDAO();
    List<Product> resultList = dao.search(keyword, category, excludeAllergens);
    System.out.println("=== search() メソッドが呼ばれました ===");


//    // ログ出力（デバッグ用）
//    System.out.println("=== 検索結果 ===");
//    System.out.println("keyword: " + keyword);
//    System.out.println("category: " + category);
//    System.out.println("excludeAllergens: " + excludeAllergens);
//    System.out.println("取得件数: " + resultList.size());
//    for (Product p : resultList) {
//      System.out.println(p.getName() + " / " + p.getCategory());
//    }

    // カテゴリ・アレルゲン一覧も再取得してフォームに戻す
    List<String> categoryList = dao.findAllCategories();
    List<Allergen> allergenList = new AllergenDAO().findAll();

    // JSPへ渡す
    req.setAttribute("resultList", resultList);
    req.setAttribute("categoryList", categoryList);
    req.setAttribute("allergenList", allergenList);

    req.getRequestDispatcher("/WEB-INF/jsp/seachResult.jsp").forward(req, res);
  }
}
