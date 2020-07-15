package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;

public interface OrderService {
    Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address);
    Order placeOrder(Order order);
}
