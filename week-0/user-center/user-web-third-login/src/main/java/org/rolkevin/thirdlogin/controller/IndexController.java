package org.rolkevin.thirdlogin.controller;

import org.rolkevin.thirdlogin.domain.AccessToken;
import org.rolkevin.thirdlogin.domain.OauthUserInfo;
import org.rolkevin.thirdlogin.oauth2.GiteeOauth;
import org.rolkevin.thirdlogin.service.GiteeOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    private GiteeOauth giteeOauth;

    @Autowired
    GiteeOauthService giteeOauthService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        request.getSession().setAttribute("authorize",giteeOauth.authorize());
        Cookie cookies [] = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    OauthUserInfo userInfo = giteeOauth.getOauthUserInfo();
                    if (null != userInfo){
                        request.getSession().setAttribute("user", userInfo);
                    }
//                    User user = new User();// userMapper.selectUserByToken(token);
//                    user.setName("XQ");
//                    if (user != null) {
//                        request.getSession().setAttribute("user", user);
//                    }
                    break;
                }
            }
        }
        return "index";
    }

    @GetMapping("/authcallback")
    public String callBack(@RequestParam(name = "code") String code,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //String accessToken = giteeOauthService.getAccessToken(code);
        String accessToken = giteeOauthService.getAccessToken1(code);
        OauthUserInfo userInfo = giteeOauthService.getUserInfo(accessToken);
        if(userInfo!=null){
            giteeOauth.setOauthUserInfo(userInfo);
            setCookie(response,accessToken);
            //response.addCookie(new Cookie("token",token1));
            //request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    private void setCookie(HttpServletResponse response,String value){
        response.addCookie(new Cookie("token",value));
    }
}
