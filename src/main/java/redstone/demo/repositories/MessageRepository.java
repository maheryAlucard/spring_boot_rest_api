package redstone.demo.repositories;

// Importing required classes 
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import redstone.demo.models.Messages;

@Repository
public interface MessageRepository extends CrudRepository<Messages, Long> {

}
