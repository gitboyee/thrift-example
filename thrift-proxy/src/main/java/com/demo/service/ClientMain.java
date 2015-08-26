package com.demo.service;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// simple();
		spring();
	}

	public static void spring() {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-thrift-client.xml");
			UserService.Iface userService = (UserService.Iface) context.getBean("userService");
			Thread.sleep(5000);
			for (int i = 0; i < 2; i++) {
				TThread t = new TThread(userService);
				t.start();
			}
			Thread.sleep(3000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class TThread extends Thread {
		UserService.Iface userService;

		TThread(UserService.Iface service) {
			userService = service;
		}

		public void run() {
			try{
				for (int i = 0; i < 10; i++) {
					User user = new User();
					user.setId(1010101);
					user.setName("zhangsan..." + i);
					userService.show(user);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public static void simple() {
		try {
			TTransport transport = new TSocket("localhost", 9090);
			TProtocol protocol = new TBinaryProtocol(transport);
			UserService.Client client = new UserService.Client(protocol);
			transport.open();
			User user = new User();
			user.setId(1010101);
			user.setName("zhangsan...");
			client.show(user);
			// //
			Thread.sleep(3000);
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
