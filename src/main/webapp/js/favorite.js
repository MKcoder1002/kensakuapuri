document.addEventListener("DOMContentLoaded", () => {

    document.querySelectorAll(".favorite-btn").forEach(star => {

        // ★ 初期状態（JSPから isFavorite を渡している）
        if (star.dataset.favorited === "true") {
            star.classList.add("active");
        }

        star.addEventListener("click", () => {

            const productId = star.dataset.productId;

            // クリックごとに ON / OFF をサーバーに送信
            fetch("FavoriteToggleServlet", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: `productId=${productId}`
            })
            .then(res => res.text())
            .then(result => {

                // result = "1" → 追加, "0" → 削除
                if (result === "1") {
                    star.classList.add("active");   // ★ 黄色にする
                } else {
                    star.classList.remove("active");// ★ 黒に戻す
                }
            });
        });
    });
});
