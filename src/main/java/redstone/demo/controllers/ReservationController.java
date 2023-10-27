package redstone.demo.controllers;

// Importing required classes 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.lang.Iterable;

//Local import
import redstone.demo.models.Reservation;
import redstone.demo.repositories.ReservationRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public Iterable<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findReservationById(@PathVariable(value = "id") long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isPresent()) {
            return ResponseEntity.ok().body(reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Reservation saveReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReservationById(@PathVariable(value = "id") long id) {
        reservationRepository.deleteById(id);
        return true;
    }

    @DeleteMapping
    public Boolean deleteReservationByIds(@RequestBody Iterable<Long> ids) {
        reservationRepository.deleteAllById(ids);
        return true;
    }
}
