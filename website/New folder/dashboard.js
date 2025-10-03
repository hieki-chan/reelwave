let editIndex = -1; // -1 = thêm mới, khác -1 = sửa
// Data mẫu
let products = [
  {
    id: 1,
    name: "Galaxy S23",
    brand: "Samsung",
    price: 16990000,
    stock: 12,
    image: "https://via.placeholder.com/60",
  },
  {
    id: 2,
    name: "iPhone 14",
    brand: "Apple",
    price: 24990000,
    stock: 8,
    image: "https://via.placeholder.com/60",
  },
  {
    id: 3,
    name: "Redmi Note 12",
    brand: "Xiaomi",
    price: 4990000,
    stock: 20,
    image: "https://via.placeholder.com/60",
  },
  {
    id: 1,
    name: "Galaxy S23",
    brand: "Samsung",
    price: 16990000,
    stock: 12,
    image: "https://via.placeholder.com/60",
  },
  {
    id: 1,
    name: "Galaxy S23",
    brand: "Samsung",
    price: 16990000,
    stock: 12,
    image: "https://via.placeholder.com/60",
  },
];

function renderProducts() {
  const tbody = document.getElementById("productsTable");
  tbody.innerHTML = "";

  let totalRevenue = 0; // biến tính doanh thu

  products.forEach((p, i) => {
    tbody.innerHTML += `
      <tr>
        <td>${i + 1}</td>
        <td><img src="${p.image}" class="img-thumbnail" width="60"></td>
        <td>${p.name}</td>
        <td>${p.brand}</td>
        <td>${p.price.toLocaleString()}₫</td>
        <td>${p.stock}</td>
        <td>
          <button class="btn btn-sm btn-primary btn-edit" data-index="${i}">
            <i class="bi bi-pencil"></i>
          </button>
          <button class="btn btn-sm btn-danger btn-delete" data-index="${i}">
            <i class="bi bi-trash"></i>
          </button>
        </td>
      </tr>
    `;

    // cộng dồn doanh thu = giá * số lượng
    totalRevenue += p.price * p.stock;
  });

  // cập nhật thống kê
  document.getElementById("stat-products").innerText = products.length;
  document.getElementById("stat-orders").innerText = 15;
  document.getElementById("stat-revenue").innerText =
    totalRevenue.toLocaleString() + "₫"; // <-- sửa ở đây
  document.getElementById("stat-customers").innerText = 45;

  // --- Gắn sự kiện xoá ---
  document.querySelectorAll(".btn-delete").forEach((btn) => {
    btn.addEventListener("click", function () {
      const index = this.getAttribute("data-index");
      products.splice(index, 1);
      renderProducts();
    });
  });

  // --- Gắn sự kiện sửa ---
  document.querySelectorAll(".btn-edit").forEach((btn) => {
    btn.addEventListener("click", function () {
      const index = this.getAttribute("data-index");
      editIndex = index;
      const p = products[index];

      document.getElementById("productName").value = p.name;
      document.getElementById("productBrand").value = p.brand;
      document.getElementById("productPrice").value = p.price;
      document.getElementById("productStock").value = p.stock;
      document.getElementById("productImage").value = p.image;

      document.getElementById("addProductLabel").innerText =
        "Chỉnh sửa sản phẩm";
      document.querySelector("#addProductForm button[type=submit]").innerText =
        "Cập nhật";

      const modal = new bootstrap.Modal(
        document.getElementById("addProductModal")
      );
      modal.show();
    });
  });
}

renderProducts();

document
  .getElementById("addProductForm")
  .addEventListener("submit", function (e) {
    e.preventDefault();
    const name = document.getElementById("productName").value.trim();
    const brand = document.getElementById("productBrand").value.trim();
    const price = parseInt(document.getElementById("productPrice").value);
    const stock = parseInt(document.getElementById("productStock").value);
    const image =
      document.getElementById("productImage").value.trim() ||
      "https://via.placeholder.com/60";

    if (editIndex === -1) {
      // 👉 Thêm mới
      products.push({
        id: products.length + 1,
        name,
        brand,
        price,
        stock,
        image,
      });
    } else {
      // 👉 Sửa sản phẩm
      products[editIndex] = {
        ...products[editIndex], // giữ id cũ
        name,
        brand,
        price,
        stock,
        image,
      };
      editIndex = -1; // reset về thêm mới
    }

    renderProducts();

    // reset form
    const modal = bootstrap.Modal.getInstance(
      document.getElementById("addProductModal")
    );
    modal.hide();
    document.getElementById("addProductForm").reset();

    // đổi lại tiêu đề & nút
    document.getElementById("addProductLabel").innerText = "Thêm sản phẩm mới";
    document.querySelector("#addProductForm button[type=submit]").innerText =
      "Lưu";
  });
