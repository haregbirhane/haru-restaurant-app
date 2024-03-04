package com.example.harurestaurant.demo.service;

import com.example.harurestaurant.demo.exceptions.ResourceNotFoundException;
import com.example.harurestaurant.demo.model.Order;
import com.example.harurestaurant.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));

        // Update the necessary fields
        existingOrder.setItemName(orderDetails.getItemName());
        existingOrder.setItemPrice(orderDetails.getItemPrice());
        existingOrder.setCustomerName(orderDetails.getCustomerName());
        existingOrder.setCustomerNumber(orderDetails.getCustomerNumber());
        existingOrder.setCustomerEmail(orderDetails.getCustomerEmail());
        existingOrder.setStatus(orderDetails.getStatus());

        return orderRepository.save(existingOrder);
    }

    public String deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + id));

        String responseMessage = "Hello " + order.getCustomerName() + ",\n\nYour order for " + order.getItemName() +
                " priced at $" + order.getItemPrice() + " has been deleted.\n\nThank you for using Haru Restaurant.";

        orderRepository.delete(order);
        return responseMessage;
    }


    public String getOrderStatusMessage(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return "Hello " + order.getCustomerName() + ",\n\nYour order status is: " + order.getStatus() + ".\n\nThank you for using Haru Restaurant\nHareg(Owner)";
    }

}
