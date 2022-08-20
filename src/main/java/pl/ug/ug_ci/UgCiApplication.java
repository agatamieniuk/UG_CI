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
        order.setName("komputer MacOS 1");
        order.setPostingoOrderDate(LocalDate.of(2022,01,03));
        order.setUsdcost(345.0);
        orderRepository.save(order);

        Order order1 = new Order();
        order1.setName("komputer dell 2");
        order1.setPostingoOrderDate(LocalDate.of(2022,01,03));
        order1.setUsdcost(543.0);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setName("komputer ATARI 3");
        order2.setPostingoOrderDate(LocalDate.of(2022,01,03));
        order2.setUsdcost(346.0);
        orderRepository.save(order2);

    }
}
