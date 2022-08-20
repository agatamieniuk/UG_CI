package pl.ug.ug_ci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ug.ug_ci.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
