package net.sxbang.watchapp.common.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.sxbang.watchapp.common.result.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * url:/logout
     * @param httpServletRequest
     * @param httpServletResponse
     * @param authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        // 0. httpServletResponse去定义一个JSON格式的返回方式
        ObjectMapper objectMapper = new ObjectMapper();
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        // 1. 从httpServletRequest里获取access_token
        String accessToken = httpServletRequest.getParameter("access_token");

        // 2. 清除redis里access_token, refresh_token, access_token与refresh_token的关联数据
        if (!StringUtils.isEmpty(accessToken)) {
            // 2.1 redis
            TokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);

            // 2.2 找到access_token, refresh_token对象
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();

            // 2.3 清除
            tokenStore.removeAccessToken(oAuth2AccessToken);
            tokenStore.removeRefreshToken(oAuth2RefreshToken);
            tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
        }

        // 3. 返回RestResult.success()
        objectMapper.writeValue(httpServletResponse.getWriter(), RestResult.success());
    }
}
