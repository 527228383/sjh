package com.sjh.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.sjh.test.Supplier.Car;

public class TestJava8 {

	final static String salutation = "Hello! ";
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("Google");
		list.add("Baidu");
		list.add("Taobao");
		list.add("Jingdong");
		list.add("Sina");
		TestJava8.sortUsingJava8(list);
		list.forEach( System.out :: println);
		
		// 声明类型
		MathOperation addition = (int a, int b) -> a + b;
		
		// 不用声明类型
		MathOperation subtraction = (a, b) -> a - b;
		
		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> { return a * b; };
		
		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;
		
		System.out.println("10 + 5 = " + TestJava8.operate(10, 5, addition));
		System.out.println("10 - 5 = " + TestJava8.operate(10, 5, subtraction));
		System.out.println("10 * 5 = " + TestJava8.operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + TestJava8.operate(10, 5, division));
		
		// 不用括号
		GreetingService greetingService1 = message -> 
		System.out.println("Hello " + message);
		
		// 用括号
		GreetingService greetingService2 = (message) ->
		System.out.println("Hello " + message);
		
		greetingService1.sayMessage("Google");
		greetingService2.sayMessage("Sina");
		
		// 访问外层全局变量
		GreetingService greetingService3 = message ->
		System.out.println(salutation + message);
		greetingService3.sayMessage("Tencent");
		
		// 访问外层局部变量
		final int num = 1;
		Converter<Integer, String> s = (param) ->
		System.out.println(param + num);
		s.convert(2);
	}
	
	@Test
	public void testFunctionalInterface() {
		// 构造器引用
		final Car car = Car.create( Car :: new );
		final List<Car> cars = Arrays.asList( car );
		
		// 静态方法引用：语法 Class::static_method
		cars.forEach( Car :: collide);
		
		// 特定类的任意对象的方法引用：语法Class::method
		cars.forEach( Car :: repair);
		
		//特定对象的方法引用：语法instance:method
		final Car police = Car.create( Car :: new );
		cars.forEach( police :: follow );
	}
	
	interface Converter<T1, T2> {
		void convert(int i);
	}
	
	interface MathOperation {
		int operation(int a, int b);
	}
	
	interface GreetingService {
		void sayMessage(String message);
	}
	
	private static int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
	
	private static void sortUsingJava8(List<String> list) {
		Collections.sort(list, (s1, s2) -> s1.compareTo(s2));
	}
}
