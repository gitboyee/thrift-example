package com.demo.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServiceMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-thrift-server.xml");
			Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
