package fun.lianys.blogserver.service;

import fun.lianys.blogserver.dao.UserDao;
import fun.lianys.blogserver.model.entity.JwtUser;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao = null;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        JwtUser user = userDao.getUserByName(s);
        log.info(user.toString());

        // if(user == null) {
        // throw new BaseException("0001", "用户名或密码错误"); 
        // }
        return user;
    }
}
