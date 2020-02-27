package lu.smarthome.usermanager.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class KeycloakAuthentication implements Authentication {

    private final KeycloakResponse keycloakResponse;
    private boolean isAuthenticated;

    public KeycloakAuthentication(KeycloakResponse keycloakResponse) {
        this.keycloakResponse = keycloakResponse;
        isAuthenticated = keycloakResponse.getAccess_token() != null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "USER");
    }

    @Override
    public Object getCredentials() {
        return keycloakResponse.getAccess_token();
    }

    @Override
    public Object getDetails() {
        return this.keycloakResponse;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return keycloakResponse.getAccess_token();
    }
}
