package engine.security;

import engine.domain.User;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();
    User currentUserEntity();
}
