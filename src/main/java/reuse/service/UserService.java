package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.User;
import reuse.dto.user.*;
import reuse.exception.NotExistUserException;
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
            userRepository.save(LoginUserRequestView.toEntity(newUser));
        }

        String jwt = tokenAuthenticationService.toJwtBySocialTokenId(user.getSocialTokenId());
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
