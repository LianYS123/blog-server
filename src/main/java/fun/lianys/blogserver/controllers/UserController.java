package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.ChangePasswordDto;
import fun.lianys.blogserver.model.dto.UserInfoDto;
import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.model.vo.UserInfoVO;
import fun.lianys.blogserver.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public Result getUserInfo() {
    JwtUser jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    UserInfoVO user = userService.getUserInfo(jwtUser.getId());
    return Result.ofSuccess(user);
  }

  @PutMapping
  public Result changeUserInfo(@Validated @RequestBody UserInfoDto dto) {
    userService.updateUserInfo(dto);
    return Result.ofSuccess(null);
  }

  @PutMapping("/password")
  public Result changePassword(@Validated @RequestBody ChangePasswordDto dto) {
    userService.updatePassword(dto.getOldPassword(), dto.getNewPassword());
    // System.out.println(dto.getOldPassword());
    // System.out.println(dto.getNewPassword());
    return Result.ofSuccess(null);
  }

}
