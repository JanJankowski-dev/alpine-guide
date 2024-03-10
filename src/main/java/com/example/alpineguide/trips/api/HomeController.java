package com.example.alpineguide.trips.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.annotation.security.RolesAllowed;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.List;


@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getTrips() {
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/elope")
    public ResponseEntity<String> getEloElo() {
        return ResponseEntity.ok("getEloElo");
    }

    @RequestMapping(value = "/anonymous", method = RequestMethod.GET)
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Anonymous");
    }

    @RolesAllowed("user")
    @GetMapping(value = "/users")
    public ResponseEntity<String> getUser(@RequestHeader("Authorization") String authorization) {

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String token = authorization.substring(7);

            try {
                DecodedJWT jwt = JWT.decode(token);
                DecodedJWT decodedJWT = verifyToken(token);

                String subject = jwt.getSubject();
                List<String> roles = jwt.getClaim("resource_access").asList(String.class);

                return ResponseEntity.ok("Hello " + subject + ", roles: " + roles);
            } catch (Exception e) {
                // Obsługa wyjątku, np. nieprawidłowy token JWT
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        }


        return ResponseEntity.ok("Hello User");
    }

    @RolesAllowed("admin")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ResponseEntity<String> getAdmin(@RequestHeader String Authorization) {
        return ResponseEntity.ok("Hello Admin");
    }

    @RolesAllowed({"admin", "user"})
    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public ResponseEntity<String> getAllUser(@RequestHeader String Authorization) {
        return ResponseEntity.ok("Hello All User");
    }

    public DecodedJWT verifyToken(String token) throws Exception {

        String jwksUrl = "http://localhost:8080/realms/myrealm/protocol/openid-connect/certs";

        // Dekoduje JWT bez weryfikacji, aby uzyskać ID klucza (kid)
        DecodedJWT jwt = JWT.decode(token);
        String jwts = fetchJwks(jwksUrl);

        JSONObject jwksResponse = new JSONObject(new JSONTokener(jwts));

        JSONArray keys = jwksResponse.getJSONArray("keys");
        RSAPublicKey publicKey=  null;
        for (int i = 0; i < keys.length(); i++) {
            JSONObject key = keys.getJSONObject(i);
            if (key.getString("kid").equals(jwt.getKeyId())) {
                BigInteger modulus = new BigInteger(1, Base64.getUrlDecoder().decode(key.getString("n")));
                BigInteger exponent = new BigInteger(1, Base64.getUrlDecoder().decode(key.getString("e")));
                RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
                KeyFactory factory = KeyFactory.getInstance("RSA");
                publicKey =  (RSAPublicKey) factory.generatePublic(spec);
            }
        }
        // Tworzy algorytm z kluczem publicznym
        Algorithm algorithm = Algorithm.RSA256(publicKey, null);
        // Weryfikuje token i zwraca zdekodowany JWT
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("http://localhost:8080/realms/myrealm")
                .build();
        return verifier.verify(token);
    }

    public static String fetchJwks(String jwksUrl) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            var request = new HttpGet(jwksUrl);
            return httpClient.execute(request, httpResponse ->
                    EntityUtils.toString(httpResponse.getEntity()));
        }
    }
}


