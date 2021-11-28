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
public class AuthControllerTest {
  @Autowired
  private MockMvc mvc;

  @Test
  void testLogin() throws Exception {
    JSONObject params = new JSONObject();
    params.put("username", "lys");
    params.put("password", "tb1766318380");
    mvc.perform(MockMvcRequestBuilders.post("/auth/login")
        .content(params.toJSONString())
        .contentType(MediaType.APPLICATION_JSON))
        // .andExpect(MockMvcResultMatchers.status().isOk())
        // .andExpect(MockMvcResultMatchers.content().json("{'code':'200'}"))
        .andDo(MockMvcResultHandlers.print()).andReturn();
  }

}
