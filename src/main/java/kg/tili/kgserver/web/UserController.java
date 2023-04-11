package kg.tili.kgserver.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.entity.User;
import kg.tili.kgserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    public AuthenticationConfiguration authenticationConfiguration;

    @RequestMapping(value = "/get_user_info", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getUserInfo() {
        System.out.println("GEt USER INFO");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.POST)
    public ResponseEntity<Boolean> registerUser(@RequestBody UserDto userDto) {
        System.out.println("REGISTER USER: " + userDto);
        User user = new User();
        user.setUsername(userDto.username);
        user.setPassword(userDto.password);
        boolean resultSaveUser = userService.saveUser(user);
        System.out.println("resultSaveUser: " + resultSaveUser);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/login_user", method = RequestMethod.POST)
    public ResponseEntity<Boolean> loginUser(@RequestBody UserDto userDto) throws Exception {
        System.out.println("LOGIN USER: " + userDto);
        SecurityContextHolder.getContext().setAuthentication(authenticationConfiguration.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(userDto.username, userDto.password)));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("user: " + user);
        Algorithm algorithm = Algorithm.HMAC256("SECRET");
        String jwt = JWT.create().withIssuer(user.getUsername()).sign(algorithm);
        System.out.println("jwt: " + jwt);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(true);
    }

    @RequestMapping(value = "/test_service", method = RequestMethod.POST)
    public ResponseEntity<Boolean> testService() {
        System.out.println("TEST_SERVICE");
        return ResponseEntity.ok(true);
    }
}
