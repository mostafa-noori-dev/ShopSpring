package test.shop.app.repositories.orders;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.orders.Transactions;


@Repository
public interface TransactionsRepository extends PagingAndSortingRepository<Transactions,Long> {
    Transactions findByAuthority(String authority);
}
