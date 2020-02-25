package lu.smarthome.usermanager.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class KeycloackAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        KeycloakResponse response = null;
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(null, KeycloakResponse.class);
        // TODO: add logic to call keycloak and get the jwt token into a keyclokresponse instance
        if (response == null) {
            //todo: add dedicated exception with @org.springframework.web.bind.annotation.ResponseStatus
            throw new RuntimeException("exit M.F. use some response type");
        }

        return new KeycloakAuthentication(response);//okay --> normal DI :)
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
