package lu.smarthome.usermanager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@CrossOrigin("${app.api.cors}")
public class RootController {

    @Value("${app.api.version.default}")
    private String defaultApiSpecUri;

    @GetMapping("/me")
    public RedirectView redirectApiToSwagger() {
        return new RedirectView(defaultApiSpecUri + "/me");
    }

    @GetMapping("${app.api.version.default}/me")
    public Principal getMe(Principal p) {
        return p;
    }

}
