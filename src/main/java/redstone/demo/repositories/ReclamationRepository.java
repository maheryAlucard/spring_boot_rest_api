package redstone.demo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import redstone.demo.models.Reclamation;

@Repository
public interface ReclamationRepository extends CrudRepository<Reclamation, Long> {

}
