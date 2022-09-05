package pl.ug.ug_ci.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.webclient.converter.ConverterClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest2 {


    OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    ConverterClient converterClient = Mockito.mock(ConverterClient.class);
    OrderService orderService = new OrderService(orderRepository, converterClient);

    @Test
    public void testFindAll() {
        orderService.findAll();
        Mockito.verify(orderRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Powinno zwrócić fałsz, gdy nazwa komputera nie brzmi 'komputer 1'")
    public void testFindBy() {
        orderService.findBy("komputer 1");
        Mockito.verify(orderRepository, Mockito.times(1)).findAllBy("komputer 1");
    }

    @Test
    @DisplayName("Powinno zwrócić fałsz, gdy zapisany obiekt nie jest równoważny ze wskazanym")
    public void testSaveOrder() {
        Order order = new Order();
        ConverterDto converterDto = new ConverterDto(LocalDate.of(2022, 01, 03), 3.2F);

        order.setName("komputer Agaty");
        order.setOrderPostingDate(LocalDate.of(2022, 01, 03));
        order.setPayInDollar(345F);

        Mockito.when(converterClient.getDateforConvertion(LocalDate.of(2022, 01, 03))).thenReturn(converterDto);
        orderService.saveOrder(order);
        Mockito.verify(converterClient, Mockito.times(1)).getDateforConvertion(LocalDate.of(2022, 01, 03));
        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }
}
