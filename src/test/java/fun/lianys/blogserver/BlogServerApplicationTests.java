package fun.lianys.blogserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
class BlogServerApplicationTests {

    @Test
    void contextLoads() {
    }

    public String genEncodedKey() {
        String key = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
        return Encoders.BASE64.encode(key.getBytes());
    }

    @Test
    void test() {
        System.out.println(genEncodedKey());
    }

    public static void main(String[] args) {
        String key = Keys.secretKeyFor(SignatureAlgorithm.HS256).toString();
        String str = Encoders.BASE64.encode(key.getBytes());
        System.out.println(str);
    }

}
