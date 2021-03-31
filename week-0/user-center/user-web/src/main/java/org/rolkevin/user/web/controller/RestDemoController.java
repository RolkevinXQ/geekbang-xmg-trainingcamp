package org.rolkevin.user.web.controller;

import org.rolkevin.framework.mvc.controller.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Path("/rest")
public class RestDemoController implements RestController {

    @POST
    @Path("/apply")
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        ServletInputStream inputStream = request.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer body = new StringBuffer();
        String temp = "";
        while ((temp = bufferedReader.readLine()) != null){
            body.append(temp);
        }
        return body;
    }
}
