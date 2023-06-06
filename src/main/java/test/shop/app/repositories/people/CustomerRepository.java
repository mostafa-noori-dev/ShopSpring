package test.shop.app.repositories.people;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.orders.Invoice;
import test.shop.app.entities.people.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Long> {

    @Query("from Customer where user.id=:userId")
    Customer findByUserId(long userId);
}
