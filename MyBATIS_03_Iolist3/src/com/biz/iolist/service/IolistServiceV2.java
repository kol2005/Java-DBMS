package com.biz.iolist.service;

import java.util.List;

import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceV2 extends IolistServiceV1{
	
	/*
	 * 매입매출 등록
	 * 날짜 현재날짜로 자동등록
	 * 거래처검색
	 * 거래처코드입력
	 * 입력한코드 검증
	 * 
	 * 상품검색
	 * 상품코드입력
	 * 입력한코드 검증
	 * 
	 * 매입,매출구분입력
	 * 
	 * 매입,매출에 따라서
	 * 매입단가, 매출단가 가져오기(세팅)
	 * 
	 * 매입합계 또는 매출합계 계산하기
	 * 
	 * insert
	 * 
	 * 추가된 데이터 보여주기
	 */
	
	public IolistServiceV2() {
		List<IolistDTO> iolist = null;
	}
	public void selectAll(List<IolistDTO> iolist) {
		//IolistDTO iolist = iolistDao.selectAll();
		System.out.println("=================================");
		System.out.println("매입매출 리스트");
		System.out.println("=================================");
		System.out.println("상품코드\t날짜\t구분\t수량\t가격\t합계\t상품코드\t거래처코드");
		for(IolistDTO vo : iolist) {
			System.out.print(vo.getIo_seq() + "\t");
			System.out.print(vo.getIo_date() + "\t");
			System.out.print(vo.getIo_inout() + "\t");
			System.out.print(vo.getIo_qty() + "\t");
			System.out.print(vo.getIo_price() + "\t");
			System.out.print(vo.getIo_total() + "\t");
			System.out.print(vo.getIo_pcode() + "\t");
			System.out.println(vo.getIo_dcode());	
		}
		
	}
	
	public void findById() {
		
	}
	
	public void insert() {
		
	}
	public void update() {
		
	}
	public void delete() {
		
	}

	

	

}
