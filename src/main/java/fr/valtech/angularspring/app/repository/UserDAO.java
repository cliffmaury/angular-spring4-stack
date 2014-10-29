package fr.valtech.angularspring.app.repository;

import fr.valtech.angularspring.app.domain.User;
import fr.valtech.angularspring.log.Log;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by cliff.maury on 28/10/2014.
 */
@Repository
public class UserDAO {

    @Log
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        return (List<User>) em.createQuery("FROM User").getResultList();
    }

    public Long create(User transientUser) {
        logger.warn("create user");
        em.persist(transientUser);
        return transientUser.getId();
    }

}
