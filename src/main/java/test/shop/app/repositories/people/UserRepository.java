package test.shop.app.repositories.people;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import test.shop.app.entities.people.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findFirstByUsernameAndPassword(String username,String password);
    User findFirstByUsername(String username);

    long countByEnableIsTrue();
}
