package pl.ug.ug_ci.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ug.ug_ci.model.Order;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testFindAll() {
        Order order = new Order(1, "komputer 1", LocalDate.of(2022, 01, 03), 345F, 1394.6279F);
        Order order2 = new Order(2, "C komputer 1", LocalDate.of(2022, 01, 10), 345F, 1382.208F);

        assertAll("Find All",
                () -> assertNotNull(orderService.findAll()),
                () -> assertTrue(orderService.findAll().contains(order)),
                () -> assertEquals(order2, orderService.findAll().get(1)),
                () -> assertEquals(6, orderService.findAll().size())
        );
    }

    @Test
    @DisplayName("Powinno zwrócić fałsz, gdy nazwa komputera nie brzmi 'komputer 1'")
    public void testFindBy() {
        List<Order> orderList = orderService.findBy("komputer 1");
        assertThat(orderList)
                .isNotNull()
                .doesNotContainNull()
                .hasSize(2)
                .containsOnly(orderService.findAll().get(0), orderService.findAll().get(1));
    }

//    Wersja druga:
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
//                () -> assertNotNull(orderService.findBy("komputer 1")),
//                () -> assertEquals(orderList.size(), orderService.findBy("komputer 1").size()),
//                () -> assertEquals(orderList, orderService.findBy("komputer 1"))
//        );
//    }

    @Test
    @DisplayName("Powinno zwrócić prawdę, gdy keyword ma wartość nulla")
    public void testIfFindByIsNull() {
        List<Order> orderList = orderService.findBy(null);
        assertThat(orderList)
                .isNotNull()
                .isInstanceOf(ArrayList.class)
                .hasSize(6)
                .doesNotContainNull();
    }

    @Test
    public void testSortByNameAsc() {
        List<Order> orderList = orderService.sortByNameAsc();
        assertThat(orderList)
                .isNotNull()
                .isInstanceOf(ArrayList.class)
                .doesNotContainNull()
                .isSortedAccordingTo(Comparator.comparing(name -> name.getName().toLowerCase()))
                .size().isEqualTo(orderService.findAll().size());
    }

    @Test
    public void testSortByNameDesc() {
        List<Order> orderList = orderService.sortByNameDesc();
        assertThat(orderList)
                .isNotNull()
                .isInstanceOf(ArrayList.class)
                .doesNotContainNull()
                .isSortedAccordingTo((name1, name2) -> name2.getName().compareToIgnoreCase(name1.getName()))
                .size().isEqualTo(orderService.findAll().size());
    }

    @Test
    public void testSortByDateAsc() {
        List<Order> orderList = orderService.sortByDateAsc();
        assertThat(orderList)
                .isNotNull()
                .isInstanceOf(ArrayList.class)
                .doesNotContainNull()
                .isSortedAccordingTo(Comparator.comparing(Order::getOrderPostingDate))
                .size().isEqualTo(orderService.findAll().size());
    }

    @Test
    public void testSortByDateDesc() {
        List<Order> orderList = orderService.sortByDateDesc();
        assertThat(orderList)
                .isNotNull()
                .isInstanceOf(ArrayList.class)
                .doesNotContainNull()
                .isSortedAccordingTo((date1, date2) -> date2.getOrderPostingDate().compareTo(date1.getOrderPostingDate()))
                .size().isEqualTo(orderService.findAll().size());
    }

//    Wersja druga:
//    Poniżej korzystam z informacji, iż Comparatory zwracaja liczbę (ujemna/dodatnia/zero) ujemna – obiekt mniejszy, dodatnia – obiekt wiekszy (uwzględniając, iż zero oznacza obiekt rowny).
//    @Test
//    public void testSortByNameAsc() {
//        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(1).getName()) <= 0);
//        assertTrue(orderService.sortByNameAsc().get(1).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
//        assertTrue(orderService.sortByNameAsc().get(0).getName().compareTo(orderService.sortByNameAsc().get(2).getName()) <= 0);
//    }
//
//    @Test
//    public void testSortByNameDesc() {
//        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(1).getName()) >= 0);
//        assertTrue(orderService.sortByNameDesc().get(1).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
//        assertTrue(orderService.sortByNameDesc().get(0).getName().compareTo(orderService.sortByNameDesc().get(2).getName()) >= 0);
//    }
//
//    @Test
//    public void testSortByDateAsc() {
//        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(1).getOrderPostingDate()) <= 0);
//        assertTrue(orderService.sortByDateAsc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(2).getOrderPostingDate()) <= 0);
//        assertTrue(orderService.sortByDateAsc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateAsc().get(orderService.sortByDateAsc().size() - 1).getOrderPostingDate()) <= 0);
//    }
//
//    @Test
//    public void testSortByDateDesc() {
//        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(1).getOrderPostingDate()) >= 0);
//        assertTrue(orderService.sortByDateDesc().get(1).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(2).getOrderPostingDate()) >= 0);
//        assertTrue(orderService.sortByDateDesc().get(0).getOrderPostingDate().compareTo(orderService.sortByDateDesc().get(orderService.sortByDateDesc().size() - 1).getOrderPostingDate()) >= 0);
//    }

    @Test
    @DisplayName("Powinno zwrócić fałsz, gdy zapisany obiekt nie jest równoważny ze wskazanym")
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
                .hasToString("Order{id=7, name='komputer Agaty', orderPostingDate=2022-01-03, payInDollar=345.0, payInPLN=1394.6279}");
    }
}
