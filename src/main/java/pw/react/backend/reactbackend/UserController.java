package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
            //throw  new ResourceNotFoundException("User","login", login);
            return "No user found";
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


}