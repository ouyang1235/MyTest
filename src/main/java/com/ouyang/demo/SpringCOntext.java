package com.ouyang.demo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringCOntext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringCOntext.applicationContext = applicationContext;
    }

    public <T> T getBean(Class<T> clazz){
        return SpringCOntext.applicationContext.getBean(clazz);
    }
}
