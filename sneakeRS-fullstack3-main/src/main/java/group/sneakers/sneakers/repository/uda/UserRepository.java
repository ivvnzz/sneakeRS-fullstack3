package group.sneakers.sneakers.repository.uda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.uda.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
