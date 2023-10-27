package redstone.demo.repositories;

import org.springframework.stereotype.Repository;

import redstone.demo.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE username=?", nativeQuery = true)
    public User findByUserName(String username);

    @Query(value = "SELECT * FROM user WHERE email=?", nativeQuery = true)
    public User findByEmail(String email);
}
