package pl.ug.ug_ci.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByName(String title) {
        return orderRepository.findAll().stream()
                .filter(name -> name.getName().toLowerCase()
                        .contains(title.toLowerCase())).collect(Collectors.toList());
    }
//TODO zmiana na datę ze Stringa
    public List<Order> findByAccountDay(String date) {
        return orderRepository.findAll().stream()
                .filter(d -> d.getPostingoOrderDate().toString().contains(date))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNameAlphabetically() {
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(name -> name.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNameReverse() {
        return orderRepository.findAll().stream()
                .sorted((name1, name2) ->name2.getName().compareToIgnoreCase(name1.getName()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNewestDate(){
        return orderRepository.findAll().stream()
                .sorted((date1,date2)->date2.getPostingoOrderDate().compareTo(date1.getPostingoOrderDate()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByLatestDate(){
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(Order::getPostingoOrderDate))
                .collect(Collectors.toList());
    }
}
