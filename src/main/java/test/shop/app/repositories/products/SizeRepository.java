package test.shop.app.repositories.products;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.products.Feature;
import test.shop.app.entities.products.Size;

import java.util.List;


@Repository
public interface SizeRepository extends PagingAndSortingRepository<Size,Long> {
    @Override
    List<Size> findAll();
}
