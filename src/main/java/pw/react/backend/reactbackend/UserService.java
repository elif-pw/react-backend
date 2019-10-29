package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class UserService {
    @Autowired
    UserRepository repository;

    public User lookforLogin(String login) {
        ArrayList<User> users = (ArrayList<User>) repository.findAll();
        for (User user : users) {
            if (user.getLogin() != null && user.getLogin().equals(login))
                return user;
        }
        return null;
    }
}