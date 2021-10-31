
package fun.lianys.blogserver.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.service.UploadService;

@RestController
@RequestMapping("/upload")
public class UploadController {

  @Value("${file.upload.path}")
  private String path;

  @Autowired
  UploadService uploadService;


  @PostMapping
  Result upload(@RequestParam MultipartFile file) throws IOException {

    return Result.ofSuccess(uploadService.upload(file));
  }
}