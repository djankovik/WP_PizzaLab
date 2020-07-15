package mk.finki.ukim.mk.lab.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="ORDERS")
@NoArgsConstructor
@Data
public class Order {
    String pizzaType;
    String pizzaSize;
    String clientName;
    String clientAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Transient
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static Long orderCounter = Long.valueOf(1);

    public static synchronized Long getAndIncrementCount() {
        return orderCounter++;
    }

    public Order(String pizzaType, String pizzaSize, String clientName, String clientAddress){
        this.pizzaType=pizzaType;
        this.pizzaSize=pizzaSize;
        this.clientName=clientName;
        this.clientAddress=clientAddress;
    }
    public static Order createOrder(String pizzaType,String pizzaSize, String clientName, String address){
        Order order = new Order(pizzaType,pizzaSize,clientName,address);
        order.pizzaType=pizzaType;
        order.pizzaSize=pizzaSize;
        order.clientName=clientName;
        order.clientAddress=address;
        return order;
    }
}