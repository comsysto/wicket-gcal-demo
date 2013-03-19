package com.github.gcaldemo.application;

import org.apache.wicket.request.IExceptionMapper;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebResponse;
import org.springframework.security.oauth2.client.UserRedirectRequiredException;

/**
 * Custom exception mapper that allows to pass UserRedirectRequiredException further up the call stack and delegates
 * handling of all other exceptions to a default exception mapper.
 */
public class OAuth2ExceptionMapper implements IExceptionMapper {
    private final IExceptionMapper delegateExceptionMapper;

    public OAuth2ExceptionMapper(IExceptionMapper delegateExceptionMapper) {
        this.delegateExceptionMapper = delegateExceptionMapper;
    }

    @Override
    public IRequestHandler map(Exception e) {
        Throwable rootCause = getRootCause(e);
        if (rootCause instanceof UserRedirectRequiredException) {
            //see DefaultExceptionMapper
            Response response = RequestCycle.get().getResponse();
            if (response instanceof WebResponse) {
                // we don't want to cache an exceptional reply in the browser
                ((WebResponse)response).disableCaching();
            }
            throw ((UserRedirectRequiredException) rootCause);
        } else {
            return delegateExceptionMapper.map(e);
        }
    }

    private Throwable getRootCause(Throwable ex) {
        if (ex == null) {
            return null;
        }
        if (ex.getCause() == null) {
            return ex;
        }
        return getRootCause(ex.getCause());
    }
}
