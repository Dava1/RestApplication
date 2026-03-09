package com.train.rest.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

	private static final Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);
	private static final String START_TIME_ATTR = "requestStartTimeNanos";

	@Override
	public boolean preHandle(HttpServletRequest request,
	                         HttpServletResponse response,
	                         Object handler) {
		request.setAttribute(START_TIME_ATTR, System.nanoTime());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
	                            HttpServletResponse response,
	                            Object handler,
	                            Exception ex) {
		Object startAttr = request.getAttribute(START_TIME_ATTR);
		if (!(startAttr instanceof Long startTime)) {
			return;
		}

		long durationMs = (System.nanoTime() - startTime) / 1_000_000L;
		String query = request.getQueryString();
		String uri = query == null ? request.getRequestURI() : request.getRequestURI() + "?" + query;

		if (ex == null) {
			log.info("Request: {} {} status={} durationMs={}",
					request.getMethod(), uri, response.getStatus(), durationMs);
			return;
		}

		log.error("Request failed: {} {} status={} durationMs={} error={}",
				request.getMethod(), uri, response.getStatus(), durationMs, ex.getClass().getSimpleName());
	}
}
