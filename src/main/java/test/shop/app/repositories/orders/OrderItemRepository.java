package test.shop.app.repositories.orders;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.orders.OrderItem;

@Repository
public interface OrderItemRepository extends PagingAndSortingRepository<OrderItem,Long> {

}
