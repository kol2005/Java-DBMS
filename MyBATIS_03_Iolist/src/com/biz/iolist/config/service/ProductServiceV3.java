package com.biz.iolist.config.service;

import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {

	public void menu() {
		System.out.println("==============================");
		System.out.println("대한쇼핑몰 상품관리 시스템v3");
		System.out.println("==============================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.끝");
		System.out.print("업무선택 >>");
		String strMenu = scanner.nextLine();
	}
	
	/*
	 * 상품정보들을 입력받아서 insert 수행
	 * 상품코드를 입력받아서
	 * 있으면 닷 ㅣ입력하도록
	 * 없으면 다음으로 진행
	 */
	public void insertProduct() {
		while(true) {
			System.out.print("상품이름(-Q:입력중단) >>");
			String strPname = scanner.nextLine();
			if(strPname.equals("-Q"))break;
			if(strPname.trim().length() < 1) {
				System.out.println("상품이름은 반드시 입력해야함");
				continue;
			}
			System.out.print("매입단가(-Q:입력중단) >>");
			String striprice = scanner.nextLine();
			if(striprice.equals("-Q"))break;
			if(striprice.trim().length() < 1) {
				System.out.println("매입단가는 반드시 입력해야함");
				continue;
			}
			System.out.print("매출단가(-Q:입력중단) >>");
			String stroprice = scanner.nextLine();
			if(stroprice.equals("-Q"))break;
			if(stroprice.trim().length() < 1) {
				System.out.println("매출단가는 반드시 입력해야함");
				continue;
			}
			
			int intiprice = Integer.valueOf(striprice);
			int intoprice = Integer.valueOf(stroprice);
			
			ProductDTO proDTO = ProductDTO.builder()
					.p_name(strPname)
					.p_iprice(intiprice)
					.p_oprice(intoprice)
					.build();
			
		}
		
	}
	
	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {
		
	}
}
