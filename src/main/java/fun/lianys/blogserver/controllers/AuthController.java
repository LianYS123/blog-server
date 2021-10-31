package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.UserDto;
import fun.lianys.blogserver.model.entity.JwtUser;
import fun.lianys.blogserver.utils.JwtUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/login")
  Result login(@Validated @RequestBody UserDto userQuery) {
    System.out.println(userQuery.toString());
    Authentication auth = new UsernamePasswordAuthenticationToken(userQuery.getUsername(), userQuery.getPassword()); // 生成auth token
    Authentication authed = authenticationManager.authenticate(auth); // 校验

    SecurityContextHolder.getContext().setAuthentication(authed);

    JwtUser user = (JwtUser)authed.getPrincipal();

    String token = jwtUtils.createJwt(user);
    
    return Result.ofSuccess(token);
  }

}