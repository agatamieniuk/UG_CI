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

        Order komputer1 = new Order();
        try {
            komputer1.setName("A komputer 1");
            komputer1.setPayInDollar(345F);
            komputer1.setOrderPostingDate(LocalDate.of(2022, 01, 03));
            ConverterDto converter1 = converterClient.getDateforConvertion(komputer1.getOrderPostingDate());
            komputer1.setPayInPLN(converter1.getExchangeRate() * komputer1.getPayInDollar());
            orderRepository.save(komputer1);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order komputer1a = new Order();
        try {
            komputer1a.setName("C komputer 1");
            komputer1a.setPayInDollar(345F);
            komputer1a.setOrderPostingDate(LocalDate.of(2022, 01, 10));
            ConverterDto converter1a = converterClient.getDateforConvertion(komputer1a.getOrderPostingDate());
            komputer1a.setPayInPLN(converter1a.getExchangeRate() * komputer1a.getPayInDollar());
            orderRepository.save(komputer1a);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order komputer2 = new Order();
        try {
            komputer2.setName("B komputer 2");
            komputer2.setPayInDollar(543F);
            komputer2.setOrderPostingDate(LocalDate.of(2022, 01, 03));
            ConverterDto converter2 = converterClient.getDateforConvertion(komputer2.getOrderPostingDate());
            komputer2.setPayInPLN(converter2.getExchangeRate() * komputer2.getPayInDollar());
            orderRepository.save(komputer2);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order komputer2a = new Order();
        try {
            komputer2a.setName("D komputer 2");
            komputer2a.setPayInDollar(543F);
            komputer2a.setOrderPostingDate(LocalDate.of(2022, 01, 10));
            ConverterDto converter2a = converterClient.getDateforConvertion(komputer2a.getOrderPostingDate());
            komputer2a.setPayInPLN(converter2a.getExchangeRate() * komputer2a.getPayInDollar());
            orderRepository.save(komputer2a);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order komputer3 = new Order();
        try {
            komputer3.setName("F komputer 3");
            komputer3.setPayInDollar(346F);
            komputer3.setOrderPostingDate(LocalDate.of(2022, 01, 03));
            ConverterDto converter3 = converterClient.getDateforConvertion(komputer3.getOrderPostingDate());
            komputer3.setPayInPLN(converter3.getExchangeRate() * komputer3.getPayInDollar());
            orderRepository.save(komputer3);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Order komputer3a = new Order();
        try {
            komputer3a.setName("E komputer 3");
            komputer3a.setPayInDollar(346F);
            komputer3a.setOrderPostingDate(LocalDate.of(2022, 01, 10));
            ConverterDto converter3a = converterClient.getDateforConvertion(komputer3a.getOrderPostingDate());
            komputer3a.setPayInPLN(converter3a.getExchangeRate() * komputer3a.getPayInDollar());
            orderRepository.save(komputer3a);
        } catch (HttpClientErrorException ex) {
            log.warn("Brak opublikowanego kursu na ww. dzień", ex);
        }

        Orders orders = new Orders();
        orders.add(komputer1);
        orders.add(komputer1a);
        orders.add(komputer2);
        orders.add(komputer2a);
        orders.add(komputer3);
        orders.add(komputer3a);
        OrderService.convertOrderToXML(orders);
    }
}
