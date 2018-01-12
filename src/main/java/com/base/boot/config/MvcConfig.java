package com.base.boot.config;

import com.base.springmvc.RequestJsonStringMethodArgumentResolver;
import com.base.springmvc.exception.GlobalHandlerExceptionResolver;
import com.base.springmvc.views.json.JsonView;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc   //开启Spring MVC支持，若无此句，重写WebMvcConfigurerAdapter方法无效
@ComponentScan("com.dy.*.controller")
public class MvcConfig {

    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){

        return new RequestMappingHandlerMapping();
    }

    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return  viewResolver;
    }

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        adapter.setSynchronizeOnSession(true);

        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new RequestJsonStringMethodArgumentResolver());
        adapter.setCustomArgumentResolvers(argumentResolvers);

        List<HttpMessageConverter<?>> converterList = new ArrayList<>();
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();//1
        List<MediaType> mediaTypeList =new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        messageConverter.setSupportedMediaTypes(mediaTypeList);
        converterList.add(new StringHttpMessageConverter());//2
        converterList.add(messageConverter);
        adapter.setMessageConverters(converterList);

        ConfigurableWebBindingInitializer webBindingInitializer =  new ConfigurableWebBindingInitializer();
        webBindingInitializer.setConversionService(new FormattingConversionServiceFactoryBean().getObject());
        adapter.setWebBindingInitializer(webBindingInitializer);

        return adapter;
    }


    @Bean(name="beanNameViewResolver")
    public BeanNameViewResolver beanNameViewResolver(){
        BeanNameViewResolver b = new BeanNameViewResolver();
        b.setOrder(1);
        return b;
    }

    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver(){
        GlobalHandlerExceptionResolver g = new GlobalHandlerExceptionResolver();
        return g;
    }

    @Bean
    public JsonView jsonView(){
        JsonView view = new JsonView();
        return view;
    }

    @Bean
    public ServletRegistrationBean restServlet(){
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.*.*.controller");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);
        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(rest_dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.setAsyncSupported(true);
        //指定urlmapping
        registrationBean.addUrlMappings("/");
        //指定name，如果不指定默认为dispatcherServlet
        return registrationBean;
    }


    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setForceEncoding(true);
        characterEncodingFilter.setEncoding("UTF-8");
        registrationBean.setFilter(characterEncodingFilter);
        registrationBean.setName("encodingFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setDispatcherTypes(DispatcherType.FORWARD,DispatcherType.REQUEST);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean openEntityManagerInViewFilter1() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        OpenEntityManagerInViewFilter entityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        registrationBean.setFilter(entityManagerInViewFilter);
        registrationBean.setName("openEntityManagerInViewFilter");
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }



}
