package org.rolkevin.user.web.controller;

import org.rolkevin.framework.mvc.controller.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/rest")
public class RestDemoController implements RestController {
    @Override
    @GET
    @POST
    @Path("/apply")
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ServletInputStream inputStream = request.getInputStream();
        
        return null;
    }
}
