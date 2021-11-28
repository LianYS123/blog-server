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
import fun.lianys.blogserver.model.dto.EssayDto;
import fun.lianys.blogserver.model.dto.PageParamDto;
import fun.lianys.blogserver.service.EssayService;
import fun.lianys.blogserver.utils.Utils;

// 数据字典
@RestController
@RequestMapping("/essay")
public class EssayController {

  @Autowired
  EssayService essayService;

  @GetMapping("/list")
  public Result list(@Validated PageParamDto dictQuery) {
    return Result.ofSuccess(essayService.list(dictQuery));
  }

  @PostMapping
  public Result add(@Validated @RequestBody EssayDto essay) {
    essay.setCreateTime(Utils.getCurrentTime());
    essay.setUpdateTime(Utils.getCurrentTime());
    Integer id = essayService.add(essay);
    return Result.ofSuccess(id);
  }

  @PutMapping("/{id}")
  public Result update(@PathVariable(required = true) Integer id, @Validated @RequestBody EssayDto essay) {
    essay.setId(id);
    essay.setUpdateTime(Utils.getCurrentTime());
    essayService.update(essay);
    return Result.ofSuccess(id);
  }

  @DeleteMapping("/{id}")
  public Result delete(@PathVariable(required = true) Integer id) {
    return Result.ofSuccess(essayService.delete(id));
  }

}
