
        package surnoi.FusionIQ.FusionIQ.service;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import surnoi.FusionIQ.FusionIQ.data.BasicInfo;
import java.util.Date;
@Service
public class TokenService {
    private static final String SECRET_KEY = "Zxs07E2JjXAFfS8GPuCCyOvHEWDG5GujHhxCmvdrPmI";
    private static final long EXPIRATION_TIME = 86400000L; // 1 day
    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public boolean validateToken(String token, BasicInfo userDetails) {
        final String email = getEmailFromToken(token);
        return (email.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    private boolean isTokenExpired(String token) {
        final Date expiration = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
 