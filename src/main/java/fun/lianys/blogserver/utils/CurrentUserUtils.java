package fun.lianys.blogserver.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import fun.lianys.blogserver.model.dto.TokenUser;

/**
 * @description 获取当前请求的用户
 */
@Component
public class CurrentUserUtils {

    public Integer getId() {
        return getCurrent().getId();
    }

    public String getUserName() {
        return getCurrent().getUsername();
    }

    public TokenUser getCurrent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            return (TokenUser) authentication.getPrincipal();
        }
        return null;
    }

}
