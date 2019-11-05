package com.biz.addr.service;

import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrCUDServiceV1 {
	
	private AddrDao addrDao = null;
	private Scanner scanner = null;
	
	public AddrCUDServiceV1() {
		scanner = new Scanner(System.in);
		addrDao = new AddrDaoImp();
	}
	
	public void inputAddr() {
		while(true) {
			System.out.println("===========================");
			System.out.println("주소록 등록");
			System.out.println("===========================");
			String strName = null;//스코프때문에 반복문 밖에다 생성
			while(true) {
				System.out.print("1. 이름 (-Q:quit) >>");
				strName = scanner.nextLine();//문자입력
				if(strName.equals("-Q"))break;// -Q 입력하면 종료
				if(strName.isEmpty()) {//
					System.out.println("이름은 반드시 입력해야함");
					continue;//메서드 처음?으로 돌아가기
				}
				break;//종료
			}
			if(strName.equals("-Q"))break;
			
			System.out.print("2. 전화번호 (-Q:quit) >>");
			String strtel = scanner.nextLine();
			if(strtel.equals("-Q"))break;
			
			System.out.print("3. 주소 (-Q:quit) >>");
			String straddr = scanner.nextLine();
			if(straddr.equals("-Q"))break;
			
			System.out.print("4. 관계 (-Q:quit) >>");
			String strchain = scanner.nextLine();
			if(strchain.equals("-Q"))break;
			
			AddrDTO addrDTO = AddrDTO.builder()
					.NAME(strName)
					.TEL(strtel)
					.ADDR(straddr)
					.CHAIN(strchain)
					.build();
			int ret = addrDao.insert(addrDTO);
			if( ret > 0) {
				System.out.println("주소록 저장 완료");
			}else {
				System.out.println("주소록 저장 실패");
			}
			
			
		}

	}
	public void updateAddr() {
		System.out.println("====================================");
		System.out.println("주소록 정보 수정");
		System.out.println("====================================");
		System.out.print("수정할 주소록ID(-Q:quit) >>");
		String strID = scanner.nextLine();
		long lID = Long.valueOf(strID);
		
		AddrDTO addrDTO = addrDao.findById(lID);
		
		System.out.printf("변경할 주소이름(%s)",addrDTO.getNAME());
		String strName = scanner.nextLine();
		if(strName.trim().length() > 0) {
			addrDTO.setNAME(strName.trim());
		}
		System.out.printf("변경할 전화번호(%s)",addrDTO.getTEL());
		String strTel = scanner.nextLine();
		if(strTel.trim().length() > 0) {
			addrDTO.setTEL(strTel.trim());
		}
		System.out.printf("변경할 주소(%s)",addrDTO.getADDR());
		String strAddr = scanner.nextLine();
		if(strAddr.trim().length() > 0) {
			addrDTO.setADDR(strAddr.trim());
		}
		System.out.printf("변경할 관계(%s)",addrDTO.getCHAIN());
		String strChain = scanner.nextLine();
		if(strChain.trim().length() > 0) {
			addrDTO.setCHAIN(strChain.trim());
		}
		addrDao.update(addrDTO);
		
	}
	
	

}
