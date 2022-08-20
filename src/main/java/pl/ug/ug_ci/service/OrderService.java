package pl.ug.ug_ci.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByName(String title) {
        return orderRepository.findAll().stream()
                .filter(name->name.getName().toLowerCase()
                        .contains(title.toLowerCase())).collect(Collectors.toList());
    }

    public List<Order> findByAccountDay(String date){
        return orderRepository.findAll().stream()
                .filter(d->d.getPostingoOrderDate().toString().contains(date))
                .collect(Collectors.toList());
    }

}
