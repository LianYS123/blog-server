package fun.lianys.blogserver.service;

import fun.lianys.blogserver.dao.UserDao;
import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao = null;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDao.getUserByName(s);
        JwtUser jwtUser = new JwtUser(user.getId(), user.getUsername(), user.getPassword());
        return jwtUser;
    }
}
