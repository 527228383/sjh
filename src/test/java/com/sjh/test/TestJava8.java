package com.sjh.test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.sjh.login.entity.User;
import com.sjh.test.Supplier.Car;

public class TestJava8 {

	final static String salutation = "Hello! ";
	
	public static void main(String[] args) {
		String str = "{\"nodes\":[{\"name\":\"本计划\",\"nodeType\":0,\"nodeId\":\"flow-chart-node01536286160393\",\"positionX\":300,\"positionY\":20,\"className\":\"node-start\",\"removable\":false},{\"nodeType\":1,\"name\":\"债权直接转让  (名称:XTJGBU 金额:123万元)\",\"nodeId\":\"flow-chart-node11536286166329\",\"positionX\":30,\"positionY\":100,\"className\":\"node-process\",\"removable\":true}],\"connections\":[{\"connectionId\":\"con_13\",\"pageSourceId\":\"flow-chart-node01536286160393\",\"pageTargetId\":\"flow-chart-node11536286166329\",\"sourceEndPointUuid\":\"flow-chart-node01536286160393Bottom\",\"targetEndPointUuid\":\"flow-chart-node11536286166329Top\"}]}";
		
		
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
		
		System.out.println("#saf".indexOf("#"));
	}
	
	@Test
	public void testListDistinct() {
		List<User> list = new ArrayList<>();
		list.add(User.builder().userName("zhangsan").passWord("123456").build());
		list.add(User.builder().userName("zhangsan").passWord("123456").build());
		list.add(User.builder().userName("zhangsan").passWord("1234567").build());
		list.add(User.builder().userName("lisi").passWord("123456").build());
		list.add(User.builder().userName("lisi").passWord("123456").build());
		list.add(User.builder().userName("wangwu").passWord("123456").build());
		list.add(User.builder().userName("wangwu").passWord("123456").build());
		
		List<User> distinctList = new ArrayList<>();
		
		for (User u : list) {
			
			boolean exists = false;
			
			for (User u1 : distinctList) {
				if (StringUtils.equals(u.getUserName(), u1.getUserName()) 
						&& StringUtils.equals(u.getPassWord(), u1.getPassWord())) {
					exists = true;
				}
			}
			
			if (!exists) {
				distinctList.add(u);
			}
		}
		System.out.println(distinctList);
	}
	
	@Test
	public void testFunctionalInterface() {
		// 构造器引用
		final Car car = Car.create( Car :: new );
		final List<Car> cars = Arrays.asList( car );
		
		// 静态方法引用：语法 Class::static_method
		cars.forEach( Car :: collide );
		
		// 特定类的任意对象的方法引用：语法Class::method
		cars.forEach( Car :: repair );
		
		//特定对象的方法引用：语法instance:method
		final Car police = Car.create( Car :: new );
		cars.forEach( police :: follow );
	}
	
	@Test
	public void testStream() {
		List<String> list = Arrays.asList("abc","","bc","efg","abcd","","jkl");
		long count = list.stream().filter( string -> string.isEmpty()).count();
		System.out.println("==>stream count:" + count);
		count = list.parallelStream().filter( string -> string.isEmpty()).count();
		System.out.println("==>parallelStram count:" + count);
		List<String> filtered = list.stream().filter(string -> !string.isEmpty()).sorted().collect(Collectors.toList());
		System.out.println("==>Collectors toList:" + filtered);
		String mergedString = list.stream().filter( string -> !string.isEmpty()).collect(Collectors.joining(","));
		System.out.println("==>Collectors joining:" + mergedString);
		
		Random random = new Random();
		System.out.println("==>Random forEach:");
		random.ints().limit(10).forEach( System.out :: println);
		System.out.println("==>Random sorted forEach:");
		random.ints().limit(10).sorted().forEach( System.out :: println);
		
		System.out.println("==>map forEach:");
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		List<Integer> squaresList = numbers.stream().map( i -> i * i).distinct().collect(Collectors.toList());
		squaresList.forEach( System.out :: println);
		IntSummaryStatistics summaryStatistics = numbers.stream().mapToInt( x -> x).summaryStatistics();
		System.out.println("==>列表中最大的数:" + summaryStatistics.getMax());
		System.out.println("==>列表中最小的数:" + summaryStatistics.getMin());
		System.out.println("==>所有数之和:" + summaryStatistics.getSum());
		System.out.println("==>平均数:" + summaryStatistics.getAverage());
	}
	
	@Test
	public void testOptional() {
		Integer value1 = null;
		Integer value2 = new Integer(10);
		// Optional.ofNullable - 允许传递为 null 参数
		Optional<Integer> a = Optional.ofNullable(value1);
		// Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
		Optional<Integer> b = Optional.of(value2);
		System.out.println(sum(a, b));
	}
	
	@Test
	public void testLocalDateTime() {
		// 本地化日期时间API
		// 获取当前时间
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("当前时间:" + currentTime);
		
		LocalDate date1 = currentTime.toLocalDate();
		System.out.println("date1: " + date1);
		
		int year = currentTime.getYear();
		Month month = currentTime.getMonth();
		int day = currentTime.getDayOfMonth();
		int hour = currentTime.getHour();
		int minute = currentTime.getMinute();
		int second = currentTime.getSecond();
		System.out.println("年: " + year + ",月: " + month + ",日: " + day + ",时: " + hour + ",分: " + minute + ",秒: " + second);
		
		LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2018);
		System.out.println("date2: " + date2);
		
		LocalDate date3 = LocalDate.of(2018, Month.DECEMBER, 12);
		System.out.println("date3: " + date3);
		
		LocalTime date4 = LocalTime.of(22, 15);
		System.out.println("date4: " + date4);
		
		LocalTime date5 = LocalTime.parse("16:21:36");
		System.out.println("date5: " + date5);
	}
	
	@Test
	public void testZonedDateTime() {
		// 时区的日期时间API
		// 获取当前时间
		ZonedDateTime date1 = ZonedDateTime.parse("2018-12-03T16:15:30+05:30[Asia/Shanghai]");
		System.out.println("date1: " + date1);
		ZoneId id = ZoneId.of("Europe/Paris");
		System.out.println("ZoneId: " + id);
		
		ZoneId currentZone = ZoneId.systemDefault();
		System.out.println("当前时区: " + currentZone);
		
	}
	
	@Test
	public void testBase64() {
		try {
			// 使用基本编码
			String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
			System.out.println("Base64 编码字符串 (基本):" + base64encodedString);
			
			// 解码
			byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
			System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
			base64encodedString = Base64.getUrlEncoder().encodeToString("TutorialsPoint?java8".getBytes("utf-8"));
			System.out.println("Base64 编码字符串(URL): " + base64encodedString);
			
			StringBuilder stringBuilder = new StringBuilder();
			
			for (int i = 0; i < 10; i++) {
				stringBuilder.append(UUID.randomUUID().toString());
			}
			
			byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
			String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
			System.out.println("Base64 编码字符串(MIME): " + mimeEncodedString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public Integer sum(Optional<Integer> a, Optional<Integer> b) {
		// isPresent() 判断值是否存在
		System.out.println("第一个参数值存在:" + a.isPresent());
		System.out.println("第二个参数值存在:" + b.isPresent());
		
		// orElse() 如果值存在，返回它，否则返回默认值
		Integer value1 = a.orElse(new Integer(0));
		Integer value2 = b.get();
		return value1 + value2;
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
