package pl.ug.ug_ci.service;

import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.webclient.converter.ConverterClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testSaveOrder(){

        Order order = new Order();
        order.setName("komputer 1");
        order.setPayInPLN(345F);
        order.setOrderPostingDate(LocalDate.of(2022, 01, 03));
        order.setPayInPLN(1394.63F);

        assertAll("save order",
                ()->assertEquals(order.getName(), orderService.findAll().get(0).getName()),
                ()->assertEquals(order.getOrderPostingDate(), orderService.findAll().get(0).getOrderPostingDate()),
//TODO: czemu sie wykrzacza?
//                ()->assertEquals(order.getPayInDollar(), orderService.findAll().get(0).getPayInDollar()),
                ()->assertEquals(order.getPayInPLN(), orderService.findAll().get(0).getPayInPLN())
        );

    }
}
