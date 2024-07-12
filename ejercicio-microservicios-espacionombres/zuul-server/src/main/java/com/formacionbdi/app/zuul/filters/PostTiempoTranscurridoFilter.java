package com.formacionbdi.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
    private static final String FILTER_TYPE = "post";
    private static final Integer FILTER_ORDER = 1;
    private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

    @Override
    public String filterType() {
        return FILTER_TYPE;
    }

    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        log.info("Entrando a post filter");
        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFin = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFin - tiempoInicio;
        log.info(String.format("Tiempo transcurrido en segundos %s seg", tiempoTranscurrido.doubleValue() / 1000.00));
        log.info(String.format("Tiempo transcurrido en milisegundos %s ms", tiempoTranscurrido));
        return null;
    }
}
