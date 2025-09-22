const searchInput = document.getElementById("searchInput");
const categoryFilter = document.getElementById("categoryFilter");
const sortSelect = document.getElementById("sortSelect");
const cardsContainer = document.getElementById("cardsContainer");
const cards = Array.from(document.querySelectorAll(".card-item"));

function renderCards(list) {
  cardsContainer.innerHTML = "";
  list.forEach((c) => cardsContainer.appendChild(c));
}

function filterAndSort() {
  const keyword = searchInput.value.toLowerCase();
  const category = categoryFilter.value;
  let filtered = cards.filter((c) => {
    const title = c.dataset.title.toLowerCase();
    const matchText = title.includes(keyword);
    const matchCat = !category || c.dataset.category === category;
    return matchText && matchCat;
  });

  // Sort
  if (sortSelect.value === "az") {
    filtered.sort((a, b) => a.dataset.title.localeCompare(b.dataset.title));
  } else if (sortSelect.value === "za") {
    filtered.sort((a, b) => b.dataset.title.localeCompare(a.dataset.title));
  } else if (sortSelect.value === "latest") {
    filtered.reverse(); // giả sử phần tử cuối là mới nhất
  }

  renderCards(filtered);
}

searchInput.addEventListener("input", filterAndSort);
categoryFilter.addEventListener("change", filterAndSort);
sortSelect.addEventListener("change", filterAndSort);
