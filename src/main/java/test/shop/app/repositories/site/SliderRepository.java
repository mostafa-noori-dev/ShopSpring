package test.shop.app.repositories.site;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.site.Nav;
import test.shop.app.entities.site.Slider;

import java.util.List;

@Repository
public interface SliderRepository extends PagingAndSortingRepository<Slider,Long> {
    List<Slider> findAllByEnableIsTrue(Sort sort);
    Slider findTopByOrderByItemOrderDesc();
    Slider findTopByItemOrder(int itemOrder);
}
