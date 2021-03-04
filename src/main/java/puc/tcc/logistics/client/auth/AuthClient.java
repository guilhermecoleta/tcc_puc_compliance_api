package puc.tcc.logistics.client.auth;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AuthClient {

    private static final String URL = "http://tcc-puc-auth.herokuapp.com/auth/get-info";

    public UserResponse validate(String token) {

        try {

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", token);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<UserResponse> response = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity(headers), UserResponse.class);
            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
