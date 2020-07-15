package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;

public interface OrderRepository {
    public Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address);
    public Order placeOrder(Order order);
}
