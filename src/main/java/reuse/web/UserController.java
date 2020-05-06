package reuse.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reuse.dto.user.CreateUserRequestView;
import reuse.dto.user.CreateUserResponseView;
import reuse.dto.user.LoginUserRequestView;
import reuse.service.UserService;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity signUp(@RequestBody CreateUserRequestView user) {
        CreateUserResponseView savedUser = userService.singUp(user);
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserRequestView loginUser) {
        return ResponseEntity.ok().body(userService.login(loginUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }
}
