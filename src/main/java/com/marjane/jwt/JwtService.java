package com.marjane.jwt;

import com.marjane.core.dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utility class that provides methods for building, decomposing and validating JWT tokens.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class JwtService {

    /**
     * Generates new JWT token for certain user provided via {@link UserDetails} .
     *
     * @param userDetails implementation of {@link UserDetails} interface with user data
     * @return generated JWT token
     */
    public String createToken(UserDetails userDetails) {
        return createToken(userDetails, new HashMap<>());
    }

    /**
     * Generates new JWT token for certain user and adds extra claims in the token body.
     *
     * @param userDetails implementation of {@link UserDetails} interface with user data
     * @param additionalClaims dictionary with claims that should be written inside the token body
     * @return generated JWT token
     */
    public String createToken(
            UserDetails userDetails,
            Map<String, Object> additionalClaims
    ) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .addClaims(additionalClaims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Extracts user phone number from a provided JWT token.
     *
     * @param token token to decompose
     * @return phone number of user or null if there is no subject claim inside provided token
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    /**
     * Extracts expiration date from a provided JWT token.
     *
     * @param token token to decompose
     * @return expiration date of a provided JWT token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Uses provided claim resolver function to take certain claim from a token body.
     * @param token token to decompose
     * @param resolver function that can extract claim from a token body
     * @return extracted claim value
     * @param <T> type of claim to extract
     */
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    /**
     * Parses provided token and returns a {@link Map} implementations that stores all the claims from the token.
     * @param token token to decompose
     * @return {@link Claims} object extracted from provided token
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Checks if provided token corresponds with data from {@link UserDetails} and if it is not expired yet.
     * @param token token to validate
     * @param userDetails user data wrapped in {@link UserDetails} implementation
     * @return {@code true} if token is valid and {@code false} - otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String phoneNumber = extractEmail(token);
        return phoneNumber.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * Checks if provided token is expired
     * @param token token to check
     * @return {@code true} if token is not valid anymore and {@code false} - otherwise
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    /**
     * Converts provided secret into a key for HMAC-SHA encoding algorithm.
     * @return generated key
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(dotenv.get("JWT_SECRET"));
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
