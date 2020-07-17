package engine.service;

import engine.dto.RegistrationFormDTO;

public interface UserValidationService {
    boolean validateRegistrationForm(RegistrationFormDTO form);
}
