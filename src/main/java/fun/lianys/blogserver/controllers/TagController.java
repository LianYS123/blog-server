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
import fun.lianys.blogserver.model.dto.TagDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.service.TagService;
import fun.lianys.blogserver.utils.Utils;

// 数据字典
@RestController
@RequestMapping("/tag")
public class TagController {

  @Autowired
  TagService tagService;

  @Autowired
  Utils utils;

  @GetMapping
  public Result getAllTags() {
    return Result.ofSuccess(tagService.listAll());
  }

  @GetMapping("/list")
  public Result list(@Validated PageParamDto dictQuery) {
    return Result.ofSuccess(tagService.list(dictQuery));
  }

  @PostMapping
  public Result add(@Validated @RequestBody TagDto tag) {
    tag.setCreateTime(utils.getCurrentTime());
    tag.setUpdateTime(utils.getCurrentTime());
    Integer id = tagService.add(tag);
    return Result.ofSuccess(id);
  }

  @PutMapping("/{id}")
  public Result update(@PathVariable(required = true) Integer id, @Validated @RequestBody TagDto tag) {
    tag.setUpdateTime(utils.getCurrentTime());
    tagService.update(tag);
    return Result.ofSuccess(id);
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable(required = true) Integer id) {
    return Result.ofSuccess(tagService.delete(id));
  }

}
