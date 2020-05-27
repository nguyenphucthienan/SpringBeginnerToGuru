package com.nguyenphucthienan.springmvcrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String CATEGORY_TAG = "Categories";
    public static final String CUSTOMER_TAG = "Customers";
    public static final String VENDOR_TAG = "Vendors";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(apiInfo())
                .tags(new Tag(CATEGORY_TAG, "Category Endpoints"),
                        new Tag(CUSTOMER_TAG, "Customer Endpoints"),
                        new Tag(VENDOR_TAG, "Vendors Endpoints"));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Nguyen Phuc Thien An",
                "https://github.com/nguyenphucthienan",
                "npta97@gmail.com");

        return new ApiInfo(
                "Spring Framework",
                "Spring Framework 5: Beginner to Guru",
                "1.0",
                "Terms of Service",
                contact,
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
