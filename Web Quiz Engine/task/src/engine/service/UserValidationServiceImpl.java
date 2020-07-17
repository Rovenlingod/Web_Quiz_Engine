package engine.service;

import engine.dto.RegistrationFormDTO;
import engine.exceptions.UserNotValidException;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidationServiceImpl implements UserValidationService {

    private UserRepository userRepository;

    @Autowired
    public UserValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validateRegistrationForm(RegistrationFormDTO form) {
        if (form.getPassword().length() < 5) throw new UserNotValidException("The length of the password should be at least 5 characters");
        if (userRepository.findByEmail(form.getEmail()) != null) throw new UserNotValidException("User with email = " + form.getEmail() + " already exists");
        if (!form.getEmail().matches("[^@]+@[^\\.]+\\..+")) throw new UserNotValidException("Email is not valid (no @ or .)");
        return true;
    }
}
