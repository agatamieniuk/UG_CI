package pl.ug.ug_ci.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.ug.ug_ci.model.ConverterDto;
import pl.ug.ug_ci.model.Order;
import pl.ug.ug_ci.model.Orders;
import pl.ug.ug_ci.repository.OrderRepository;
import pl.ug.ug_ci.webclient.converter.ConverterClient;
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

    @Autowired
    ConverterClient converterClient;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    //Optymalizacja wyszukiwania:
    public List<Order> findBy(String keyword) {
        if (keyword != null) {
            return orderRepository.findAllBy(keyword);
        }
        return orderRepository.findAll();
    }

    //Optymalizacja sortowania  - po nazwie rosnąco:
    public List<Order> sortByNameAsc() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    //Optymalizacja sortowania  - po nazwie malejąco:
    public List<Order> sortByNameDesc() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    //Optymalizacja sortowania  - po dacie od najnowszego:
    public List<Order> sortByDateAsc() {
        return orderRepository.findAll(Sort.by(Sort.Direction.ASC, "orderPostingDate"));
    }

    //Optymalizacja sortowania  - po dacie od najstarszego:
    public List<Order> sortByDateDesc() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderPostingDate"));
    }

    //Dodawanie rekordów przez żądanie HTTP:
    public Order saveOrder(Order order) {
        ConverterDto converter = converterClient.getDateforConvertion(order.getOrderPostingDate());
        order.setPayInPLN(converter.getExchangeRate() * order.getPayInDollar());
        return orderRepository.save(order);
    }

    public Order findById(Integer id){
        return orderRepository.getReferenceById(id);
    }

    public List<Order> findByName(String name) {
        return orderRepository.findAll().stream()
                .filter(names -> names.getName().toLowerCase()
                        .contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Order> findByAccountDay(String orderPostingDate) {
        return orderRepository.findAll().stream()
                .filter(d -> d.getOrderPostingDate().toString().contains(orderPostingDate))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNameAlphabetically() {
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(name -> name.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNameReverse() {
        return orderRepository.findAll().stream()
                .sorted((name1, name2) -> name2.getName().compareToIgnoreCase(name1.getName()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByNewestDate() {
        return orderRepository.findAll().stream()
                .sorted((date1, date2) -> date2.getOrderPostingDate().compareTo(date1.getOrderPostingDate()))
                .collect(Collectors.toList());
    }

    public List<Order> sortByLatestDate() {
        return orderRepository.findAll().stream()
                .sorted(Comparator.comparing(Order::getOrderPostingDate))
                .collect(Collectors.toList());
    }

    public static void convertOrderToXML(Orders orders) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Orders.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(orders, new File("orders.xml"));
    }
}
