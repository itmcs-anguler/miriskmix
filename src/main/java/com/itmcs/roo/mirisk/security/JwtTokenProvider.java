package com.itmcs.roo.mirisk.security;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.itmcs.roo.mirisk.dta.ManageUser;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";
    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000; // 1h


    boolean authenticateUsingCredentials(HttpServletRequest request, HttpServletResponse response) {
    	System.out.println("Attempting authentication using credentials...");
        try {
            String base64AuthHeader = request.getHeader("Authorization");
             System.out.println("base64AuthHeader: " + base64AuthHeader);
            if (base64AuthHeader == null || base64AuthHeader.isEmpty())
                return false;
            base64AuthHeader = base64AuthHeader.replace("Basic ", "");
            System.out.println("base64AuthHeader: " + base64AuthHeader);
            String authHeader = new String(Base64.getDecoder().decode(base64AuthHeader), StandardCharsets.UTF_8);
            System.out.println("authHeader: " + authHeader);
            String[] credentials = authHeader.split(":");
            if (credentials.length < 2)
                return false;
            Authentication auth = new UsernamePasswordAuthenticationToken(credentials[0], credentials[1]);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return true;
        } catch (Exception ae) {
        	System.out.println("Authentication attempt using credentials failed due to \"" + ae.getMessage() + "\".");
        }
        return false;
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public DecodedJWT verifyToken(String token) {
        if (token == null)
            return null;
        DecodedJWT jwt = null;

        // DecodedJWT jwt = JWT.decode(token);
        // log.info("jwt username: " + jwt.getClaim("username").asString());
        // UserDetails userDetails =
        // userDetailsService.loadUserByUsername(jwt.getClaim("username").asString());
        // log.info(userDetails.getUsername());
        // log.info(userDetails.getPassword());

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("system")
                    // .withClaim("username",
                    // userDetailsService.loadUserByUsername(username))
                    .build();
            jwt = verifier.verify(token);
        } catch (JWTVerificationException exception) {
            // UTF-8 encoding not supported
        	System.out.println("\n\nToken verification failed due to \"" + exception.getMessage() + "\".\n");
        }
        return jwt;
    }

    public String generateToken(Authentication userDetails) {       
        String token = null;
        HashMap<String, String> tokenData = null;

         ManageUser userdto = null;
         if (userDetails.getPrincipal() instanceof ManageUser)
        	 userdto = (ManageUser) userDetails.getPrincipal();
        //
        // if (null == userdto)
        // return null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            long currTime = Calendar.getInstance().getTimeInMillis();
            long expireAfterMillis = 900000; // 1800000;
            Date expireAt = new Date(currTime + expireAfterMillis);
            token = JWT.create().withIssuer("system").withExpiresAt(expireAt)
                    .withClaim("username", userdto.getUsername())
                    .withClaim("password", userdto.getPassword())
                    .sign(algorithm);
            System.out.println("Token will expire at: " + expireAt.toString());

            try {
                DecodedJWT jwt = JWT.decode(token);
                String issuer = jwt.getIssuer();
                // boolean admin = jwt.getClaim("admin").asBoolean();
                // String username = jwt.getClaim("username").asString();
                // String password = jwt.getClaim("password").asString();
                String expiresAt = jwt.getExpiresAt().toString();

                tokenData = new HashMap<String, String>();
                tokenData.put("token", token);
                tokenData.put("issuer", issuer);
                // tokenData.put("admin", admin + "");
                tokenData.put("expiresAt", expiresAt);
            } catch (JWTDecodeException exception) {
                // Invalid token
                exception.printStackTrace();
            }
        } catch (JWTCreationException exception) {
            exception.printStackTrace();
        }
        return token;
    }

    public boolean isNewTokenRequired(DecodedJWT jwt) {
        // String issuer = jwt.getIssuer();
        // boolean admin = jwt.getClaim("admin").asBoolean();
        String expiresAt = jwt.getExpiresAt().toString();
        long expiryDateInMillis = 0;
        try {
            SimpleDateFormat parseFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date date = parseFormat.parse(expiresAt);
            expiryDateInMillis = date.getTime();
        } catch (ParseException e) {
        	System.out.println("\nNot able to parse date.\n");
            e.printStackTrace();
        }
        long currTimeInMillis = Calendar.getInstance().getTimeInMillis();
        return (expiryDateInMillis - currTimeInMillis <= 10000);
    }

    // public Authentication getAuthentication(String token) {
    // UserDetails userDetails =
    // this.userDetailsService.loadUserByUsername(getUsername(token));
    // return new UsernamePasswordAuthenticationToken(userDetails, "",
    // userDetails.getAuthorities());
    // }
}
