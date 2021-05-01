package reuse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reuse.domain.User;
import reuse.dto.user.*;
import reuse.repository.UserRepository;
import reuse.security.TokenAuthenticationService;

@Slf4j
@Service
public class UserService {
    private UserRepository userRepository;
    private TokenAuthenticationService tokenAuthenticationService;

    public UserService(TokenAuthenticationService tokenAuthenticationService, UserRepository userRepository) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userRepository = userRepository;
    }

    @Transactional
    public CreateUserResponseView singUp(CreateUserRequestView newUser) {
        User save = userRepository.save(CreateUserRequestView.toEntity(newUser));
        return CreateUserResponseView.toDto(save);
    }

    @Transactional
    public LoginUserResponseView login(LoginUserRequestView newUser) {
        User user = userRepository.findBySocialTokenId(newUser.getSocialTokenId());

        if (isExistUser(user)) {
            log.error("User 가 존재하지 않으므로 새로운 User 추가");
            User save = userRepository.save(LoginUserRequestView.toEntity(newUser, User.getRandomUserName()));
            return toDtoWithJWt(save);
        }

        return toDtoWithJWt(user);
    }

    LoginUserResponseView toDtoWithJWt(User user) {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(user.getSocialTokenId());
        return LoginUserResponseView.toDto(jwt, user);
    }

    private boolean isExistUser(User user) {
        return user == null;
    }

    @Transactional(readOnly = true)
    public User retrieve(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));
    }

    public FindByIdResponseView findById(Long id) {
        return FindByIdResponseView.toDto(retrieve(id));
    }
}
