package fun.lianys.blogserver.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import fun.lianys.blogserver.common.Result;
import fun.lianys.blogserver.utils.JwtUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (checkIgnores(request)) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = request.getHeader("Authorization");
    try {
      String username = jwtUtils.parseJwt(token, request);
      UserDetails userDetails = userDetailsService.loadUserByUsername(username);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

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
