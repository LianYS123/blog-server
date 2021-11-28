package fun.lianys.blogserver.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import fun.lianys.blogserver.config.SecurityConstants;
import fun.lianys.blogserver.model.dto.TokenUser;
import fun.lianys.blogserver.model.entity.User;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

@Component
@Slf4j
public class JwtUtils {
    static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // or HS384 or HS512

    // @Value("${jwt.secretKey}")
    private static String secretKey = SecurityConstants.JWT_SECRET_KEY;

    /**
     * token 过期时间, 单位: s. 这个值表示 7 天
     */
    private static long TOKEN_EXPIRED_TIME = 7 * 24 * 60 * 60 * 1000;

    public static String createJwt(User user) {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date exp = new Date(System.currentTimeMillis() + TOKEN_EXPIRED_TIME);
        String jwt = Jwts.builder().setIssuer("lian'blog").setSubject(user.getUsername()).setId(user.getId().toString())
                .setExpiration(exp)
                .signWith(key).compact();
        return jwt;
    }

    public static TokenUser parseJwt(String jws) {
        String TOKEN_EXPIRED = "token已过期";
        String TOKEN_PARSE_ERROR = "token解析错误";
        try {
            Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody();
            String username = claims.getSubject();
            String id = claims.getId();
            TokenUser tokenUser = new TokenUser(username, Integer.parseInt(id));
            return tokenUser;
        } catch (ExpiredJwtException e) {
            log.error("Token 已过期");
            throw new SecurityException(TOKEN_EXPIRED);
        } catch (UnsupportedJwtException e) {
            log.error("不支持的 Token");
            throw new SecurityException(TOKEN_PARSE_ERROR);
        } catch (MalformedJwtException e) {
            log.error("Token 无效");
            throw new SecurityException(TOKEN_PARSE_ERROR);
        } catch (SignatureException e) {
            log.error("无效的 Token 签名");
            System.out.println(e);
            throw new SecurityException(TOKEN_PARSE_ERROR);
        } catch (IllegalArgumentException e) {
            log.error("Token 参数不存在");
            throw new SecurityException(TOKEN_PARSE_ERROR);
        }
    }

}
