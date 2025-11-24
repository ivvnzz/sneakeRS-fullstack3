package group.sneakers.sneakers.repository.uda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group.sneakers.sneakers.model.uda.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
