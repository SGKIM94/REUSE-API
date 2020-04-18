package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.domain.User;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.LoginUserRequestView;
import reuse.security.LoginUser;
import reuse.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserRequestView loginUser) {
        return ResponseEntity.ok().body(userService.login(loginUser));
    }

    @GetMapping("")
    public ResponseEntity detail(@LoginUser User user) {
        return ResponseEntity.ok().body(userService.findById(user.getId()));
    }
}
