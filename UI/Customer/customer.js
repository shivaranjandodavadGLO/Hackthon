const apiUrl = "http://localhost:8084/api/products";
const orderApiUrl = "http://localhost:8085/api/orders/create";

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
    let productDropdown = document.getElementById("productDropdown");
    let selectedProductId = productDropdown.value;
    let productName = productDropdown.options[productDropdown.selectedIndex].text;
    let price = parseFloat(document.getElementById("price").value);
    let quantity = parseInt(document.getElementById("stock").value);
    let userId = prompt("Enter your User ID:");

    if (!selectedProductId || quantity <= 0 || !userId) {
        alert("Please select a valid product, quantity, and enter your User ID.");
        return;
    }

    // Construct order payload
    let orderData = {
        orderId: "null", // Backend generates orderId automatically
        userId: userId,
        totalAmount: price * quantity,
        paymentStatus: "Pending",
        created_at: new Date().toISOString()
    };

    // Send order request
    fetch(orderApiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(orderData),
    })
    .then(response => response.text())
    .then(responseMessage => {
        alert("Order completed! You will receive a confirmation soon.");
    })
    .catch(error => {
        console.error("Error placing order:", error);
        alert("Failed to place order. Please try again.");
    });
}
