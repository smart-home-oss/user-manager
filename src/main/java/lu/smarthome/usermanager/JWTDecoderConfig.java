package lu.smarthome.usermanager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
@RequiredArgsConstructor
public class JWTDecoderConfig {

    @Value("${app.oauth2.issuer}")
    private String issuer;

    @Bean
    public JwtDecoderFactory<ClientRegistration> jwtDecoderFactory() {
        final JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuer);
        return context -> decoder;
    }

}