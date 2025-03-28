document.addEventListener("DOMContentLoaded", function () {
    loadAnalytics();
});

function loadAnalytics() {
    fetch("http://localhost:8085/api/orders")
        .then(response => response.json())
        .then(orders => {
            let totalOrders = orders.length;
            let totalRevenue = orders.reduce((sum, order) => sum + order.totalAmount, 0).toFixed(2);
            let pendingOrders = orders.filter(order => order.paymentStatus === "Pending").length;
            let paidOrders = totalOrders - pendingOrders;

            document.getElementById("totalOrders").innerText = totalOrders;
            document.getElementById("totalRevenue").innerText = totalRevenue;
            document.getElementById("pendingOrders").innerText = pendingOrders;
            document.getElementById("paidOrders").innerText = paidOrders;

            renderOrderChart(orders);
        })
        .catch(error => console.error("Error fetching order analytics:", error));
}

function renderOrderChart(orders) {
    let dates = {};

    orders.forEach(order => {
        let date = new Date(order.created_at).toLocaleDateString();
        dates[date] = (dates[date] || 0) + 1;
    });

    let labels = Object.keys(dates);
    let data = Object.values(dates);

    const ctx = document.getElementById("orderChart").getContext("2d");
    new Chart(ctx, {
        type: "line",
        data: {
            labels: labels,
            datasets: [{
                label: "Orders per Day",
                data: data,
                borderColor: "#007bff",
                fill: false,
                tension: 0.1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    display: false
                }
            }
        }
    });
}
