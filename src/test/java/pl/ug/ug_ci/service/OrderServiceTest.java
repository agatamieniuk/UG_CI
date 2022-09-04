package pl.ug.ug_ci.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ug.ug_ci.model.Order;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testFindAll() {
        Order order = new Order(1, "komputer 1", LocalDate.of(2022, 01, 03), 345F, 1394.63F);

        assertThat(orderService.findAll()).isNotNull();
        assertAll("Find All",
                () -> assertEquals(order.getName(), orderService.findAll().get(0).getName()),
                () -> assertEquals(order.getOrderPostingDate(), orderService.findAll().get(0).getOrderPostingDate()),
                () -> assertEquals(order.getPayInDollar(), orderService.findAll().get(0).getPayInDollar()),
                () -> assertEquals(order.getPayInPLN(), orderService.findAll().get(0).getPayInPLN())
        );
    }

    @Test
    public void testFindBy() {
        List<Order> orderList = orderService.findBy("komputer 1");
        assertThat(orderList)
                .isNotNull()
                .hasSize(2)
                .containsOnly(orderService.findAll().get(0), orderService.findAll().get(1));
    }
//    @Test
//    public void testFindBy() {
//        Order order = new Order(1, "komputer 1", LocalDate.of(2022, 01, 03), 345F, 1394.63F);
//        Order order2 = new Order(2, "C komputer 1", LocalDate.of(2022, 01, 10), 345F, 1382.21F);
//
//        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        orderList.add(order2);
//
//        assertAll("Find By",
//                () -> assertEquals(orderList.size(), orderService.findBy("komputer 1").size()),
//                () -> assertEquals(orderList.get(0).getName(), orderService.findBy("komputer 1").get(0).getName()),
//                () -> assertEquals(orderList.get(1).getName(), orderService.findBy("komputer 1").get(1).getName()),
//                () -> assertEquals(orderList.get(0).getOrderPostingDate(), orderService.findBy("komputer 1").get(0).getOrderPostingDate()),
//                () -> assertEquals(orderList.get(1).getOrderPostingDate(), orderService.findBy("komputer 1").get(1).getOrderPostingDate()),
//                () -> assertEquals(orderList.get(0).getPayInDollar(), orderService.findBy("komputer 1").get(0).getPayInDollar()),
//                () -> assertEquals(orderList.get(1).getPayInDollar(), orderService.findBy("komputer 1").get(1).getPayInDollar()),
//                () -> assertEquals(orderList.get(0).getPayInPLN(), orderService.findBy("komputer 1").get(0).getPayInPLN()),
//                () -> assertEquals(orderList.get(1).getPayInPLN(), orderService.findBy("komputer 1").get(1).getPayInPLN())
//        );
//    }

    @Test
    public void testSortByNameAsc() {
        List<Order> orderList = orderService.sortByNameAsc();
        assertThat(orderList)
                .isNotNull()
                .isSortedAccordingTo(Comparator.comparing(name -> name.getName().toLowerCase()));
    }

    @Test
    public void testSortByNameDesc() {
        List<Order> orderList = orderService.sortByNameDesc();
        assertThat(orderList)
                .isNotNull()
                .isSortedAccordingTo((name1, name2) -> name2.getName().compareToIgnoreCase(name1.getName()));
    }

    @Test
    public void testSortByDateAsc() {
        List<Order> orderList = orderService.sortByDateAsc();
        assertThat(orderList)
                .isNotNull()
                .isSortedAccordingTo(Comparator.comparing(Order::getOrderPostingDate));
    }

    @Test
    public void testSortByDateDesc() {
        List<Order> orderList = orderService.sortByDateDesc();
        assertThat(orderList)
                .isNotNull()
                .isSortedAccordingTo((date1, date2) -> date2.getOrderPostingDate().compareTo(date1.getOrderPostingDate()));
    }

