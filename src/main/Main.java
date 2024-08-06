package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		boolean onOff = true;
		Order order = new Order();

		while (onOff) {
			System.out.println("1. 상품 주문");
			System.out.println("2. 전체 주문 이력 조회");
			System.out.println("3. 고객 주문 이력 조회");
			System.out.println("4. 날짜 주문 이력 조회");
			System.out.println("5. 종료");
			System.out.println("옵션(번호) 선택:");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch (choice) {
			case 1:
				System.out.println("고객명");
				String orderName = sc.nextLine();
				System.out.println("제품명");
				String goodsName = sc.nextLine();
				System.out.println("제품수량");
				int goodsEA = sc.nextInt();
				System.out.println("제품가격");
				int goodsPrice = sc.nextInt();
				order.goodsOrder(orderName, goodsName, goodsEA, goodsPrice);
				break;
			case 2:
				order.allOrderCheck();
				break;
			case 3:
				System.out.println("고객명");
				String orderName1 = sc.nextLine();
				order.custormerNameOrderCheck(orderName1);
				break;
			case 4:
				System.out.println("날짜");
				String date= sc.nextLine();
				order.dateDayInquiry(date);
				break;
			case 5:
				onOff = false;
				break;
			default:
				System.out.println("잘못된 입력값입니다.");
				System.out.println();
			}
		}
	}

}
