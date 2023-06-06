package test.shop.app.repositories.products;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.products.Color;
import test.shop.app.entities.products.Feature;

@Repository
public interface FeatureRepository extends PagingAndSortingRepository<Feature,Long> {

}
