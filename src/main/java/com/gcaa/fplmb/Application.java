package com.gcaa.fplmb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
/*@EnableJpaRepositories*/
@ComponentScan(basePackages="com.gcaa.fplmb")
@ImportResource({/*"classpath:\\META-INF\\spring\\jms\\jms-context.xml"*//*, "classpath:\\META-INF\\spring\\app-datasource.xml"})//{"classpath*:applicationContext.xml"*/})/*@ContextConfiguration(locations = {"classpath:\\META-INF\\spring\\jms\\jms-context.xml"})*/
public class Application{

	
    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    	
/*    	for (String string : context.getBeanDefinitionNames()) {
			System.out.println(string);
		}*/
    }

    
}
