package lu.smarthome.usermanager.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@Getter
@Setter
public class KeycloakProperties {
    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String tokenName;
    private String scope;
    private String userInfoUri;
    private String grantType;
}
