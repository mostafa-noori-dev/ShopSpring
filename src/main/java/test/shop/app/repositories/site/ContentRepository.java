package test.shop.app.repositories.site;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.site.Content;

import java.util.List;

@Repository
public interface ContentRepository extends PagingAndSortingRepository<Content,Long> {
    Content findFirstByKey(String key);
    @Override
    List<Content> findAll();
}
