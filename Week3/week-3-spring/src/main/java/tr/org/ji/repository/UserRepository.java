package tr.org.ji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tr.org.ji.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUsernameStartsWithAndActiveIsTrueOrderByCreatedDateDesc(String username);

    Optional<User> findByUsername(String username);

    @Query(value = "select u from User as u where u.id = :id")
    Optional<User> getByIdHql(Long id);

    @Query(value = "select * from USERS where id = :id", nativeQuery = true)
    Optional<User> getByIdSql(Long id);

}
