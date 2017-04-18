package com.automation.runner;

public class DeadLockDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				DeadLockDemo.method1();
				// DeadLockDemo.method2();
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				DeadLockDemo.method1();
				// DeadLockDemo.method2();
			}
		});

		t1.start();
		t2.start();

		// t1.interrupt();
		// t2.interrupt();
	}

	/*
	 * This method request two locks, first String and then Integer
	 */
	public static void method1() {

		synchronized (String.class) {
			System.out.println("Aquired lock on String.class object");

			synchronized (Integer.class) {
				System.out.println("Aquired lock on Integer.class object");
			}
		}
	}

	/*
	 * This method also requests same two lock but in exactly Opposite order
	 * i.e. first Integer and then String. This creates potential deadlock, if
	 * one thread holds String lock and other holds Integer lock and they wait
	 * for each other, forever.
	 */
	public static void method2() {

		synchronized (String.class) {
			System.out.println("Aquired lock on String.class object");

			synchronized (Integer.class) {
				System.out.println("Aquired lock on Integer.class object");
			}
		}
	}

}