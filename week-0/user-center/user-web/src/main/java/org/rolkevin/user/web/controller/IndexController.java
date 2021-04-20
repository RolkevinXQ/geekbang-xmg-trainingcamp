package org.rolkevin.user.web.controller;



import org.rolkevin.framework.mvc.controller.PageController;
import org.rolkevin.user.domain.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("")
public class IndexController implements PageController {


    public String index(HttpServletRequest request){
        Cookie cookies [] = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = new User();// userMapper.selectUserByToken(token);
                    user.setName("XQ");
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        return "home.jsp";
    }

    /**
     * @param request  HTTP 请求
     * @param response HTTP 相应
     * @return 视图地址路径
     * @throws Throwable 异常发生时
     */
    @Override
    @POST
    @GET
    @Path("/")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return index(request);
    }
}
