package kg.tili.kgserver.web;

import kg.tili.kgserver.dto.ResponseDto;
import kg.tili.kgserver.dto.UserDto;
import kg.tili.kgserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    private final UserService userService;

    @RequestMapping(value = "/get_user_info", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getUserInfo() {
        System.out.println("GEt USER INFO");
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/register_user", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<String>> registerUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(ResponseDto.<String>success().data(userService.registration(userDto)).build());
    }

    @RequestMapping(value = "/login_user", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto<String>> loginUser(@RequestBody UserDto userDto) {
        String jwt;
        try {
            jwt = userService.loginUser(userDto);
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDto.<String>failure(e.getMessage()).build());
        }

        return ResponseEntity.ok(ResponseDto.<String>success().data(jwt).build());
    }

    @RequestMapping(value = "/test_service", method = RequestMethod.POST)
    public ResponseEntity<Boolean> testService() {
        System.out.println("TEST_SERVICE");
        return ResponseEntity.ok(true);
    }
}
