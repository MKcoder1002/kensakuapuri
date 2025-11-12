document.addEventListener("DOMContentLoaded", function () {
  const buttons = document.querySelectorAll(".allergen-btn");
  const hiddenInput = document.getElementById("excludedAllergens");

  buttons.forEach(btn => {
    btn.addEventListener("click", () => {
      btn.classList.toggle("selected");

      // 選択されたアレルゲン名を収集
      const selected = Array.from(document.querySelectorAll(".allergen-btn.selected"))
        .map(b => b.dataset.allergen);

      // hiddenフィールドにセット（カンマ区切り）
      hiddenInput.value = selected.join(",");
	  console.log("hidden:",hiddenInput.value);
    });
  });
});
