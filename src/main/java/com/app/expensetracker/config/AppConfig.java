package com.app.expensetracker.config;

import com.app.expensetracker.interceptors.ExpenseTrackerWebInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final ExpenseTrackerWebInterceptor expenseTrackerWebInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(expenseTrackerWebInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/updateUser");
    }
}
