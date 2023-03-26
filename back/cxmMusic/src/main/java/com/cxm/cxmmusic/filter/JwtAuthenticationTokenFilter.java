package com.cxm.cxmmusic.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cxm.cxmmusic.Exception.GlobalException;
import com.cxm.cxmmusic.Exception.StatusCodeEnum;
import com.cxm.cxmmusic.vo.LoginUser;
import com.cxm.cxmmusic.utils.JwtUtils;
import com.cxm.cxmmusic.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //放行 去登录接口
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        //token认证
        JwtUtils.verify(token);

        DecodedJWT payload = JwtUtils.getPayload(token);

        String account = payload.getClaim("account").asString();

        //从redis中获取用户信息
        String redisKey = LoginUser.USER_REDIS_PREFIX + account;
        LoginUser loginUser = redisUtils.getValue(redisKey, LoginUser.class);
        if (Objects.isNull(loginUser)) {
            //用户未登录
            throw new RuntimeException(StatusCodeEnum.NOT_LOGGED_IN.getMessage());
        }


        //把通过认证的用户封装到Authentication
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        //存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        log.info("用户：" + loginUser.getUsername() + "发来了请求");
        //放行
        filterChain.doFilter(request, response);
    }
}