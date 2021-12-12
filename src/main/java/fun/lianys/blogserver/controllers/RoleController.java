package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.RoleDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.service.RoleService;
import fun.lianys.blogserver.utils.Utils;

// 数据字典
@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  RoleService roleService;

  @GetMapping("/list")
  public Result list(@Validated PageParamDto dictQuery) {
    return Result.ofSuccess(roleService.list(dictQuery));
  }

  @PostMapping
  public Result add(@Validated @RequestBody RoleDto role) {
    role.setCreateTime(Utils.getCurrentTime());
    role.setUpdateTime(Utils.getCurrentTime());
    Integer id = roleService.add(role);
    return Result.ofSuccess(id);
  }

  @PutMapping("/{id}")
  public Result update(@PathVariable(required = true) Integer id, @Validated @RequestBody RoleDto role) {
    role.setId(id);
    role.setUpdateTime(Utils.getCurrentTime());
    roleService.update(role);
    return Result.ofSuccess(id);
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable(required = true) Integer id) {
    return Result.ofSuccess(roleService.delete(id));
  }

}
