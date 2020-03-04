package lu.smarthome.usermanager.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties("app.keycloak")
public class KeycloakProperties {
    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private String tokenName;
    private String scope;
    private String userInfoUri;
    private String grantType;
}
