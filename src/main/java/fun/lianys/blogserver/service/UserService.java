package fun.lianys.blogserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.UserDao;
import fun.lianys.blogserver.model.entity.User;
import fun.lianys.blogserver.model.vo.UserInfoVO;

@Service
public class UserService {

  @Autowired
  UserDao userDao;

  private UserInfoVO user2uservo(User user) {
    UserInfoVO vo = new UserInfoVO(user.getId(), user.getUsername(), user.getCreateTime(), user.getUpdateTime());
    return vo;
  }

  public UserInfoVO getUserInfo(Integer id) {
    User user = userDao.getUserById(id);
    return user2uservo(user);
  }

}
