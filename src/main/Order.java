package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Order {
	String orderName;
	String goodsName;
	int goodsEA;
	int goodsPrice;
	String orderTime;

	Scanner sc = new Scanner(System.in);

	public Order() {
		super();
	}

	// 1. 상품 주문
	public void goodsOrder(String orderName, String goodsName, int goodsEA, int goodsPrice) throws IOException {
		LocalDateTime realTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDate = realTime.format(formatter);
		int orderNum = getOrderNum()+1;
		
		String txtDate = String.format("주문번호: %d / 고객명: %s / 제품명: %s / 주문수량: %d / 가격: %d / 주문일시: %s", orderNum,
				orderName, goodsName, goodsEA, goodsPrice, formatDate);

		FileWriter fw = new FileWriter("order.txt", true);
		fw.write(txtDate + "\n");
		fw.flush();
		fw.close();

		System.out.println("주문이 완료되었습니다.");
		System.out.println();
	}

	// 2. 전체 주문 이력 조회
	public void allOrderCheck() throws IOException {
		Reader fis = new FileReader("order.txt");

		while (true) {
			int i = fis.read();
			if (i == -1) {
				System.out.println("조회 종료");
				break;
			}
			System.out.print((char) i);
		}
		System.out.println();
		fis.close();
	}

	// 3. 고객 주문 이력 조회 및 주문 건수, 총 주문 금액 계산
	public void custormerNameOrderCheck(String name) throws IOException {
		FileReader fr = new FileReader("order.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		int orderCount = 0;
		int orderPrice = 0;

		while ((line = br.readLine()) != null) {
			if (line.contains("고객명: " + name)) {
				orderCount++;

				String[] split = line.split("/");
				String[] arr1 = split[4].split(":");
				String[] arr2 = split[3].split(":");

				orderPrice = orderPrice + (Integer.parseInt(arr1[1].trim()) * Integer.parseInt(arr2[1].trim()));
			}
		}
		System.out.println("전체 주문 건수:" + orderCount);
		System.out.println("전체 주문 금액:" + orderPrice);
		System.out.println();
		fr.close();
		br.close();
	}

	// 4. 날짜 주문 이력 조회
	public void dateDayInquiry(String date) throws IOException {

		FileReader fr = new FileReader("order.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;

		while ((line = br.readLine()) != null) {
			if (line.contains("주문일시: " + date)) {
				System.out.println(line);
			}
		}
		fr.close();
		br.close();
	}

	// 주문번호 증가 메소드
	public int getOrderNum() throws IOException {
		FileReader fr = new FileReader("order.txt");
		BufferedReader br = new BufferedReader(fr);
		String line;
		int orderNum = 0;

		while ((line = br.readLine()) != null) {
			String[] arr1 = line.split("/");
			String[] arr2 = arr1[0].split(":");

			orderNum = Integer.parseInt(arr2[1].trim());
		}
		fr.close();
		br.close();
		return orderNum;
	}
}
