package com.nguyenphucthienan.didemo.config;

import com.nguyenphucthienan.didemo.examplebeans.FakeDataSource;
import com.nguyenphucthienan.didemo.examplebeans.FakeJmsBroker;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.context.annotation.PropertySources;
// import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
// import org.springframework.core.env.Environment;

@Configuration
// @PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
// @PropertySources({
//         @PropertySource("classpath:datasource.properties"),
//         @PropertySource("classpath:jms.properties")
// })
public class PropertyConfig {
    // @Autowired
    // Environment env;

    @Value("${npta.username}")
    String user;

    @Value("${npta.password}")
    String password;

    @Value("${npta.dburl}")
    String url;

    @Value("${npta.jms.username}")
    String jmsUsername;

    @Value("${npta.jms.password}")
    String jmsPassword;

    @Value("${npta.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(user);
        // fakeDataSource.setUsername(env.getProperty("TEST_USERNAME"));
        fakeDataSource.setPassword(password);
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker fakeJmsBroker = new FakeJmsBroker();
        fakeJmsBroker.setUsername(jmsUsername);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);
        return fakeJmsBroker;
    }

    // @Bean
    // public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }
}
