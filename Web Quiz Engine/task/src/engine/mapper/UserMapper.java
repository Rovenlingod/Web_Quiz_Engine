package engine.mapper;

import engine.domain.User;
import engine.dto.RegistrationFormDTO;
import engine.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private UserValidationService userValidationService;

    @Autowired
    public UserMapper(UserValidationService userValidationService) {
        this.userValidationService = userValidationService;
    }

    public User toEntity(RegistrationFormDTO form) {
        userValidationService.validateRegistrationForm(form);
        User result = new User();
        result.setEmail(form.getEmail());
        result.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
        return result;
    }
}
