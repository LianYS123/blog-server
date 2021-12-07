package fun.lianys.blogserver.service;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fun.lianys.blogserver.dao.UserDao;
import fun.lianys.blogserver.enums.StatusEnum;
import fun.lianys.blogserver.exception.BaseException;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.dto.UserInfoDto;
import fun.lianys.blogserver.model.entity.User;
import fun.lianys.blogserver.model.vo.UserInfoVO;
import fun.lianys.blogserver.utils.CurrentUserUtils;
import fun.lianys.blogserver.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  private final UserDao userDao;

  private final PasswordEncoder encoder;

  private final CurrentUserUtils currentUserUtils;

  private UserInfoVO user2uservo(User user) {
    UserInfoVO vo = new UserInfoVO(user.getId(), user.getUsername(), user.getAvatar(), user.getCreateTime(),
        user.getUpdateTime());
    return vo;
  }

  public PageInfo<UserInfoVO> list(PageParamDto params) {
    PageHelper.startPage(params.getPage(), params.getPageSize());
    List<User> userList = userDao.list(params);
    PageInfo pageInfo = new PageInfo(userList);
    List<UserInfoVO> list = userList.stream().map((User user) -> user2uservo(user)).toList();
    pageInfo.setList(list);
    return pageInfo;
  }

  public UserInfoVO getUserInfo(Integer id) {
    User user = userDao.getUserById(id);
    return user2uservo(user);
  }

  public void updateUserInfo(UserInfoDto dto) {
    Integer id = currentUserUtils.getCurrent().getId();
    dto.setUpdateTime(Utils.getCurrentTime());
    dto.setId(id);
    userDao.updateUserInfo(dto);
  }

  public void updateUser(UserInfoDto dto) {
    dto.setUpdateTime(Utils.getCurrentTime());
    userDao.updateUserInfo(dto);
  }

  public void addUser(UserInfoDto dto) {
    dto.setCreateTime(Utils.getCurrentTime());
    dto.setUpdateTime(Utils.getCurrentTime());
    dto.setPassword(encoder.encode(dto.getPassword()));
    userDao.add(dto);
  }

  public User matchUser(String username, String password) {
    User user = userDao.getUserByName(username);
    if (user == null) {
      return null;
    }
    Boolean matched = encoder.matches(password, user.getPassword());
    if (matched) {
      return user;
    }
    return null;
  }

  public void updatePassword(String oldPassword, String newPassword) {
    String username = currentUserUtils.getCurrent().getUsername();
    User user = userDao.getUserByName(username);
    Boolean matched = encoder.matches(oldPassword, user.getPassword());
    if (matched) {
      userDao.updatePassword(user.getId(), encoder.encode(newPassword));
    } else {
      throw new BaseException("1111", "密码错误");
    }
  }

  public void deleteUserById(Integer id) {
    userDao.changeStatus(id, StatusEnum.DELETED.getValue());
  }

  public User loadUserByUsername(String username) {
    User user = userDao.getUserByName(username);
    log.info(user.toString());

    return user;
  }

}
