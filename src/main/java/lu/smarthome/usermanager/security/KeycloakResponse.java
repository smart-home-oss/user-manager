package lu.smarthome.usermanager.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
