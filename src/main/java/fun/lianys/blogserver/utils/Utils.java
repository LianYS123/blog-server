package fun.lianys.blogserver.utils;

import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
import fun.lianys.blogserver.model.entity.JwtUser;

@Component
public class Utils {

  public Integer getCurrentTime() {
    Long time = System.currentTimeMillis() / 1000;
    Integer currentTime = time.intValue();
    return currentTime;
  }

  public JwtUser getCurrentUser() {
    JwtUser author = (JwtUser) (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    return author;
  }

}
