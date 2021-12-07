package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.ChangePasswordDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.model.dto.UserInfoDto;
import fun.lianys.blogserver.model.vo.UserInfoVO;
import fun.lianys.blogserver.service.UserService;
import fun.lianys.blogserver.utils.CurrentUserUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final CurrentUserUtils currentUserUtils;

  private final UserService userService;

  @GetMapping
  public Result getUserInfo() {
    UserInfoVO user = userService.getUserInfo(currentUserUtils.getId());
    return Result.ofSuccess(user);
  }

  @GetMapping("/list")
  public Result list(@Validated PageParamDto query) {
    return Result.ofSuccess(userService.list(query));
  }

  @PutMapping
  public Result changeUserInfo(@Validated @RequestBody UserInfoDto dto) {
    userService.updateUserInfo(dto);
    return Result.ofSuccess(null);
  }

  @PutMapping("/{id}")
  public Result updateUser(@Validated @RequestBody UserInfoDto dto, @PathVariable Integer id) {
    dto.setId(id);
    userService.updateUserInfo(dto);
    return Result.ofSuccess(null);
  }

  @DeleteMapping("/{id}")
  public Result deleteUser(@PathVariable Integer id) {
    userService.deleteUserById(id);
    return Result.ofSuccess(null);
  }

  @PostMapping
  public Result addUser(@Validated @RequestBody UserInfoDto dto) {
    userService.addUser(dto);
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
