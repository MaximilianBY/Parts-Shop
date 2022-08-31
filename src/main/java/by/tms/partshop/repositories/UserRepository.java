package by.tms.partshop.repositories;

import by.tms.partshop.dto.UserDataDto;
import by.tms.partshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsUserByLoginAndPassword(String login, String password);

  @Query(value = "SELECT ID FROM PARTS_SHOP.USERS WHERE LOGIN > :login",
      nativeQuery = true)
  long getUserId(String login);

  UserDataDto getUserById(long id);
}
