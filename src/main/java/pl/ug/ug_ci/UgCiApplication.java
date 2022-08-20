package pl.ug.ug_ci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;

import java.time.LocalDate;

@SpringBootApplication
public class UgCiApplication implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(UgCiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setName("komputer 1");
        order.setPostingoOrderDate(LocalDate.of(2022,01,03));
        order.setUsdcost(345.0);
        orderRepository.save(order);

    }
}
