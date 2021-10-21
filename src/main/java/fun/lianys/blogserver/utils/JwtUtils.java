package fun.lianys.blogserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


@Component
public class JwtUtils {
    static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); //or HS384 or HS512

    public static String createJwt(){
        return null;
    }

    public static Claims parseJwt(){
        return null;
    }

}
