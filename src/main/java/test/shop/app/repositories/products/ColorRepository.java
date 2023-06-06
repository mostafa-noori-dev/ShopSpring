package test.shop.app.repositories.products;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.products.Color;

import java.util.List;


@Repository
public interface ColorRepository extends PagingAndSortingRepository<Color,Long> {
    @Override
    List<Color> findAll();
}
