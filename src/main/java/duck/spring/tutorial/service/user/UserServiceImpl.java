//package duck.spring.tutorial.service.user;
//
//import duck.spring.tutorial.model.User;
//import duck.spring.tutorial.repository.UserRepository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
////    private final BCryptPasswordEncoder passwordEncoder;
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public Optional<User> getUserById(UUID id) {
//        return userRepository.findById(id);
//    }
//
//    @Override
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public User updateUser(User user) {
//        return userRepository.save(user);
//    }
//
//    @Override
//    public void deleteUserById(UUID id) {
//        userRepository.deleteById(id);
//    }
//}
