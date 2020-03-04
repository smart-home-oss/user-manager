package lu.smarthome.usermanager.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeycloakResponse {

    private String access_token;
    private Long expires_in;
    private Long refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private Long not_before_policy;
    private String session_state;
    private String scope;
}
