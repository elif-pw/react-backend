package pw.react.backend.reactbackend;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

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


//    public User createOrUpdateStudent(User entity) throws ResourceNotFoundException {
//
//        if (entity.getLogin() != null) {
//            Optional<User> user = repository.findById(entity.getId());
//
//            if (user.isPresent()) {
//                User newEntity = user.get();
//                //newEntity.setId(entity.getId());
//                newEntity.setLogin(entity.getLogin());
//                newEntity.setName(entity.getName());
//                newEntity.setLast_name(entity.getLast_name());
//                newEntity.setDob(entity.getDob());
//                newEntity.setIs_active(entity.getIs_active());
//
//                newEntity = repository.save(newEntity);
//
//                return newEntity;
//            } else {
//                entity = repository.save(entity);
//
//                return entity;
//            }
//        } else {
//            entity = repository.save(entity);
//            return entity;
//        }
//    }
}