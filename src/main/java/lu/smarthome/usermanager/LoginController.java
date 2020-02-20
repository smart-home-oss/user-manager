package lu.smarthome.usermanager;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    @PostMapping("${app.api.version.default}/token")
    public Object getMe(Principal p) {
        if(p instanceof OAuth2AuthenticationToken) {
            return ((DefaultOidcUser) ((OAuth2AuthenticationToken) p).getPrincipal()).getIdToken().getTokenValue();
        }
        return p;
    }

}
