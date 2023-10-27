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
import redstone.demo.models.Messages;
import redstone.demo.repositories.MessageRepository;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public Iterable<Messages> findAllMessagess() {
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Messages> findMessagesById(@PathVariable(value = "id") long id) {
        Optional<Messages> message = messageRepository.findById(id);

        if (message.isPresent()) {
            return ResponseEntity.ok().body(message.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Messages saveMessages(@RequestBody Messages message) {
        return messageRepository.save(message);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMessageById(@PathVariable(value = "id") long id) {
        messageRepository.deleteById(id);
        return true;
    }

    @DeleteMapping
    public Boolean deleteMessageByIds(@RequestBody Iterable<Long> ids) {
        messageRepository.deleteAllById(ids);
        return true;
    }
}
