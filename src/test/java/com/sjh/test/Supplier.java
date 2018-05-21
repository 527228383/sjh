package com.sjh.test;

/**
 * 函数式接口，有且只有一个抽象方法
 * 但是可以有多个非抽象方法
 * @author ex-sujh
 * @date 2018年5月21日14:29:50
 * @param <T>
 */
@FunctionalInterface
public interface Supplier<T> {
	
	// 抽象方法
	T get();
	
	// java.lang.Object 中的方法不是抽象方法
	boolean equals(Object var1);
	
	// default 方法不是抽象方法
	default void defaultMethod() {
	}
	
	// static 方法不是抽象方法
	static void staticMethod() {
	}
	
	class Car {
		public static Car create(final Supplier<Car> supplier) {
			return supplier.get();
		}
		
		public static void collide(final Car car) {
			System.out.println("collide " + car.toString());
		}
		
		public void follow(Car car) {
			System.out.println("follow " + car.toString());
		}
		
		public void repair() {
			System.out.println("repair " + this.toString());
		}
	}
}


