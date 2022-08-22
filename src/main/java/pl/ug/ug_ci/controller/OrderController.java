package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("order")
public class OrderController {

    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("by_title/{title}")
    public List<Order> findByTitle(@PathVariable String title) {
        return orderService.findByName(title);
    }


    @GetMapping("by_date/{date}")
    public List<Order> findByAccountDay(@PathVariable String date) {
//        return orderService.findByAccountDay(date);
        return orderService.findByAccountDay(date);
    }

    @GetMapping("sortedName")
    public List<Order> sortedName() {
        return orderService.sortByNameAlphabetically();
    }

    @GetMapping("sortedNameReverse")
    public List<Order> sortedNameReverse() {
        return orderService.sortByNameReverse();
    }

    @GetMapping("sortedDateNewest")
    public List<Order> sortedDateNewest() {
        return orderService.sortByNewestDate();
    }

    @GetMapping("sortedDateLatest")
    public List<Order> sortedDateLatest() {
        return orderService.sortByLatestDate();
    }
}
