package pl.ug.ug_ci.model;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Getter
@XmlRootElement(name = "order")
public class Orders {
    private List<Order> orders;

    @XmlElement(name = "order")
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void add(Order order) {
        if (this.orders == null) {
            this.orders = new ArrayList<>();
        }
        this.orders.add(order);
    }
}
