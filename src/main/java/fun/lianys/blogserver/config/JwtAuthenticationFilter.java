package fun.lianys.blogserver.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.model.dto.TokenUser;
import fun.lianys.blogserver.utils.JwtUtils;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    if (checkIgnores(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = request.getHeader("Authorization");
    if (token != null) {
      token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
    }

    try {
      TokenUser user = JwtUtils.parseJwt(token);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);

      // 获取安全对象上下文, 修改authentication
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (SecurityException ex) {
      response.setStatus(401);
      response.getWriter().write(JSONUtil.toJsonStr(new JSONObject(Result.of("1001", ex.getMessage(), null))));
    }

  }

  private Boolean checkIgnores(HttpServletRequest request) {
    return new AntPathRequestMatcher("/auth/login", request.getMethod()).matches(request);
    // return false;
  }

}
