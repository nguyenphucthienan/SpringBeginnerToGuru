package com.nguyenphucthienan.didemo;

import com.nguyenphucthienan.didemo.controller.*;
import com.nguyenphucthienan.didemo.beans.FakeDataSource;
import com.nguyenphucthienan.didemo.beans.FakeJmsBroker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DiDemoApplication.class, args);

        I18nController i18nController = (I18nController) context.getBean("i18nController");
        System.out.println("I18n Bean: " + i18nController.sayHello());

        MyController myController = (MyController) context.getBean("myController");
        System.out.println("Primary Bean: " + myController.sayHello());

        System.out.println("Property: " + context.getBean(PropertyInjectedController.class).sayHello());
        System.out.println("Setter: " + context.getBean(SetterInjectedController.class).sayHello());
        System.out.println("Constructor" + context.getBean(ConstructorInjectedController.class).sayHello());

        FakeDataSource fakeDataSource = context.getBean(FakeDataSource.class);
        System.out.println("Data Source: " + fakeDataSource.getUsername());

        FakeJmsBroker fakeJmsBroker = context.getBean(FakeJmsBroker.class);
        System.out.println("JMS Broker: " + fakeJmsBroker.getUsername());
    }
}
