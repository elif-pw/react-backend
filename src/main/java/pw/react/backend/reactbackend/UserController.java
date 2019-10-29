package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }

    @GetMapping("/findbylogin/{login}")
    public String retrieveByLogin(@PathVariable String login) {
        User user = repository.findByLogin(login);
        if (user == null)
            throw  new ResourceNotFoundException("User","login", login);
        return user.toString();
    }

    @Autowired
    UserService service;

    @PostMapping(value = "/createUser")
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


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable long id) {

        Optional<User> studentOptional = repository.findById(id);

        if (!studentOptional.isPresent())
            throw new ResourceNotFoundException("User", "id", id);

        user.setId(id);
        repository.save(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id) {
        if(repository.findById(id).isPresent())
            repository.deleteById(id);
        else
            throw new ResourceNotFoundException("User", "id", id);
    }

    @GetMapping("/getbyid/{id}")
    public User retrieveById(@PathVariable long id) {
        Optional<User> student = repository.findById(id);

        if (!student.isPresent())
            throw new ResourceNotFoundException("User","id", id);

        return student.get();
    }

}