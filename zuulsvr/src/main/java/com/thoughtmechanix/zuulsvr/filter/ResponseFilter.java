package com.thoughtmechanix.zuulsvr.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author LucasLiu
 * @date 2021/10/22
 */
//@Component
public class ResponseFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;


    @Autowired
    private FilterUtils filterUtils;

    private static final Logger logger = LoggerFactory.getLogger(ResponseFilter.class);


    @Override
    public String filterType() {
        return FilterUtils.POST_FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        logger.debug("Add the correlation id to the outbound headers.{}", filterUtils.getCorrelationId());
        requestContext.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
        logger.debug("Completing outging response for {}. ", requestContext.getRequest().getRequestURI());
        return null;
    }
}
