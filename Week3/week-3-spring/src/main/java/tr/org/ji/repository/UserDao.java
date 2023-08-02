package tr.org.ji.repository;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import tr.org.ji.Entity.User;

import java.util.List;

@Repository
public class UserDao {
    private final EntityManager entityManager;

    public UserDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public List<User> getUsers(int pageNumber, int pageSize) {
        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var from = criteriaQuery.from(User.class);
        var select = criteriaQuery.select(from);
        criteriaQuery.select(from).where(criteriaBuilder.isTrue(from.get("active")));
        var query = entityManager.createQuery(select);
        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);
        return  query.getResultList();

    }

}
