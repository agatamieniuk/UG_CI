package pl.ug.ug_ci.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.service.OrderService;
import pl.ug.ug_ci.webclient.converter.ConverterClient;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ConverterClient converterClient;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        orderService.findAll();
        if (orderService.findAll().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    //Optymalizacja wyszukiwania/sortowania:
    @GetMapping("search")
    public ResponseEntity<List<Order>> findBy(@Param("keyword") String keyword) {
        orderService.findBy(keyword);
        if (orderService.findBy(keyword).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.findBy(keyword), HttpStatus.OK);
    }

    //Optymalizacja sortowania - po nazwie rosnąco:
    @GetMapping("sort-name")
    public ResponseEntity<List<Order>> sortByNameAs() {
        orderService.sortByNameAsc();
        if (orderService.sortByNameAsc().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.sortByNameAsc(), HttpStatus.OK);
    }

    //Optymalizacja sortowania  - po nazwie malejąco:
    @GetMapping("sort-name-desc")
    public ResponseEntity<List<Order>> sortByNameDesc() {
        orderService.sortByNameDesc();
        if (orderService.sortByNameDesc().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.sortByNameDesc(), HttpStatus.OK);
    }

    //Optymalizacja sortowania  - po dacie od najnowszego:
    @GetMapping("sort-date")
    public ResponseEntity<List<Order>> sortByDateAsc() {
        orderService.sortByDateAsc();
        if(orderService.sortByDateAsc().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.sortByDateAsc(), HttpStatus.OK);
    }

    //Optymalizacja sortowania  - po dacie od najstarszego:
    @GetMapping("sort-date-desc")
    public ResponseEntity<List<Order>> sortByDateDesc() {
        orderService.sortByDateDesc();
        if(orderService.sortByDateDesc().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderService.sortByDateDesc(), HttpStatus.OK);
    }

    //Dodawanie rekordów przez żądanie HTTP:
    @PostMapping("save")
    public String saveOrder(@RequestBody Order order) {
//TODO: Czy dobrze?
        try {
            orderService.saveOrder(order);
        } catch (HttpClientErrorException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (HttpMessageNotReadableException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return "Dodano...";
    }

    //Edycja rekordów przez żądanie HTTP:
    @PutMapping("update/{id}")
    public String updateOrder(@PathVariable Integer id, @RequestBody Order order) {
        try {
            Order updatedOrder = orderService.findById(id);
            updatedOrder.setName(order.getName());
            updatedOrder.setOrderPostingDate(order.getOrderPostingDate());
            updatedOrder.setPayInDollar(order.getPayInDollar());
            ConverterDto converter = converterClient.getDateforConvertion(order.getOrderPostingDate());
            order.setPayInPLN(converter.getExchangeRate() * order.getPayInDollar());

            orderService.saveOrder(updatedOrder);
//TODO: Czy dobrze?
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        } catch (HttpClientErrorException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "Edytowano...";
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
