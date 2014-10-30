package fr.valtech.angularspring.app.repository;

import fr.valtech.angularspring.app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cliff.maury on 29/10/2014.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
