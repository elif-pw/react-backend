package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping({"/users"})
public class UserController {
    @Autowired
    UserRepository repository;

    UserController(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/login/{login}")
    public String retrieveByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if (user == null)
            throw  new ResourceNotFoundException("User","login", login);
        return user.toString();
    }

    @Autowired
    UserService service;

    @PostMapping(value = "/create")
    public String create(@RequestBody User newUser) {
        if (service.lookforLogin(newUser.getLogin()) == null) {
            repository.save(new User(newUser.getLogin(),
                    newUser.getName(),
                    newUser.getLast_name(),
                    newUser.getDob(),
                    newUser.getIs_active()));
            return "OK";
        }
        return "Error";
    }


//    @PutMapping("/update/{id}")
//    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {
//
//        Optional<User> studentOptional = repository.findById(id);
//
//        if (!studentOptional.isPresent())
//            throw new ResourceNotFoundException("User", "id", id);
//
//        user.setId(id);
//        repository.save(user);
//        return ResponseEntity.noContent().build();
//    }

    @PutMapping(path = "")
    public ResponseEntity<User> updateWholeUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(repository.save(updatedUser));
    }

    @PatchMapping(path = "")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User updatedUser) {
        return ResponseEntity.ok().body(service.updateUser(updatedUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("User", "id", id);
    }

    @GetMapping("/id/{id}")
    public User retrieveById(@PathVariable long id) {
        Optional<User> student = repository.findById(id);

        if (!student.isPresent())
            throw new ResourceNotFoundException("User","id", id);

        return student.get();
    }

}