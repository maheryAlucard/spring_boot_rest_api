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
import redstone.demo.models.Reclamation;
import redstone.demo.repositories.ReclamationRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reclamation")
public class ReclamationController {
    @Autowired
    private ReclamationRepository reclamationRepository;

    @GetMapping
    public Iterable<Reclamation> findAllReclamationss() {
        return reclamationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reclamation> findReclamationsById(@PathVariable(value = "id") long id) {
        Optional<Reclamation> reclamation = reclamationRepository.findById(id);

        if (reclamation.isPresent()) {
            return ResponseEntity.ok().body(reclamation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Reclamation saveReclamations(@RequestBody Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteReclamationById(@PathVariable(value = "id") long id) {
        reclamationRepository.deleteById(id);
        return true;
    }

    @DeleteMapping
    public Boolean deleteReclamationByIds(@RequestBody Iterable<Long> ids) {
        reclamationRepository.deleteAllById(ids);
        return true;
    }
}
