package fun.lianys.blogserver.service;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

  @Autowired
  private COSClient cosClient;

  public URL upload(MultipartFile file) throws IOException {

    // api
    // https://cloud.tencent.com/document/product/436/35215#.E4.B8.8A.E4.BC.A0.E5.AF.B9.E8.B1.A1.EF.BC.88.E5.88.9B.E5.BB.BA.E6.96.87.E4.BB.B6.E5.A4.B9.EF.BC.89

    // 指定文件将要存放的存储桶
    String bucketName = "liuli-1259462774";
    // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder
    // 路径下
    String uuid = UUID.randomUUID().toString();
    String key = uuid + file.getOriginalFilename();
    ObjectMetadata objectMetadata = new ObjectMetadata();
    // 设置输入流长度（STREAMLENGTH根据自己流大小做替换）
    objectMetadata.setContentLength(file.getSize());
    // 设置 Content type, 默认是 application/octet-stream
    // objectMetadata.setContentType("application/pdf");
    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata);
    PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
    System.out.println(putObjectResult);
    URL objectUrl = cosClient.getObjectUrl(bucketName, key);
    return objectUrl;
    // Files.copy(file.getInputStream(), dest.toPath());
  }

}
