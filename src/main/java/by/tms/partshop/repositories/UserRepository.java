package by.tms.partshop.repositories;

import by.tms.partshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsUserByLoginAndPassword(String login, String password);
}
