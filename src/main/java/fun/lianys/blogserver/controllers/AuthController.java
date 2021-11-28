package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.UserDto;
import fun.lianys.blogserver.model.entity.User;
import fun.lianys.blogserver.service.UserService;
import fun.lianys.blogserver.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

  @Autowired
  UserService userService;

  @PostMapping("/login")
  Result login(@Validated @RequestBody UserDto userQuery) {
    System.out.println(userQuery.toString());

    User user = userService.matchUser(userQuery.getUsername(), userQuery.getPassword());
    System.out.println(user);

    if (user == null) {
      return Result.of("1112", "用户名或密码错误", null);
    }

    String token = JwtUtils.createJwt(user);

    return Result.ofSuccess(token);
  }

}