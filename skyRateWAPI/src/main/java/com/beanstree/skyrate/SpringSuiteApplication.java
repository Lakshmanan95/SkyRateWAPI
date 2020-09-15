package com.beanstree.skyrate;

import javax.jms.Queue;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ComponentScan({"com.skyrate.*","com.beanstree.*"})
@EnableJpaRepositories({"com.skyrate.*","com.beanstree.*"})
@EntityScan({"com.skyrate.*","com.beanstree.*"})
@EnableAutoConfiguration
@EnableJms
@ImportResource("classpath*:/si-config.xml")
public class SpringSuiteApplication {

//	@Bean
//	public Queue queue() {
//		return new ActiveMQQueue("email.queue7");
//	}
	public static void main(String[] args)  throws Exception {
		SpringApplication.run(SpringSuiteApplication.class, args);
	}
}
