package pl.ug.ug_ci.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.model.Orders;
import pl.ug.ug_ci.repository.OrderRepository;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByName(String name) {
//        Wyszukiwanie pozostawiłam na streamach, gdyz z repozytorium wyszukiwało po pełnej nazwie, a w wymaganiach jest fragment. W przypadku użycia repozytorium:
//        public List<Order> findByName(String name) {
//        return orderRepository.findByName(name);}
        return orderRepository.findAll().stream()
                .filter(names -> names.getName().toLowerCase()
                        .contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Order> findByAccountDay(String orderPostingDate) {
        return orderRepository.findAll().stream()
                .filter(d -> d.getOrderPostingDate().toString().contains(orderPostingDate))
                .collect(Collectors.toList());
//        public List<Order> findByAccountDay(LocalDate orderPostingDate) {
//        return orderRepository.findByOrderPostingDate(orderPostingDate);
    }

    public List<Order> sortByNameAlphabetically() {
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(name -> name.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNameReverse() {
        return orderRepository.findAll().stream()
                .sorted((name1, name2) ->name2.getName().compareToIgnoreCase(name1.getName()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNewestDate(){
        return orderRepository.findAll().stream()
                .sorted((date1,date2)->date2.getOrderPostingDate().compareTo(date1.getOrderPostingDate()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByLatestDate(){
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(Order::getOrderPostingDate))
                .collect(Collectors.toList());
    }
//TODO nowy package = servis
    public static void convertOrderToXML(Orders orders) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Orders.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(orders, new File("orders.xml"));
    }
}
