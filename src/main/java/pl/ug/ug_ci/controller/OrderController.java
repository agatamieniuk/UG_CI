package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.model.Order;
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

    @GetMapping("order/by_title/{title}")
    public List<Order> findByTitle(@PathVariable String title) {
        return orderService.findByName(title);
    }


    @GetMapping("order/by_date/{date}")
    public List<Order> findByAccountDay(@PathVariable String date) {
        return orderService.findByAccountDay(date);
    }

    @GetMapping("order/sortedName")
    public List<Order> sortedName() {
        return orderService.sortByNameAlphabetically();
    }

        @GetMapping("order/sortedNameReverse")
    public List<Order> sortedNameReverse() {
        return orderService.sortByNameReverse();
    }
}
