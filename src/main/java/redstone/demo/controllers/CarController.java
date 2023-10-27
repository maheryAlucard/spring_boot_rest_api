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
import redstone.demo.models.Car;
import redstone.demo.repositories.CarRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public Iterable<Car> findAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable(value = "id") long id) {
        Optional<Car> car = carRepository.findById(id);

        if (car.isPresent()) {
            return ResponseEntity.ok().body(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Car saveCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteCarById(@PathVariable(value = "id") long id) {
        carRepository.deleteById(id);
        return true;
    }

    @DeleteMapping
    public Boolean deleteCarByIds(@RequestBody Iterable<Long> ids) {
        carRepository.deleteAllById(ids);
        return true;
    }

}