package net.sxbang.watchapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @description:
 * @author: Emoker
 **/
@Component
public class RoleUtils {

    static TokenStore tokenStore;
    @Autowired
    public void setTokenStore(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    public static boolean isRole(Authentication auth,String RoleKey)  {
        if (null == auth) {
            return false;
        }
        if (null == RoleKey) {
            return false;
        }
        Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>) auth.getAuthorities();
        for (GrantedAuthority role : roles) {
            if (role.getAuthority().equals(RoleKey)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMaster(Authentication auth) {
        return isRole(auth,"ROLE_MASTER");
    }
    public static boolean isCustomer(Authentication auth) {
        return isRole(auth,"ROLE_CUSTOMER");
    }
    public static boolean isStoremanager(Authentication auth) {
        return isRole(auth,"ROLE_SHOPOWNER");
    }
    public static boolean isAdmin(Authentication auth) {
        return isRole(auth,"ROLE_ADMIN");
    }

    public static Authentication getAuthentication(String accessToken)  {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
        if (null == oAuth2AccessToken) {
            return null;
        }
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(oAuth2AccessToken);
        if (null == oAuth2Authentication) {
            return null;
        }
        return oAuth2Authentication.getUserAuthentication();
    }

    public static boolean isMaster(String accessToken) {
        return isRole(getAuthentication(accessToken),"ROLE_MASTER");
    }
    public static boolean isCustomer(String accessToken) {
        return isRole(getAuthentication(accessToken),"ROLE_CUSTOMER");
    }
    public static boolean isStoremanager(String accessToken) {
        return isRole(getAuthentication(accessToken),"ROLE_SHOPOWNER");
    }
    public static boolean isAdmin(String accessToken) {
        return isRole(getAuthentication(accessToken),"ROLE_ADMIN");
    }

}
