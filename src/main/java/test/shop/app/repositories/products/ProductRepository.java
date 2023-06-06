package test.shop.app.repositories.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.products.Product;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query("from Product where category.id=:categoryId")
    List<Product> findAllProductByCategoryId(long categoryId);

    @Query("select count(id) from Product where category.id=:ProductCategoryId")
    long countByCategoryId(long ProductCategoryId);

    @Query(value = "from Product where category.id=:ProductCategoryId",countQuery = "select count(id) from Product where category.id=:ProductCategoryId ")
    Page<Product> findAllProductsByProductCategoryIdWithPageble(long ProductCategoryId, Pageable pageable);
    @Query("from Product where enable=true and (title like concat('%',:search,'%') or description like concat('%',:search,'%'))")
    List<Product> findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(String search);

    List<Product> findTop6ByOrderByAddDateDesc();
    List<Product> findTop6ByOrderByVisitCountDesc();

    long countByExistsIsTrue();
    long countByEnableIsTrue();

}