//    //Poniżej korzystam z informacji, iż Comparatory zwracaja liczbę (ujemna/dodatnia/zero) ujemna – obiekt mniejszy, dodatnia – obiekt wiekszy (uwzględniając, iż zero oznacza obiekt rowny).
//    @Test
//    public void testSortByNameAsc() {
////        System.out.println(orderService.sortByNameAsc().get(0));//B komputer 2
////        System.out.println(orderService.sortByNameAsc().get(1));//C komputer 1
////        System.out.println(orderService.sortByNameAsc().get(2));//D komputer 2
//        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(1).getName()) <= 0);
//        assertTrue(orderService.sortByNameAsc().get(1).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
//        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
//    }

//    @Test
//    public void testSortByNameDesc() {
//        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(1).getName()) >= 0);
//        assertTrue(orderService.sortByNameDesc().get(1).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
//        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
//    }
//
//    @Test
//    public void testSortByDateAsc() {
////        System.out.println(orderService.sortByDateAsc().get(0));//1 <=id
////        System.out.println(orderService.sortByDateAsc().get(1));//3
////        System.out.println(orderService.sortByDateAsc().get(2));//5
////        System.out.println(orderService.sortByDateAsc().get(orderService.sortByDateAsc().size() - 1));//6
//        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(1).getOrderPostingDate()) <= 0);
//        assertTrue(orderService.sortByDateAsc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(2).getOrderPostingDate()) <= 0);
//        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(orderService.sortByDateAsc().size() - 1).getOrderPostingDate()) <= 0);
//    }
//
//    @Test
//    public void testSortByDateDesc() {
////        System.out.println(orderService.sortByDateDesc().get(0));//2 <=id
////        System.out.println(orderService.sortByDateDesc().get(1));//4
////        System.out.println(orderService.sortByDateDesc().get(2));//6
////        System.out.println(orderService.sortByDateDesc().get(orderService.sortByDateAsc().size() - 1));//5
//        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(1).getOrderPostingDate()) >= 0);
//        assertTrue(orderService.sortByDateDesc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(2).getOrderPostingDate()) >= 0);
//        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(orderService.sortByDateDesc().size() - 1).getOrderPostingDate()) >= 0);
//    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setName("komputer Agaty");
        order.setOrderPostingDate(LocalDate.of(2022, 01, 03));
        order.setPayInDollar(345F);
        orderService.saveOrder(order);
        Order addedOrder = orderService.findById(orderService.findAll().size());

        assertThat(addedOrder)
                .isNotNull()
                .isInstanceOf(Order.class)
                .hasNoNullFieldsOrProperties()
                .hasToString("Order{id=7, name='komputer Agaty', orderPostingDate=2022-01-03, payInDollar=345.0, payInPLN=1394.63}");
    }


//    @Test
//    public void testSaveOrder() {
//        Order order = new Order();
//        order.setName("komputer Agaty");
//        order.setOrderPostingDate(LocalDate.of(2022, 01, 03));
//        order.setPayInDollar(345F);
//        Order savedOrder = orderService.saveOrder(order);
//        orderService.findById(orderService.findAll().size()).getPayInPLN();
//        System.out.println(savedOrder.getPayInPLN());
//        System.out.println(orderService.findById(orderService.findAll().size()).getPayInPLN());
//
//        assertAll("SaveOrder",
//                () -> assertEquals(savedOrder.getName(), orderService.findById(orderService.findAll().size()).getName()),
//                () -> assertEquals(savedOrder.getOrderPostingDate(), orderService.findById(orderService.findAll().size()).getOrderPostingDate()),
//                () -> assertEquals(savedOrder.getPayInDollar(), orderService.findById(orderService.findAll().size()).getPayInDollar())
////              TODO: czemu zaokrągla do 4 miejsc
////                () -> assertEquals(savedOrder.getPayInPLN().floatValue(), orderService.findById(orderService.findAll().size()).getPayInPLN())
//        );
//    }
}
