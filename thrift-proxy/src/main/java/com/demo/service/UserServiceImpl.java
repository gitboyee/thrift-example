package com.demo.service;

import org.apache.thrift.TException;

public class UserServiceImpl implements UserService.Iface{

	@Override
	public void show(User user) throws TException {
		System.out.println("id:" + user.getId() + ";name" + user.getName());
		
	}
	
	
}
