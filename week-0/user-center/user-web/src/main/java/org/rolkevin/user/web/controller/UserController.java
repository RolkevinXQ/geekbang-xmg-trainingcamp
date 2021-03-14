package org.rolkevin.user.web.controller;

import org.rolkevin.framework.mvc.controller.PageController;
import org.rolkevin.user.context.ComponentContext;
import org.rolkevin.user.domain.User;
import org.rolkevin.user.response.ResponseResult;
import org.rolkevin.user.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user")
public class UserController implements PageController {


    //private DatabaseUserRepository repository = new DatabaseUserRepository();
    //private UserService userService = new UserServiceImpl(repository);

    @Resource(name = "bean/userService")
    private UserService userService ;//= ComponentContext.getInstance().getComponent("bean/userService");

    @Override
    @Path("/hello")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

    @GET
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "register1.jsp";
    }

    @GET
    @Path("/register1")
    public String register1(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "register1.jsp";
    }

    @POST
    @Path("/doregister")
    public String doregister(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        /*
        User user = new User();
        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String mobile = request.getParameter("userMobile");
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(mobile);
        if (userService.register(user)){
            request.setAttribute("user",user);
            return "welcome.jsp";
        }else{
            return "register.jsp";
        }*/

        String viewPage = "";
        User user = new User();
        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String mobile = request.getParameter("userMobile");
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhoneNumber(mobile);

        ResponseResult result = userService.registerR(user);
        if (result.getCode()<0){
            request.setAttribute("result",result);
            viewPage = "error.jsp";

        }else {
            User newUser = userService.queryUserById(user.getId());
            request.setAttribute("user",newUser);
            viewPage = "welcome.jsp";
        }
        return viewPage;

    }

    @GET
    @Path("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "login-form.jsp";
    }

    @Path("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
