import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ClearFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
        Stream<Cookie> streamCookie = cookies != null ? Arrays.stream(cookies) : Stream.empty();

        String cookieValue = streamCookie.filter(cookie -> cookie.getName().equals("hh-auth"))
                .findFirst()
                .orElse(new Cookie("empty", "")).getValue();

        if (cookieValue.length() > 10) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(401, "hhAuth should be > 10");
        }
    }

    @Override
    public void destroy() {

    }
}
