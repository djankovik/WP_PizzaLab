package mk.finki.ukim.mk.lab.repository.impl;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Order placeOrder(String pizzaType, String pizzaSize, String clientName, String address) {
        Order or = new Order(pizzaType,pizzaSize,clientName,address);
        DataHolder.orders.add(or);
        return or;
    }

    @Override
    public Order placeOrder(Order order) {
        DataHolder.orders.add(order);
        return order;
    }
}
