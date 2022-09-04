package pl.ug.ug_ci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.ug.ug_ci.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    //Optymalizacja wyszukiwania:
    @Query("SELECT o FROM Order o WHERE " +
            "concat(o.id, o.name, o.orderPostingDate)" +
            "LIKE %?1%")
    List<Order> findAllBy(String keyword);

}
