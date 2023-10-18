package kg.tili.kgserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kg.tili.kgserver.config.JwtUtils;
import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.entity.Role;
import kg.tili.kgserver.entity.User;
import kg.tili.kgserver.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден.", username));
        }

        return user;
    }

    public String saveUser(User newUser) {
        User userFromDB = userRepo.findByUsername(newUser.getUsername());
        if (userFromDB != null) {
            return "Пользователь уже существует.";
        }

        newUser.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));

        userRepo.save(newUser);

        return null;
    }

    public String registration(UserDto dto) {
        User user = new User();
        user.setUsername(dto.username);
        String password = passwordEncoder.encode(dto.password);
        user.setPassword(password);
        return saveUser(user);
    }

    public void forceAuthenticationUser(String username) {
        UserDetails userDetails = this.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public String loginUser(UserDto userDto) throws Exception {
        User userDB = userRepo.findByUsername(userDto.username);
        if (Objects.isNull(userDB)) {
            throw new Exception("Пользователь не найден.");
        }
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationConfiguration.getAuthenticationManager().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        return jwtUtils.generateToken(userDto.getUsername());
    }
}
