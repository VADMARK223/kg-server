package kg.tili.kgserver.service;

import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.entity.Role;
import kg.tili.kgserver.entity.User;
import kg.tili.kgserver.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public String saveUser(User newUser) {
        User userFromDB = userRepository.findByUsername(newUser.getUsername());
        if (userFromDB != null) {
            return "Пользователь уже существует.";
        }

        newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        userRepository.save(newUser);

        return null;
    }

    public String registration(UserDto dto) {
        User user = new User();
        user.setUsername(dto.username);
        String password = passwordEncoder.encode(dto.password);
        user.setPassword(password);
        return saveUser(user);
    }
}
