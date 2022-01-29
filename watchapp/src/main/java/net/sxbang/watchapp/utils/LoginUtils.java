package net.sxbang.watchapp.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import net.sxbang.watchapp.model.vo.LocalAuthTokenVO;
import net.sxbang.watchapp.model.vo.WechatAuthTokenVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class LoginUtils {

    private static String AUTH_URL;

    private static String APPID;

    private static String SECRET;

    @Value("${auth-url}")
    public void setLocalDomain(String authUrl) {
        LoginUtils.AUTH_URL = authUrl;
    }

    @Value("${wx.secret}")
    public void setSecret(String secret) {
        LoginUtils.SECRET = secret;
    }

    @Value("${wx.appid}")
    public void setAppid(String appid) {
        LoginUtils.APPID = appid;
    }


    /**
     * 获取微信session_key
     * @param code
     * @return
     * @throws IOException
     */
    public static WechatAuthTokenVO getWxSessionKeyAndOpenIdByCode(String code) throws IOException {
        String wxJsCodeUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                APPID, SECRET, code);
        String responseBody = MyHttpClientUtils.sendPost(wxJsCodeUrl);
        JSONObject resultObject = JSONObject.parseObject(responseBody);
        return convertJsonToWechatAuthTokenVO(resultObject);
    }

    /**
     * 把JSONObject 转化化 WechatAuthTokenVO 对象
     * @param jsonObject
     * @return
     */
    private static WechatAuthTokenVO convertJsonToWechatAuthTokenVO(JSONObject jsonObject) {
        WechatAuthTokenVO vo = new WechatAuthTokenVO();
        if (jsonObject != null && jsonObject.getIntValue("errorCode") == 0) {
            vo.setOpenid(jsonObject.getString("openid"));
            vo.setSessionKey(jsonObject.getString("session_key"));
            vo.setExpiresIn(jsonObject.getInteger("expires_in"));
            return vo;
        }
        return null;
    }

    /**
     * 密码模式登录授权
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public static LocalAuthTokenVO loginMyselfByPassword(String username, String password, String clientId, String clientSecret) throws IOException {
        final String GRANT_TYPE = "password";
        final String SCOPE = "all";
        // http://localhost:90/oauth/token?grant_type=password&scope=all&client_id=xx&client_secret=xx&username=xx&password=
        String authorizationUrl = String.format("%s?grant_type=%s&scope=%s&client_id=%s&client_secret=%s&username=%s&password=%s",
                AUTH_URL, GRANT_TYPE, SCOPE, clientId, clientSecret, username, password);
        String responseBody = MyHttpClientUtils.sendPost(authorizationUrl);
        JSONObject resultObject = JSONObject.parseObject(responseBody);
        if (resultObject.getString("error") != null) {
            return null;
        }
        return convertJsonToLocalAuthTokenVO(resultObject);
    }

    /**
     * 把JSONObject 转化化 LocalAuthTokenVO 对象
     * @param jsonObject
     * @return
     */
    public static LocalAuthTokenVO convertJsonToLocalAuthTokenVO(JSONObject jsonObject) {
        LocalAuthTokenVO vo = new LocalAuthTokenVO();
        if (jsonObject != null) {
            vo.setAccessToken(jsonObject.getString("access_token"));
            vo.setExpiresIn(jsonObject.getInteger("expires_in"));
            vo.setRefreshToken(jsonObject.getString("refresh_token"));
            vo.setScope(jsonObject.getString("scope"));
            vo.setTokenType(jsonObject.getString("token_type"));
        }
        return vo;
    }
}
