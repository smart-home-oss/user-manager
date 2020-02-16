package lu.smarthome.usermanager;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class KeycloakSecurityContextClientRequestInterceptor implements ClientHttpRequestInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        String query = httpRequest.getURI().getQuery();
        query = query.replaceAll("&&", "&");
        String[] parts = query.split("&");
        for (String part : parts) {
            String[] queryParam = part.split("=");
            if (queryParam.length == 2) {

                if ("state".equals(queryParam[0])) {
                    httpRequest.getHeaders().set(AUTHORIZATION_HEADER, "Bearer " + queryParam[1]);
                }
            }
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}