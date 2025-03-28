const apiUrl = "http://localhost:8084/api/products";

// Load products on page load
document.addEventListener("DOMContentLoaded", () => {
    loadProductDropdown();
});

// Fetch products for dropdown
function loadProductDropdown() {
    fetch(apiUrl)
        .then(response => response.json())
        .then(data => {
            let dropdown = document.getElementById("productDropdown");
            dropdown.innerHTML = '<option value="">-- Select a Product --</option>';

            data.forEach(product => {
                let option = document.createElement("option");
                option.value = product.productId;
                option.textContent = product.name;
                dropdown.appendChild(option);
            });
        })
        .catch(error => console.error("Error loading dropdown:", error));
}

// Load product details
function loadProductDetails() {
    let selectedProductId = document.getElementById("productDropdown").value;
    if (!selectedProductId) return;

    fetch(`${apiUrl}/${selectedProductId}`)
        .then(response => response.json())
        .then(product => {
            document.getElementById("description").value = product.description;
            document.getElementById("price").value = product.price;
            document.getElementById("category").value = product.category;
            document.getElementById("stock").value = 1; // Default quantity
        })
        .catch(error => console.error("Error loading product details:", error));
}

// Update stock quantity
function updateStock(amount) {
    let stockInput = document.getElementById("stock");
    let newStock = parseInt(stockInput.value) + amount;
    stockInput.value = newStock < 1 ? 1 : newStock;
}

// Checkout action
function checkout() {
    let productName = document.getElementById("productDropdown").options[document.getElementById("productDropdown").selectedIndex].text;
    let quantity = document.getElementById("stock").value;

    if (!productName || quantity <= 0) {
        alert("Please select a valid product and quantity.");
        return;
    }

    alert(`Order placed: ${productName} - Quantity: ${quantity}`);
}
