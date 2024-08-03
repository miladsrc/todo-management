package sys.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @org.springframework.beans.factory.annotation.Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-millisecounds}")
    private String jwtExpirationInMs;

    //generate jwt token
    public String generateToken(Authentication authentication) {

        //this field may contain username or email
        String username = authentication.getName();

        //this field will specify the expiration time
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate+jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }


    //this method will decrypt app.jwt-secret
    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }


    //Get username form jwt token
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();

        return username;
    }

    //validate JWT token
    public Boolean validateToken(String token) {
        Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);
        return true;
    }
}
