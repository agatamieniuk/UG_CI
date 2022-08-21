package pl.ug.ug_ci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.model.Orders;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.service.OrderService;

import javax.xml.bind.JAXBException;
import java.time.LocalDate;

@SpringBootApplication
public class UgCiApplication implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(UgCiApplication.class, args);
    }

    @Override
    public void run(String... args) throws JAXBException {

        //TODO dodawanie nowego order - co z setterem PLN?!
        Order order = new Order();
        order.setName("MacOS komputer 1");
        order.setPostingoOrderDate(LocalDate.of(2022, 06, 03));
        order.setUsdcost(345.0);
        orderRepository.save(order);

        Order order1 = new Order();
        order1.setName("dell komputer 2");
        order1.setPostingoOrderDate(LocalDate.of(2020, 06, 03));
        order1.setUsdcost(543.0);
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setName("ATARI komputer 3");
        order2.setPostingoOrderDate(LocalDate.of(2019, 12, 03));
        order2.setUsdcost(346.0);
        orderRepository.save(order2);

        Order order3 = new Order();
        order3.setName("biały komputer 3");
        order3.setPostingoOrderDate(LocalDate.of(2019, 12, 04));
        order3.setUsdcost(346.0);
        orderRepository.save(order3);

        Orders orders = new Orders();
        orders.add(order);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        OrderService.convertOrderToXML(orders);
    }
}
