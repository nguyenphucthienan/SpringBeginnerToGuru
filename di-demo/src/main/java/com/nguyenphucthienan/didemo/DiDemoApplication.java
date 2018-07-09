package com.nguyenphucthienan.didemo;

// import com.nguyenphucthienan.didemo.controller.ConstructorInjectedController;
// import com.nguyenphucthienan.didemo.controller.MyController;
// import com.nguyenphucthienan.didemo.controller.PropertyInjectedController;
// import com.nguyenphucthienan.didemo.controller.SetterInjectedController;
import com.nguyenphucthienan.didemo.examplebeans.FakeDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DiDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(DiDemoApplication.class, args);

        // MyController controller = (MyController) context.getBean("myController");
        // System.out.println(controller.hello());

        // System.out.println(context.getBean(PropertyInjectedController.class).sayHello());
        // System.out.println(context.getBean(SetterInjectedController.class).sayHello());
        // System.out.println(context.getBean(ConstructorInjectedController.class).sayHello());

        FakeDataSource fakeDataSource = context.getBean(FakeDataSource.class);
        System.out.println(fakeDataSource.getUsername());
    }
}
