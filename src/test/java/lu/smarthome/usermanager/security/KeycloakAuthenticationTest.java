package lu.smarthome.usermanager.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeycloakAuthenticationTest {

    KeycloakAuthentication subject;
    KeycloakResponse response;

    @BeforeEach
    void setUp() {
        response = new KeycloakResponse();
        response.setAccess_token("Access token");
        subject = new KeycloakAuthentication(response);
    }

    @Test
    void getAuthorities() {
        assertEquals(1, subject.getAuthorities().size());
        assertEquals("USER", subject.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void getCredentials() {
        assertEquals(response.getAccess_token(), subject.getCredentials());
    }

    @Test
    void getDetails() {
        assertEquals(response, subject.getDetails());
    }

    @Test
    void isAuthenticated() {
        assertTrue(subject.isAuthenticated());
    }

    @Test
    void isAuthenticatedFailed() {
        response.setAccess_token(null);
        subject = new KeycloakAuthentication(response);
        assertFalse(subject.isAuthenticated());
    }

    @Test
    void getName() {
        assertEquals(response.getAccess_token(), subject.getName());
    }
}