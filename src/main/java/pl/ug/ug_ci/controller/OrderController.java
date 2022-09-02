package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
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

    //Optymalizacja wyszukiwania/sortowania:
    @GetMapping("search")
    public List<Order> findBy(@Param("keyword") String keyword) {
        return orderService.findBy(keyword);
    }

    //Optymalizacja sortowania - po nazwie rosnąco:
    @GetMapping("sort-name")
    public List<Order> sortByNameAs(){
        return orderService.sortByNameAsc();
    }

    //Optymalizacja sortowania  - po nazwie malejąco:
    @GetMapping("sort-name-desc")
    public List<Order> sortByNameDesc(){
        return orderService.sortByNameDesc();
    }

    //Optymalizacja sortowania  - po dacie od najnowszego:
    @GetMapping("sort-date")
    public List<Order> sortByDateAsc(){
        return orderService.sortByDateAsc();
    }

    //Optymalizacja sortowania  - po dacie od najstarszego:
    @GetMapping("sort-date-desc")
    public List<Order> sortByDateDesc(){
        return orderService.sortByDateDesc();
    }

    //Dodawanie rekordów:
    @PostMapping("save")
    public String saveOrder(@RequestBody Order order){
        orderService.saveOrder(order);
        return "Dodano...";
    }

    @GetMapping("by-name/{name}")
    public List<Order> findByName(@PathVariable String name) {
        return orderService.findByName(name);
    }


    @GetMapping("by-date/{date}")
    public List<Order> findByAccountDay(@PathVariable String date) {
        return orderService.findByAccountDay(date);
    }

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
