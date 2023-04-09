package com.nhnacademy.notice_board.filter;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebFilter(filterName = "visitFilter", urlPatterns = {
        "/post/view.do",
        "/user/view.do"
})
public class VisitFilter implements Filter {
    private File totalCountFile;
    private long count;

    @SneakyThrows
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);

        ServletContext servletContext = filterConfig.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/count");
        totalCountFile = new File(realPath);

        if (!totalCountFile.isFile()) {
            servletContext.setAttribute("count", 0L);
            log.error("파일 찾지 못 함");
            return;
        }

        long value = 0;

        try (BufferedReader input = new BufferedReader(new FileReader(totalCountFile, StandardCharsets.UTF_8))) {
            String line;
            while ((line = input.readLine()) != null) {
                value = Long.parseLong(line);
            }
        }

        count = value;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("nowCount: {}", ++count);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        try (BufferedWriter output = new BufferedWriter(
                new FileWriter(totalCountFile, StandardCharsets.UTF_8, false))) {
            output.write(String.valueOf(count));
            output.flush();
        } catch (IOException e) {
            log.error("파일 작성 불가");
        }

        Filter.super.destroy();
    }
}
