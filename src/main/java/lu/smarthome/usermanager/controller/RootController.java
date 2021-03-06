package lu.smarthome.usermanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@RestController
@CrossOrigin("${app.api.cors}")
public class RootController {

    @Value("${app.api.version.default}")
    private String defaultApiSpecUri;

    @GetMapping
    public RedirectView redirectRootToSwagger() {
        return new RedirectView("/api");
    }

    @GetMapping("/api")
    public RedirectView redirectApiToSwagger() {
        return new RedirectView("/webjars/swagger-ui/index.html?url=" + defaultApiSpecUri + ".yml");
    }

    @GetMapping("me")
    public Object getMe(Principal p) {
        return p;
    }
}
