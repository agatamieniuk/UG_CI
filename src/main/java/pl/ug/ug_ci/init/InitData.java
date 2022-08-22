package pl.ug.ug_ci.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.model.Orders;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.service.OrderService;
import pl.ug.ug_ci.webclient.converter.ConverterClient;
import java.time.LocalDate;

@Slf4j
@Component
public class InitData implements CommandLineRunner {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ConverterClient converterClient;

    @Override
    public void run(String... args) throws Exception {

        Order order = new Order();
        try {
            order.setName("MacOS komputer 1");
            order.setPayInDollar(345.0F);
            order.setOrderPostingDate(LocalDate.of(2022, 01, 03));
            ConverterDto converter = converterClient.getDateforConvertion(order.getOrderPostingDate());
            order.setPayInPLN(converter.getExchangeRate() * order.getPayInDollar());
            orderRepository.save(order);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order order1 = new Order();
        try {
            order1.setName("dell komputer 2");
            order1.setPayInDollar(543.0F);
            order1.setOrderPostingDate(LocalDate.of(2020, 06, 03));
            ConverterDto converter1 = converterClient.getDateforConvertion(order1.getOrderPostingDate());
            order1.setPayInPLN(converter1.getExchangeRate() * order.getPayInDollar());
            orderRepository.save(order1);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order order2 = new Order();
        try {
            order2.setName("ATARI komputer 3");
            order2.setPayInDollar(346.0F);
            order2.setOrderPostingDate(LocalDate.of(2019, 12, 03));
            ConverterDto converter2 = converterClient.getDateforConvertion(order2.getOrderPostingDate());
            order2.setPayInPLN(converter2.getExchangeRate() * order.getPayInDollar());
            orderRepository.save(order2);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order order3 = new Order();
        try {
            order3.setName("biały komputer 3");
            order3.setPayInDollar(346.0F);
            order3.setOrderPostingDate(LocalDate.of(2019, 12, 04));
            ConverterDto converter3 = converterClient.getDateforConvertion(order3.getOrderPostingDate());
            order3.setPayInPLN(converter3.getExchangeRate() * order.getPayInDollar());
            orderRepository.save(order3);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Orders orders = new Orders();
        orders.add(order);
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        OrderService.convertOrderToXML(orders);
    }
}
