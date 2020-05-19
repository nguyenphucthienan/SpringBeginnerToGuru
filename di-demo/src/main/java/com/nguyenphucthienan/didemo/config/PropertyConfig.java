package com.nguyenphucthienan.didemo.config;

import com.nguyenphucthienan.didemo.beans.FakeDataSource;
import com.nguyenphucthienan.didemo.beans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.PropertySource;
// import org.springframework.context.annotation.PropertySources;
// import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
// @PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})
// @PropertySources({
//         @PropertySource("classpath:datasource.properties"),
//         @PropertySource("classpath:jms.properties")
// })
public class PropertyConfig {

    // @Autowired
    // private Environment env;

    @Value("${npta.db.username}")
    private String username;

    @Value("${npta.db.password}")
    private String password;

    @Value("${npta.db.url}")
    private String url;

    @Value("${npta.jms.username}")
    private String jmsUsername;

    @Value("${npta.jms.password}")
    private String jmsPassword;

    @Value("${npta.jms.url}")
    private String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource() {
        FakeDataSource dataSource = new FakeDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        // dataSource.setUsername(env.getProperty("TEST_USERNAME"));
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public FakeJmsBroker fakeJmsBroker() {
        FakeJmsBroker jmsBroker = new FakeJmsBroker();
        jmsBroker.setUsername(jmsUsername);
        jmsBroker.setPassword(jmsPassword);
        jmsBroker.setUrl(jmsUrl);
        return jmsBroker;
    }

    // @Bean
    // public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    //     return new PropertySourcesPlaceholderConfigurer();
    // }
}
