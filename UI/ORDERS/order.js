document.addEventListener("DOMContentLoaded", function () {
    loadOrders();
});

// Fetch orders from API
function loadOrders() {
    fetch("http://localhost:8085/api/orders")
        .then(response => response.json())
        .then(orders => {
            const tableBody = document.getElementById("orderTableBody");
            tableBody.innerHTML = "";

            orders.forEach(order => {
                const row = document.createElement("tr");

                // Payment status icon
                let statusIcon = order.paymentStatus === "Paid" ? "paid.svg" : "pending.svg";

                row.innerHTML = `
                    <td>${order.orderId}</td>
                    <td>${order.userId}</td>
                    <td>$${order.totalAmount.toFixed(2)}</td>
                    <td>
                        ${order.paymentStatus}
                        <img src="${statusIcon}" class="status-icon">
                    </td>
                    <td>${new Date(order.created_at).toLocaleString()}</td>
                `;

                // Click listener to fetch order details
                row.addEventListener("click", () => fetchOrderDetailsById(order.orderId));

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error loading orders:", error));
}

// Fetch Order Details by Order ID
function fetchOrderDetailsById(orderId) {
    fetch(`http://localhost:8085/api/orders/${orderId}`)
        .then(response => response.json())
        .then(order => {
            document.getElementById("modalOrderId").innerText = order.orderId;
            document.getElementById("modalUserId").innerText = order.userId;
            document.getElementById("modalTotalAmount").innerText = order.totalAmount.toFixed(2);
            document.getElementById("modalPaymentStatus").innerText = order.paymentStatus;
            document.getElementById("modalCreatedAt").innerText = new Date(order.created_at).toLocaleString();

            // Update modal payment status icon
            document.getElementById("modalPaymentIcon").src = order.paymentStatus === "Paid" ? "paid.svg" : "pending.svg";

            openModal();
        })
        .catch(error => console.error("Error fetching order details:", error));
}

// Open Modal
function openModal() {
    document.getElementById("orderModal").style.display = "flex";
}

// Close Modal
function closeModal() {
    document.getElementById("orderModal").style.display = "none";
}
