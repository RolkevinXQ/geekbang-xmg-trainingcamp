package org.rolkevin.user.web.controller;

import org.rolkevin.framework.mvc.controller.PageController;
import org.rolkevin.user.repository.DatabaseUserRepository;
import org.rolkevin.user.service.UserService;
import org.rolkevin.user.service.impl.UserServiceImpl;
import org.rolkevin.user.sql.DBConnectionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/user")
public class UserController implements PageController {

    private DBConnectionManager connectionManager = new DBConnectionManager();
    private DatabaseUserRepository repository = new DatabaseUserRepository(connectionManager);
    private UserService userService = new UserServiceImpl(repository);

    @Override
    @Path("/hello")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

    @POST
    @Path("/register")
    public String register(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        String userName = request.getParameter("userName");
        return "register.jsp";
    }

    @POST
    @Path("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "login-form.jsp";
    }

    @Path("/loginOut")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return null;
    }
}
