package com.zm.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulApp{
    public static void main( String[] args ){
    	SpringApplication.run(ZuulApp.class, args);
    }
    
    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
      return new PreRequestLogFilter();
    }
}
