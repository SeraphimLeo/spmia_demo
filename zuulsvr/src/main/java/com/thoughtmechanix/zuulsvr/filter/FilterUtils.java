package com.thoughtmechanix.zuulsvr.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

/**
 * @author LucasLiu
 * @date 2021/10/22
 */
@Component
public class FilterUtils {

    public static final String PRE_FILTER_TYPE = "pre";
    public static final String POST_FILTER_TYPE = "post";
    public static final String ROUTE_FILTER_TYPE = "route";


    public static final String CORRELATION_ID = "tmx-correlation-id";

    public String getCorrelationId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.getRequest().getHeader(CORRELATION_ID) != null) {
            return requestContext.getRequest().getHeader(CORRELATION_ID);
        } else {
            return requestContext.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext requestContext = RequestContext.getCurrentContext();
        requestContext.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    public String getServiceId() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if (requestContext.get("serviceId") == null)
            return "";
        else
            return requestContext.get("serviceId").toString();
    }

}
