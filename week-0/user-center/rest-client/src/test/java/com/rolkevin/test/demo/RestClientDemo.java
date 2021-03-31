package com.rolkevin.test.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolkevin.test.util.Maps;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RestClientDemo {
    static Logger logger = Logger.getLogger(RestClientDemo.class.getName());
    public static void main(String[] args) throws Exception {
        testPost();
    }

    private static void testGet(){
        Client client = ClientBuilder.newClient();
        Response response = client
                .target("http://127.0.0.1:8080/hello/world")      // WebTarget
                .request() // Invocation.Builder
                .get();                                     //  Response

        String content = response.readEntity(String.class);

        System.out.println(content);
    }

    private static void testPost() throws Exception {
        Map body = new HashMap<String,String>();
        body.put("title","testPost");
        body.put("content","XQ");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://127.0.0.1:8080/rest/apply");
        Invocation.Builder builder = target.request();
        //builder.header("","");
        String jsonBody = new ObjectMapper().writeValueAsString(body);
        logger.info("»Î≤Œ£∫"+jsonBody);
        Entity entity = Entity.json(jsonBody);
        Response response = builder.post(entity);
        logger.info("∑µªÿ£∫"+response.readEntity(String.class));

    }

}
