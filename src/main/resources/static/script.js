
 .catch(error => console.error("Error fetching menu:", error));

    // Add menu item to the cart
    function addToCart(menuItem) {
        const existingItem = cart.find(item => item.id === menuItem.id);
        if (existingItem) {
            existingItem.quantity++;
        } else {
            cart.push(menuItem);
        }
        updateOrderDetails();
    }

    // Update the order details section
    function updateOrderDetails() {
        orderDetails.innerHTML = ""; // Clear the current order details
        cart.forEach(item => {
            const itemElement = document.createElement("p");
            itemElement.textContent = `${item.itemName} (x${item.quantity}) - ₹${(item.price * item.quantity).toFixed(2)}`;
            orderDetails.appendChild(itemElement);
        });
    }

    // Handle order form submission
    document.getElementById("order-form").addEventListener("submit", function (event) {
        event.preventDefault();

        const customerName = document.getElementById("customerName").value;
        const contactNumber = document.getElementById("contactNumber").value;
        const deliveryOption = document.getElementById("deliveryOption").value;

        if (cart.length === 0) {
            alert("Your cart is empty!");
            return;
        }

        const orderData = {
            customerName,
            contactNumber,
            deliveryOption,
            items: cart
        };

        fetch("http://localhost:8080/customer/order", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(orderData)
        })
            .then(response => response.json())
            .then(data => {
                alert("Order placed successfully!");
                cart.length = 0; // Clear the cart
                updateOrderDetails();
            })
            .catch(error => {
                alert("Failed to place order.");
            });
    });

    // Handle cancel order
    document.getElementById("cancel-order").addEventListener("click", function () {
        if (confirm("Are you sure you want to cancel the order?")) {
            cart.length = 0; // Clear the cart
            updateOrderDetails();
        }
    });
});
