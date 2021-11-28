package fun.lianys.blogserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.UserDao;
import fun.lianys.blogserver.exception.BaseException;
import fun.lianys.blogserver.model.dto.UserInfoDto;
import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.model.entity.User;
import fun.lianys.blogserver.model.vo.UserInfoVO;
import fun.lianys.blogserver.utils.Utils;

@Service
public class UserService {

  @Autowired
  UserDao userDao;

  @Autowired
  Utils utils;

  @Autowired
  PasswordEncoder encoder;

  private UserInfoVO user2uservo(User user) {
    UserInfoVO vo = new UserInfoVO(user.getId(), user.getUsername(), user.getAvatar(), user.getCreateTime(),
        user.getUpdateTime());
    return vo;
  }

  public UserInfoVO getUserInfo(Integer id) {
    User user = userDao.getUserById(id);
    return user2uservo(user);
  }

  public void updateUserInfo(UserInfoDto dto) {
    Integer id = utils.getCurrentUser().getId();
    dto.setUpdateTime(utils.getCurrentTime());
    dto.setId(id);
    userDao.updateUserInfo(dto);
  }

  public void updatePassword(String oldPassword, String newPassword) {
    JwtUser user = utils.getCurrentUser();
    Boolean matched = encoder.matches(oldPassword, user.getPassword());
    if (matched) {
      userDao.updatePassword(user.getId(), encoder.encode(newPassword));
    } else {
      throw new BaseException("1111", "密码错误");
    }
    // Authentication auth = new
    // UsernamePasswordAuthenticationToken(user.getUsername(), oldPassword); //
    // 生成auth token
    // Authentication authed = authenticationManager.authenticate(auth); // 校验
    // if (len == 0) {
    // throw new BaseException("1111", "密码错误");
    // }
  }

}
