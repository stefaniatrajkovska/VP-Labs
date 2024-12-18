package mk.finki.ukim.mk.lab1.service.implementation;

import mk.finki.ukim.mk.lab1.model.User;
import mk.finki.ukim.mk.lab1.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab1.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab1.model.exceptions.PasswordDoNotMatchException;
import mk.finki.ukim.mk.lab1.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab1.repository.jpa.AuthenticationRepository;
import mk.finki.ukim.mk.lab1.service.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationServiceImpl(AuthenticationRepository authenticationRepository) {
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        return authenticationRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        if (!password.equals(repeatPassword)) throw new PasswordDoNotMatchException();

        if (this.authenticationRepository.findByUsername(username).isPresent() || !this.authenticationRepository.findByUsername(username).isEmpty())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username,password,name,surname);
        return authenticationRepository.save(user);
    }
}
