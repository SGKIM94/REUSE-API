package reuse.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import reuse.exception.InvalidAccessTokenException;

import java.util.Date;

@Component
public class TokenAuthenticationService {
    private static final String SALT = "63B75D39E3F6BFE72263F7C1145AC22E";
    public static final String BEARER_TOKEN_TYPE = "Bearer";
    private static final String TOKEN_TYPE_KEY = "tokenType";
    private static final String SOCIAL_TOKEN_ID = "socialTokenId";


    public byte[] generateKey(String salt) {
        return salt.getBytes();
    }

    public String toJwtBySocialTokenId(String socialTokenId) {
        return Jwts.builder()
                .claim(SOCIAL_TOKEN_ID, socialTokenId)
                .setHeaderParam(TOKEN_TYPE_KEY, BEARER_TOKEN_TYPE)
                .setSubject(socialTokenId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, this.generateKey(SALT))
                .compact();
    }

    public String getSocialTokenByJwt(String jwt) {
        try {
            Jws<Claims> claims = getJwtClaim(jwt);
            return getSocialTokenIdByClaims(claims);

        } catch (ExpiredJwtException e) {
            throw new InvalidAccessTokenException("Token is expired!");
        }
    }


    public Jws<Claims> getJwtClaim(String jwt) {
        return Jwts.parser()
                .setSigningKey(generateKey(SALT))
                .parseClaimsJws(jwt);
    }

    public Boolean isVerifyToken(String jwt) {
        return Jwts.parser()
                .isSigned(jwt);
    }

    public String getSocialTokenIdByClaims(Jws<Claims> claims) {
        return claims.getBody().get(SOCIAL_TOKEN_ID).toString();
    }

    public String getTokenTypeByJwt(String jwt) {
        return getJwtClaim(jwt).getHeader().get(TOKEN_TYPE_KEY).toString();
    }
}
