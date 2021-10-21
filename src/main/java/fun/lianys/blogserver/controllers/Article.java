package fun.lianys.blogserver.controllers;

import fun.lianys.blogserver.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class Article {
    @GetMapping("/list")
    public Result list(){
        return Result.ofSuccess(null);
    }
}
