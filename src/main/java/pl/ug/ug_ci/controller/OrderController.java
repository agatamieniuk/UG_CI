package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.service.OrderService;
import java.util.List;

@RestController
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("by-name/{name}")
    public List<Order> findByName(@PathVariable String name) {
        return orderService.findByName(name);
    }


    @GetMapping("by-date/{date}")
    public List<Order> findByAccountDay(@PathVariable String date) {
        return orderService.findByAccountDay(date);
    }
//    metoda do użycia w przypadku skorzystania z repozytorium (pozostawiłam String, aby można było wyszukiwać po części daty):

//    public List<Order> findByAccountDay(@PathVariable LocalDate date) {
//        return orderService.findByAccountDay(date);}

    @GetMapping("sorted-name")
    public List<Order> sortedName() {
        return orderService.sortByNameAlphabetically();
    }

    @GetMapping("sorted-name-reverse")
    public List<Order> sortedNameReverse() {
        return orderService.sortByNameReverse();
    }

    @GetMapping("sorted-date-newest")
    public List<Order> sortedDateNewest() {
        return orderService.sortByNewestDate();
    }

    @GetMapping("sorted-date-latest")
    public List<Order> sortedDateLatest() {
        return orderService.sortByLatestDate();
    }
}
