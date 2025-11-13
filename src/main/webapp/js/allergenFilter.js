document.addEventListener("DOMContentLoaded", function () {
  const buttons = document.querySelectorAll(".allergen-btn");
  const hiddenInput = document.getElementById("excludedAllergens");

  // 個別アレルゲンボタンの選択
  buttons.forEach(btn => {
    btn.addEventListener("click", () => {
      btn.classList.toggle("selected");
      updateHiddenField();
    });
  });

  // 全選択ボタン
  document.querySelectorAll(".select-all-btn").forEach(btn => {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      buttons.forEach(b => b.classList.add("selected"));
      updateHiddenField();
      console.log("全選択:", hiddenInput.value);
    });
  });

  // 全解除ボタン
  document.querySelectorAll(".deselect-all-btn").forEach(btn => {
    btn.addEventListener("click", (e) => {
      e.preventDefault();
      buttons.forEach(b => b.classList.remove("selected"));
      updateHiddenField();
      console.log("全解除:", hiddenInput.value);
    });
  });

  // hiddenフィールド更新
  function updateHiddenField() {
    const selected = Array.from(buttons)
                          .filter(b => b.classList.contains("selected"))
                          .map(b => b.dataset.allergen);
    hiddenInput.value = selected.join(",");
  }
});
