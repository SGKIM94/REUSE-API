package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.User;
import reuse.dto.user.*;
import reuse.repository.UserRepository;
import reuse.security.TokenAuthenticationService;

@Service
public class UserService {
    private UserRepository userRepository;
    private TokenAuthenticationService tokenAuthenticationService;

    public UserService(TokenAuthenticationService tokenAuthenticationService, UserRepository userRepository) {
        this.tokenAuthenticationService = tokenAuthenticationService;
        this.userRepository = userRepository;
    }

    public CreateUserResponseView singUp(CreateUserRequestView newUser) {
        return CreateUserResponseView.toDto(userRepository.save(CreateUserRequestView.toEntity(newUser)));
    }

    public LoginUserResponseView login(LoginUserRequestView newUser) {
        FindBySocialTokenIdResponseView user = userRepository.findBySocialTokenId(newUser.getSocialTokenId());

        if (isExistUser(user)) {
            User save = userRepository.save(LoginUserRequestView.toEntity(newUser));
            return toDtoWithJWt(save.getSocialTokenId());
        }

        return toDtoWithJWt(user.getSocialTokenId());
    }

    LoginUserResponseView toDtoWithJWt(String socialTokenId) {
        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(socialTokenId);
        return LoginUserResponseView.toDto(jwt, tokenAuthenticationService.getTokenTypeByJwt(jwt));
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    private boolean isExistUser(FindBySocialTokenIdResponseView user) {
        return user.getIdx() == null;
    }

    public FindByEmailResponseView findById(Long id) {
        return FindByEmailResponseView.toDtoEntity(userRepository.findById(id));
    }
}
