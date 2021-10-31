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
import fun.lianys.blogserver.model.dto.AddDictDto;
import fun.lianys.blogserver.model.dto.EditDictDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.service.DictService;
import fun.lianys.blogserver.utils.Utils;

@RestController
@RequestMapping("/setting")
public class SettingController {

  @Autowired
  DictService dictService;

  @Autowired
  Utils utils;

  @GetMapping
  public Result getAll() {
    return Result.ofSuccess(null);
  }

  // 数据字典
  @GetMapping("/dict")
  public Result getAllDicts() {
    return Result.ofSuccess(dictService.listAll());
  }

  @GetMapping("/dict/list")
  public Result list(@Validated PageParamDto dictQuery) {
    return Result.ofSuccess(dictService.list(dictQuery));
  }

  @PostMapping("/dict")
  public Result add(@Validated @RequestBody AddDictDto dict) {
    dict.setCreateTime(utils.getCurrentTime());
    dict.setUpdateTime(utils.getCurrentTime());
    Integer id = dictService.add(dict);
    return Result.ofSuccess(id);
  }

  @PutMapping("/dict")
  public Result update(@Validated @RequestBody EditDictDto dict) {
    dict.setUpdateTime(utils.getCurrentTime());
    Integer id = dictService.update(dict);
    return Result.ofSuccess(id);
  }

  @DeleteMapping("/dict/{id}")
  public Result delete(@PathVariable(required = true) Integer id) {
    return Result.ofSuccess(dictService.delete(id));
  }

}
