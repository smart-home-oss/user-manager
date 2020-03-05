package lu.smarthome.usermanager.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class KeycloackAuthenticationProvider implements AuthenticationProvider {

    private final KeycloakProperties properties;

    @Qualifier("keycloak")
    private final RestTemplate restTemplate;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        KeycloakResponse response = null;
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;

        //call catre keycloack
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("client_id", properties.getClientId());
        body.add("client_secret", properties.getClientSecret());
        body.add("username", authenticationToken.getName());
        body.add("password", String.valueOf(authenticationToken.getCredentials()));
        body.add("grant_type", properties.getGrantType());


        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity(body, headers);
        ResponseEntity<KeycloakResponse> exchange = restTemplate
                .exchange(
                        properties.getAccessTokenUrl(),
                        HttpMethod.POST,
                        request,
                        KeycloakResponse.class
                );
        // TODO: add logic to call keycloak and get the jwt token into a keyclokresponse instance
        if (exchange.getStatusCode().is2xxSuccessful() && exchange.getBody() != null) {
            return new KeycloakAuthentication(exchange.getBody());
        }

        throw new RuntimeException("exit M.F. use some response type");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
