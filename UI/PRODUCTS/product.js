const apiUrl = "http://localhost:8084/api/products";

// Fetch all products and display in table
function fetchProducts() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            let productTable = document.getElementById("productTable");
            productTable.innerHTML = ""; // Clear previous content

            data.forEach(product => {
                let row = `
                    <tr>
                        <td>${product.productId}</td>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>$${product.price.toFixed(2)}</td>
                        <td>${product.stockQuantity}</td>
                        <td>${product.category}</td>
                        <td>
                            <button onclick="editProduct('${product.productId}')">Edit</button>
                            <button onclick="deleteProduct('${product.productId}')">Delete</button>
                        </td>
                    </tr>`;
                productTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}

// Add a new product
function addProduct() {
    let product = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        price: parseFloat(document.getElementById("price").value),
        stockQuantity: parseInt(document.getElementById("stock").value),
        category: document.getElementById("category").value
    };

    fetch(apiUrl + "/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(product)
    })
    .then(response => response.json())
    .then(() => {
        alert("Product added successfully!");
        fetchProducts();
        document.getElementById("productForm").reset(); // Clear form
    })
    .catch(error => console.error("Error adding product:", error));
}

// Edit a product
function editProduct(productId) {
    let name = prompt("Enter new name:");
    let description = prompt("Enter new description:");
    let price = prompt("Enter new price:");
    let stock = prompt("Enter new stock quantity:");
    let category = prompt("Enter new category:");

    let updatedProduct = {
        productId,
        name,
        description,
        price: parseFloat(price),
        stockQuantity: parseInt(stock),
        category
    };

    fetch(`${apiUrl}/${productId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedProduct)
    })
    .then(response => response.json())
    .then(() => {
        alert("Product updated successfully!");
        fetchProducts();
    })
    .catch(error => console.error("Error updating product:", error));
}

// Delete a product
function deleteProduct(productId) {
    if (confirm("Are you sure you want to delete this product?")) {
        fetch(`${apiUrl}/${productId}`, { method: "DELETE" })
            .then(() => {
                alert("Product deleted successfully!");
                fetchProducts();
            })
            .catch(error => console.error("Error deleting product:", error));
    }
}

// Fetch products on page load
document.addEventListener("DOMContentLoaded", fetchProducts);
