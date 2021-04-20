package org.rolkevin.thirdlogin.controller;

import org.rolkevin.thirdlogin.oauth2.GiteeOauth;
import org.rolkevin.thirdlogin.service.GiteeOauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private GiteeOauth giteeOauth;

    @Autowired
    GiteeOauthService giteeOauthService;

    @GetMapping("/")
    public String index(HttpServletRequest request){
        request.getSession().setAttribute("clientInfo",giteeOauth.getClientInfo());
        Cookie cookies [] = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
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
    public String callBack(@RequestParam(name = "code") String code){

        return "index";
    }
}
