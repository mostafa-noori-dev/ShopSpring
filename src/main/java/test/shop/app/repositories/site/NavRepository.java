package test.shop.app.repositories.site;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.site.Nav;

import java.util.List;


@Repository
public interface NavRepository extends PagingAndSortingRepository<Nav, Long> {
    List<Nav> findAllByEnableIsTrue(Sort sort);
    Nav findTopByOrderByItemOrderDesc();
    Nav findTopByItemOrder(int itemOrder);
}
