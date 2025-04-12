# **Order Service Overview**

The Order Service is responsible for handling the creation and management of orders. It serves as the core service that coordinates between the Product Service and  Payment Service to process orders in a microservices architecture
### **Endpoints**

**Create a new order**

    Method: POST
    
    Endpoint: /order
    
    Description: Creates a new order.

## Key Features

#### Create Order endpoint that:

* Accepts a list of product IDs and quantities.
* Fetches product details (like price) from the Product Service via REST.
* Calculates the total order amount.
* Creates and saves the order with status PENDING_PAYMENT.
* Sends a request to the Payment Service to initiate payment and retrieves a payment link.
* Returns an OrderResponse containing the order details and the payment link.


#### External Service Integration

* Product Service - REST call to retrieve product detail

* Payment Service - REST call to create a payment and get the payment URL


#### TODO: 
* No authentication is implemented yet — any user can currently place an order.