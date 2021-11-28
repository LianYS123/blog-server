package fun.lianys.blogserver.controllers;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
public class ArticleControllerTest {
  @Autowired
  private MockMvc mvc;

  @Test
  void testList() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/article/test")
        .header("Authorization",
            "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsaWFuJ2Jsb2ciLCJzdWIiOiJseXMiLCJqdGkiOiIxIiwiZXhwIjoxNjM4NjcxNjc0fQ.BQaMY9X97hE7RD-eGrriC6QmJxdjWYk-UvJpz88nwI8"))
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  @Test
  void testNoToken() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/article/test"))
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

  @Test
  void test() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/article/detail/5")
        .header("Authorization",
            "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJsaWFuJ2Jsb2ciLCJzdWIiOiJseXMiLCJqdGkiOiIxIiwiZXhwIjoxNjM4NjcxNjc0fQ.BQaMY9X97hE7RD-eGrriC6QmJxdjWYk-UvJpz88nwI8"))
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

}
