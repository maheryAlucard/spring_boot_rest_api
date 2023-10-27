package redstone.demo.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import redstone.demo.models.Reservation;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
