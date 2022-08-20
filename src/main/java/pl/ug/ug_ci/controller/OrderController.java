package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    final OrderRepository orderRepository;

    @GetMapping("/order")
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
