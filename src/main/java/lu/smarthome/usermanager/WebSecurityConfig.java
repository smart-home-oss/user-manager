package lu.smarthome.usermanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.oauth2.jwt.JwtDecoders;

import java.nio.charset.StandardCharsets;

import static java.util.Collections.singletonList;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper objectMapper;
    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${app.oauth2.issuer}")
    private String issuer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
            .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(userService());
    }

    @Bean
    public JwtDecoderFactory<ClientRegistration> jwtDecoderFactory() {
        final JwtDecoder decoder = JwtDecoders.fromIssuerLocation(issuer);
        return context -> decoder;

    }

    private OAuth2UserService<OAuth2UserRequest, OAuth2User> userService() {
        DefaultOAuth2UserService userService = new DefaultOAuth2UserService();

        MappingJackson2HttpMessageConverter messageConverter =
                new MappingJackson2HttpMessageConverter(objectMapper);
        messageConverter.setSupportedMediaTypes(
                singletonList(
                        new MediaType(
                                "text", "javascript", StandardCharsets.UTF_8
                        )
                )
        );

        userService.setRestOperations(
                restTemplateBuilder
                        .errorHandler(new OAuth2ErrorResponseErrorHandler())
                        .additionalMessageConverters(messageConverter)
                        .interceptors(new KeycloakSecurityContextClientRequestInterceptor())
                        .build()
        );

        return userService;
    }

}