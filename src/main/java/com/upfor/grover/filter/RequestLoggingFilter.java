package com.upfor.grover.filter;

import lombok.Setter;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.UUID;

public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

    // configures
    protected String beforeMessagePrefix = "BeforeRequest [";
    protected String beforeMessageSuffix = "]";
    protected String afterMessagePrefix = "AfterRequest [";
    protected String afterMessageSuffix = "]";
    protected String requestIdHeader = "request-id";

    /**
     *
     */
    @Setter
    protected boolean includeResponseBody = false;
    @Setter
    protected boolean includeElapsedTime = true;

    // MDC Keys
    protected String requestId = "requestId";
    protected String startTime = "startTime";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean isFirstRequest = !this.isAsyncDispatch(request);
        HttpServletRequest requestToUse = request;
        if (this.isIncludePayload() && isFirstRequest && !(request instanceof ContentCachingRequestWrapper)) {
            requestToUse = new ContentCachingRequestWrapper(request, this.getMaxPayloadLength());
        }

        HttpServletResponse responseToUse = response;
        if (this.includeResponseBody && !(response instanceof ContentCachingResponseWrapper)) {
            responseToUse = new ContentCachingResponseWrapper(response);
        }

        // http request id
        String requestId = request.getHeader(this.requestId);
        if (requestId == null) {
            requestId = UUID.randomUUID().toString();
        }
        MDC.put(this.requestId, requestId);

        // http request start time
        MDC.put(this.startTime, String.valueOf(System.currentTimeMillis()));

        boolean shouldLog = this.shouldLog(requestToUse);
        if (shouldLog && isFirstRequest) {
            this.beforeRequest(requestToUse, this.getBeforeMessage(requestToUse));
        }

        try {
            filterChain.doFilter(requestToUse, responseToUse);
        } finally {
            responseToUse.setHeader(this.requestIdHeader, requestId);
            if (shouldLog && !this.isAsyncStarted(requestToUse)) {
                this.afterRequest(requestToUse, this.getAfterMessage(requestToUse, responseToUse));
            }

            MDC.clear();
        }
    }

    private String getBeforeMessage(HttpServletRequest request) {
        return this.beforeMessagePrefix + this.createMessage(request, null) + this.beforeMessageSuffix;
    }

    private String getAfterMessage(HttpServletRequest request, HttpServletResponse response) {
        return this.afterMessagePrefix + this.createMessage(request, response) + this.afterMessageSuffix;
    }

    protected String createMessage(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder msg = new StringBuilder();
        msg.append(request.getMethod()).append(" ");
        msg.append(request.getRequestURI());

        if (this.includeElapsedTime) {
            msg.append(", elapsedTime=")
                    .append(System.currentTimeMillis() - Long.parseLong(MDC.get(this.startTime)))
                    .append("ms");
        }

        String payload;
        if (this.isIncludeQueryString()) {
            payload = request.getQueryString();
            if (payload != null) {
                msg.append('?').append(payload);
            }
        }

        String header;
        if (this.isIncludeClientInfo()) {
            payload = request.getRemoteAddr();
            if (StringUtils.hasLength(payload)) {
                msg.append(", client=").append(payload);
            }

            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(", session=").append(session.getId());
            }

            header = request.getRemoteUser();
            if (header != null) {
                msg.append(", user=").append(header);
            }
        }

        if (this.isIncludeHeaders()) {
            HttpHeaders headers = (new ServletServerHttpRequest(request)).getHeaders();
            if (this.getHeaderPredicate() != null) {
                Enumeration<String> names = request.getHeaderNames();

                while (names.hasMoreElements()) {
                    header = names.nextElement();
                    if (!this.getHeaderPredicate().test(header)) {
                        headers.set(header, "masked");
                    }
                }
            }

            msg.append(", headers=").append(headers);
        }

        if (this.isIncludePayload()) {
            payload = this.getMessagePayload(request);
            if (payload != null) {
                msg.append(", payload=").append(payload);
            }
        }

        if (this.includeResponseBody) {
            ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
            if (wrapper != null) {
                try {
                    msg.append(", responseBody=").append(new String(wrapper.getContentAsByteArray()));
                    wrapper.copyBodyToResponse();
                } catch (Exception ignore) {
                }
            }
        }

        return msg.toString();
    }

    protected void beforeRequest(HttpServletRequest request, String message) {
        this.logger.debug(message);
    }

    protected void afterRequest(HttpServletRequest request, String message) {
        this.logger.info(message);
    }

}
