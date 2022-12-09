package com.home.springshope.Config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConf {

    public static final String NAME_SPACE = "http://home.com/springshope/ws/greeting";
    public static final String NAME_SPACE_TWO = "http://home.com/springshope/ws/products.xsd";


    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {

        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/ws/*");


    }


    @Bean(name = "greeting")
    public DefaultWsdl11Definition defaultWsdl11Definition() {

        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("GreetingPort");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setTargetNamespace(NAME_SPACE);
        defaultWsdl11Definition.setSchema(xsdGreetingSchema());


        return defaultWsdl11Definition;


    }

    @Bean
    public XsdSchema xsdGreetingSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/greeting.xsd"));
    }


    @Bean(name = "products")
    public DefaultWsdl11Definition productsWsdlDefinition() {

        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();

        defaultWsdl11Definition.setPortTypeName("ProductsPort");
        defaultWsdl11Definition.setLocationUri("/ws");
        defaultWsdl11Definition.setTargetNamespace(NAME_SPACE_TWO);
        defaultWsdl11Definition.setSchema(xsdProductSchema());
        return defaultWsdl11Definition;

    }

    @Bean
    public XsdSchema xsdProductSchema() {
        return new SimpleXsdSchema(new ClassPathResource("ws/products.xsd"));
    }

}
