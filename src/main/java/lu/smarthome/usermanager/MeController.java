package lu.smarthome.usermanager;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.api.version.v1}/me")
@CrossOrigin("${app.api.cors}")
public class MeController {

    @GetMapping
    public Principal getMe(Principal p) {
        return p;
    }

}
