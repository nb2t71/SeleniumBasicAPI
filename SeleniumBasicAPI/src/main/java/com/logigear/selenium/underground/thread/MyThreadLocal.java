package com.logigear.selenium.underground.thread;

public class MyThreadLocal extends Thread{
	public static final ThreadLocal userThreadLocal = new ThreadLocal();

	public static void set(Object userObject) {
		userThreadLocal.set(userObject);
	}
	
	public static void unset() {
		userThreadLocal.remove();
	}
	
	public static Object get() {
		return userThreadLocal.get();
	}
	
	
}
