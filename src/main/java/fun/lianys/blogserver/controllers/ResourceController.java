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
import fun.lianys.blogserver.model.dto.ResourceDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.service.ResourceService;
import fun.lianys.blogserver.utils.Utils;

// 数据字典
@RestController
@RequestMapping("/resource")
public class ResourceController {

  @Autowired
  ResourceService resourceService;

  @Autowired
  Utils utils;

  @GetMapping("/list")
  public Result list(@Validated PageParamDto dictQuery) {
    return Result.ofSuccess(resourceService.list(dictQuery));
  }

  @PostMapping
  public Result add(@Validated @RequestBody ResourceDto resource) {
    resource.setCreateTime(utils.getCurrentTime());
    resource.setUpdateTime(utils.getCurrentTime());
    Integer id = resourceService.add(resource);
    return Result.ofSuccess(id);
  }

  @PutMapping("/{id}")
  public Result update(@PathVariable(required = true) Integer id, @Validated @RequestBody ResourceDto resource) {
    resource.setId(id);
    resource.setUpdateTime(utils.getCurrentTime());
    resourceService.update(resource);
    return Result.ofSuccess(id);
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable(required = true) Integer id) {
    return Result.ofSuccess(resourceService.delete(id));
  }

}
