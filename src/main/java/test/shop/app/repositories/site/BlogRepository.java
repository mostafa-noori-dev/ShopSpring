package test.shop.app.repositories.site;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.site.Blog;
import test.shop.app.enums.BlogStatus;

import java.util.Date;
import java.util.List;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    @Query("from Blog where title like concat('%',:search,'%') or description like concat('%',:search,'%')  ")
    List<Blog> findAllByTitleContainsOrDescriptionContains(String search);

    Page<Blog> findAllByStatusAndPublishDateLessThanEqual(BlogStatus status, Date publishDate, Pageable pageable);
    long countByStatusAndPublishDateLessThanEqual(BlogStatus status, Date publishDate);
}
