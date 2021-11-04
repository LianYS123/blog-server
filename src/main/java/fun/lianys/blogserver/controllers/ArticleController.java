package fun.lianys.blogserver.controllers;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.AddArticleDto;
import fun.lianys.blogserver.model.dto.ArticleQueryDto;
import fun.lianys.blogserver.model.dto.EditArticleDto;
import fun.lianys.blogserver.service.ArticleService;
import fun.lianys.blogserver.utils.Utils;

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

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @Autowired
    Utils utils;

    @GetMapping("/detail/{id}")
    public Result get(@PathVariable Integer id){
        return Result.ofSuccess(articleService.findOne(id));
    }

    @GetMapping("/list")
    public Result list(@Validated ArticleQueryDto articleQuery) {
        return Result.ofSuccess(articleService.list(articleQuery));
    }

    @PostMapping
    public Result add(@Validated @RequestBody AddArticleDto article) {
        article.setCreateTime(utils.getCurrentTime());
        article.setUpdateTime(utils.getCurrentTime());
        article.setAuthor(utils.getCurrentUser().getId());
        Integer id = articleService.add(article);
        return Result.ofSuccess(id);
    }

    @PutMapping
    public Result update(@Validated @RequestBody EditArticleDto article) {
        article.setUpdateTime(utils.getCurrentTime());
        Integer id = articleService.update(article);
        return Result.ofSuccess(id);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.ofSuccess(articleService.delete(id));
    }

}
