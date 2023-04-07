package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.entity.User;
import kg.tili.kgserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Markitanov Vadim
 * @since 07.04.2023
 */
@CrossOrigin
@RestController
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

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
}
