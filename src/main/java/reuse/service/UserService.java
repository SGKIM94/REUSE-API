package reuse.service;

import org.springframework.stereotype.Service;
import reuse.domain.User;
import reuse.dto.user.*;
import reuse.exception.ExistUserException;
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
        FindByEmailResponseView user = userRepository.findByEmail(newUser.getEmail());

        if (!isExistUser(user)) {
            throw new ExistUserException();
        }

        return CreateUserResponseView.toDto(userRepository.save(CreateUserRequestView.toEntity(newUser)));
    }

    public LoginUserResponseView login(LoginUserRequestView newUser) {
        FindByEmailResponseView user = userRepository.findByEmail(newUser.getEmail());

        if (isExistUser(user)) {
            throw new NotExistUserException();
        }

        String jwt = tokenAuthenticationService.toJwtByEmail(user.getEmail());
        return LoginUserResponseView.toDto(jwt, tokenAuthenticationService.getTokenTypeByJwt(jwt));
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    private boolean isExistUser(FindByEmailResponseView user) {
        return user.getId() == null;
    }

    public FindByEmailResponseView findById(Long id) {
        return FindByEmailResponseView.toDtoEntity(userRepository.findById(id));
    }
}
