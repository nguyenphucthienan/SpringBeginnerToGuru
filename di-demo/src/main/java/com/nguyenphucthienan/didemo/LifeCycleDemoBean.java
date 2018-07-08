package com.nguyenphucthienan.didemo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware {
    public LifeCycleDemoBean() {
        System.out.println("## 1. LifeCycleBean constructor");
    }

    public void beforeInit() {
        System.out.println("## 5. beforeInit - called by BeanPostProcessor");
    }

    public void afterInit() {
        System.out.println("## 8. afterInit - called by BeanPostProcessor");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("## 7. afterPropertiesSet");
    }

    @Override
    public void destroy() {
        System.out.println("## 10. destroy. The lifecycle Bean has been terminated");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("## 2. setBeanName. Bean name is: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## 3. setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## 4. setApplicationContext");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## 6. @PostConstruct");
    }

    @PreDestroy
    public void PreDestroy() {
        System.out.println("## 9. @PreDestroy");
    }
}
