package fun.lianys.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.lianys.blogserver.common.Result;
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

}
