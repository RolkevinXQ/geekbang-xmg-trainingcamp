package com.rolkevin.rest.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rolkevin.rest.core.DefaultResponse;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.concurrent.Future;

public class HttpPostInvocation implements Invocation {

    private final URI uri;

    private final URL url;

    private final Entity entity;

    public HttpPostInvocation(URI uri,Entity entity) {
        this.uri = uri;
        try {
            this.url = uri.toURL();
            this.entity = entity;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public Invocation property(String s, Object o) {
        return null;
    }

    @Override
    public Response invoke() {
        HttpURLConnection httpURLConnection = null;
        try {
            //URL url = new URL("http://localhost:8080/xxx.do");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(HttpMethod.POST);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.write(new ObjectMapper().writeValueAsString(entity.getEntity()));
            writer.flush();
            writer.close();
            int statusCode = httpURLConnection.getResponseCode();

            DefaultResponse response = new DefaultResponse();
            response.setConnection(httpURLConnection);
            response.setStatus(statusCode);

        } catch (Exception e) {

        }

        return null;
    }

    @Override
    public <T> T invoke(Class<T> aClass) {
        Response response = invoke();
        return response.readEntity(aClass);
    }

    @Override
    public <T> T invoke(GenericType<T> genericType) {
        Response response = invoke();
        return response.readEntity(genericType);
    }

    @Override
    public Future<Response> submit() {
        return null;
    }

    @Override
    public <T> Future<T> submit(Class<T> aClass) {
        return null;
    }

    @Override
    public <T> Future<T> submit(GenericType<T> genericType) {
        return null;
    }

    @Override
    public <T> Future<T> submit(InvocationCallback<T> invocationCallback) {
        return null;
    }
}
