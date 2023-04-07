package kg.tili.kgserver.service;

import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.entity.Role;
import kg.tili.kgserver.entity.User;
import kg.tili.kgserver.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    public UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean saveUser(User newUser) {
        User userFromDB = userRepository.findByUsername(newUser.getUsername());
        if (userFromDB != null) {
            return false;
        }

        newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        userRepository.save(newUser);

        return true;
    }
}
