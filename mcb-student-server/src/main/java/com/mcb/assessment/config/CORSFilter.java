package com.mcb.assessment.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;

		// Set CORS headers
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"X-Requested-With, Content-Type, Authorization, Origin, Accept, " +
						"Access-Control-Request-Method, Access-Control-Request-Headers");

		// Proceed with the rest of the filter chain
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Can be used for initialization if needed
	}

	@Override
	public void destroy() {
		// Can be used for resource cleanup if needed
	}
}
