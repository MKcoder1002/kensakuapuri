document.addEventListener("DOMContentLoaded", function () {
  const buttons = document.querySelectorAll(".allergen-btn");
  const hiddenInput = document.getElementById("excludedAllergens");
  const toggleBtn = document.getElementById("toggleAllergenArea");
  const allergenArea = document.getElementById("allergenArea");

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

  //クリックしたら絞り込みの表示／非表示
  toggleBtn.addEventListener("click", () => {
    allergenArea.style.display = (allergenArea.style.display === "none") ? "block" : "none";
  });
  //クリックしたら絞り込みの表示／非表示
  toggleBtn.addEventListener("click", () => {
    const isHidden = window.getComputedStyle(allergenArea).display === "none";
    allergenArea.style.display = isHidden ? "block" : "none";
    toggleBtn.textContent = isHidden ? "絞り込み条件を隠す" : "絞り込み条件を表示";
  });

  //クリックした際の文言の切り替え
  toggleBtn.addEventListener("click", () => {
    const isHidden = allergenArea.style.display === "none";
    allergenArea.style.display = isHidden ? "block" : "none";
    toggleBtn.textContent = isHidden ? "絞り込み条件を隠す" : "絞り込み条件を表示";
  });

  // hiddenフィールド更新
  function updateHiddenField() {
    const selected = Array.from(buttons)
                          .filter(b => b.classList.contains("selected"))
                          .map(b => b.dataset.allergen);
    hiddenInput.value = selected.join(",");
  }
});