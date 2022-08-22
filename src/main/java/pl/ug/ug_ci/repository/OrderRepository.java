package pl.ug.ug_ci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ug.ug_ci.model.Order;
import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByOrderPostingDate(LocalDate orderPostingDate);

}
