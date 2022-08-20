package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.service.OrderService;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping("/order")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("order/{title}")
    public List<Order> findByTitle(@PathVariable String title, Order order) {
        return orderService.findByName(title);
    }


}
