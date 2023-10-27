package redstone.demo.repositories;

// Importing required classes 
import org.springframework.stereotype.Repository;
import redstone.demo.models.Car;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

}
