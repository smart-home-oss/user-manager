package lu.smarthome.usermanager.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KeycloackAuthenticationProviderTest {

    KeycloakProperties properties;
    RestTemplate restTemplate;
    Authentication authentication;
    KeycloackAuthenticationProvider subject;

    @BeforeEach
    void setUp() {
        properties = new KeycloakProperties();
        properties.setAccessTokenUrl("http://localhost.tld");
        restTemplate = mock(RestTemplate.class);
        authentication = mock(UsernamePasswordAuthenticationToken.class);
        subject = new KeycloackAuthenticationProvider(properties, restTemplate);
    }

    @Test
    void authenticate() {
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<KeycloakResponse> responseEntity = mock(ResponseEntity.class);

        when(responseEntity.getStatusCode())
                .thenReturn(httpStatus);

        when(responseEntity.getBody())
                .thenReturn(mock(KeycloakResponse.class));

        when(restTemplate.exchange(
                eq(properties.getAccessTokenUrl()),
                eq(HttpMethod.POST),
                any(),
                eq(KeycloakResponse.class)
        )).thenReturn(
                responseEntity
        );

        Authentication result = subject.authenticate(authentication);
        assertNotNull(result);
        assertTrue(result instanceof KeycloakAuthentication);
    }

    @Test
    void supports() {
        assertFalse(subject.supports(KeycloakAuthentication.class));
        assertTrue(subject.supports(UsernamePasswordAuthenticationToken.class));
    }
}