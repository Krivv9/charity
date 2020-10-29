package pl.coderslab.charity.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.models.dtos.UserToAddDTO;
import pl.coderslab.charity.models.entities.Role;
import pl.coderslab.charity.models.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(UserToAddDTO userToAddDTO) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userToAddDTO.getPassword()));
        user.setActive(true);
        user.setEmail(userToAddDTO.getEmail());
        user.setName(userToAddDTO.getName());
        if ("admin".equals(user.getName())) {
            user.setRole(Role.ROLE_ADMIN.toString());
        } else {
            user.setRole(Role.ROLE_USER.toString());
        }
        log.info("Attempt to save user: " + user);
        userRepository.save(user);
    }
}