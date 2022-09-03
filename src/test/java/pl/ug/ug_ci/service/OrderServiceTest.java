package pl.ug.ug_ci.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ug.ug_ci.model.Order;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testFindAll() {

        Order order = new Order(1, "komputer 1", LocalDate.of(2022, 01, 03), 345F, 1394.63F);

        assertAll("Find All",
                () -> assertEquals(order.getName(), orderService.findAll().get(0).getName()),
                () -> assertEquals(order.getOrderPostingDate(), orderService.findAll().get(0).getOrderPostingDate()),
                () -> assertEquals(order.getPayInDollar(), orderService.findAll().get(0).getPayInDollar()),
                () -> assertEquals(order.getPayInPLN(), orderService.findAll().get(0).getPayInPLN())
        );
    }

    @Test
    public void testFindBy() {
        Order order = new Order(1, "komputer 1", LocalDate.of(2022, 01, 03), 345F, 1394.63F);
        Order order2 = new Order(2, "C komputer 1", LocalDate.of(2022, 01, 10), 345F, 1382.21F);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);

        assertAll("Find By",
                () -> assertEquals(orderList.size(), orderService.findBy("komputer 1").size()),
                () -> assertEquals(orderList.get(0).getName(), orderService.findBy("komputer 1").get(0).getName()),
                () -> assertEquals(orderList.get(1).getName(), orderService.findBy("komputer 1").get(1).getName()),
                () -> assertEquals(orderList.get(0).getOrderPostingDate(), orderService.findBy("komputer 1").get(0).getOrderPostingDate()),
                () -> assertEquals(orderList.get(1).getOrderPostingDate(), orderService.findBy("komputer 1").get(1).getOrderPostingDate()),
                () -> assertEquals(orderList.get(0).getPayInDollar(), orderService.findBy("komputer 1").get(0).getPayInDollar()),
                () -> assertEquals(orderList.get(1).getPayInDollar(), orderService.findBy("komputer 1").get(1).getPayInDollar()),
                () -> assertEquals(orderList.get(0).getPayInPLN(), orderService.findBy("komputer 1").get(0).getPayInPLN()),
                () -> assertEquals(orderList.get(1).getPayInPLN(), orderService.findBy("komputer 1").get(1).getPayInPLN())
        );
    }

    //Poniżej korzystam z informacji, iż Comparatory zwracaja liczbę (ujemna/dodatnia/zero) ujemna – obiekt mniejszy, dodatnia – obiekt wiekszy (uwzględniając, iż zero oznacza obiekt rowny).
    @Test
    public void testSortByNameAsc() {
//        System.out.println(orderService.sortByNameAsc().get(0));//B komputer 2
//        System.out.println(orderService.sortByNameAsc().get(1));//C komputer 1
//        System.out.println(orderService.sortByNameAsc().get(2));//D komputer 2
        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(1).getName()) <= 0);
        assertTrue(orderService.sortByNameAsc().get(1).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
    }

    @Test
    public void testSortByNameDesc() {
        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(1).getName()) >= 0);
        assertTrue(orderService.sortByNameDesc().get(1).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
    }

    @Test
    public void testSortByDateAsc() {
//        System.out.println(orderService.sortByDateAsc().get(0));//1 <=id
//        System.out.println(orderService.sortByDateAsc().get(1));//3
//        System.out.println(orderService.sortByDateAsc().get(2));//5
//        System.out.println(orderService.sortByDateAsc().get(orderService.sortByDateAsc().size() - 1));//6
        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(1).getOrderPostingDate()) <= 0);
        assertTrue(orderService.sortByDateAsc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(2).getOrderPostingDate()) <= 0);
        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(orderService.sortByDateAsc().size() - 1).getOrderPostingDate()) <= 0);
    }

    @Test
    public void testSortByDateDesc() {
//        System.out.println(orderService.sortByDateDesc().get(0));//2 <=id
//        System.out.println(orderService.sortByDateDesc().get(1));//4
//        System.out.println(orderService.sortByDateDesc().get(2));//6
//        System.out.println(orderService.sortByDateDesc().get(orderService.sortByDateAsc().size() - 1));//5
        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(1).getOrderPostingDate()) >= 0);
        assertTrue(orderService.sortByDateDesc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(2).getOrderPostingDate()) >= 0);
        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(orderService.sortByDateDesc().size() - 1).getOrderPostingDate()) >= 0);
    }

    
}
