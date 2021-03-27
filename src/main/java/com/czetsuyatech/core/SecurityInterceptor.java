package com.czetsuyatech.core;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import lombok.extern.log4j.Log4j2;

@Provider
@Log4j2
public class SecurityInterceptor implements ContainerRequestFilter, ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext context) {

    log.debug("Receiving request={}", context.getRequest());

    String authorization = context.getHeaderString(HttpHeaders.AUTHORIZATION);

    log.debug("Authorization token={}", authorization);

    ThreadLocalUserHolder.set("edward");

    if ("/secret".equals(context.getUriInfo().getPath())) {
      context.abortWith(Response.accepted("forbidden!").build());
    }
  }

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext)
      throws IOException {

    ThreadLocalUserHolder.remove();
  }
}