package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2{
	
	/*
	 * 키보드에서 정보를 입력받아 DB에 추가하기
	 * 1. 거래처코드 : 자동생성
	 * 		기존 코드가 있으면 추가 금지
	 * 2. 상호는 같은 상호가 있더라도 
	 * 		대표자명이 다르면 입력 가능하도록
	 * 
	 */
	@Override
	public void deptInsert() {
		System.out.println("===========================");
		System.out.println("거래처 신규등록");
		String strDCode;
		List<DeptDTO> deptList = null;
		
		while(true) {
			System.out.print("거래처코드(Enter:자동생성 -Q:quit)");
			strDCode = scanner.nextLine();
			if(strDCode.equals("-Q"))break;
			if(strDCode.trim().isEmpty()) {
				String strTMDCode = deptDao.getMaxDCode();
				
				int intDCode = Integer.valueOf(strTMDCode.substring(1));
				intDCode ++;
				strDCode = strTMDCode.substring(0, 1);
				strDCode += String.format("%04d", intDCode);
				
				System.out.println("생성된 코드 : " + strDCode);
				System.out.print("사용하시겠습니까? (Yes/No)");
				String YesNo = scanner.nextLine();
				if(YesNo.trim().isEmpty())break;
				else continue;
			}
			if(strDCode.length() != 5) {
				System.out.println("거래처코드 길이가 맞지않음(5칸)");
						continue;
			}
			if(!strDCode.substring(0,1).equals("D")) {
				System.out.println("거래처코드는 첫글자가 'D' 로 시작해야함");
				continue;	
			}
			if(deptDao.findById(strDCode) != null) {
			System.out.println("중복된 코드값");
			continue;
			}	
			try {
				Integer.valueOf(strDCode.substring(1));
			} catch (Exception e) {
				System.out.println("코드의 2번째 이후는 숫자만 올수있음");
				continue;
			}
			break;
		}
		if(strDCode.equals("-Q"))return;
		String strDName;
		String strDCeo = null;
		
		while(true) {
			System.out.print("상호(-Q:quit) : >>");
			strDName = scanner.nextLine();
			if(strDName.equals("-Q"))break;
			if(strDName.trim().isEmpty()) {
				System.out.println("상호명은 반드시 입력해야함");
				continue;
			}
			deptList = deptDao.findByName(strDName);
			if(deptList != null) {
				this.viewList(deptList);
				System.out.println("사용하시겠습니까?(Enter:사용/No:다시입력)");
				String yesNO = scanner.nextLine();
				if(!yesNO.trim().isEmpty())continue;
			}
			System.out.print("대표자(-Q:quit) : >>");
			strDCeo = scanner.nextLine();
			if(strDCeo.equals("-Q"))break;
			if(strDCeo.trim().isEmpty()) {
				System.out.println("대표자는 반드시 입력해야함");
				continue;
			}
			deptList = deptDao.findByCEO(strDCeo);
			if(deptList != null) {
				this.viewList(deptList);
				System.out.println("사용하시겠습니까?(Enter:사용/No:다시입력)");
				String yesNO = scanner.nextLine();
				if(yesNO.trim().isEmpty())break;
				continue;
			}
			break;
		}
		System.out.print("전화(-Q:quit) >>");
		String strDTel = scanner.nextLine();
		if(strDTel.equals("-Q"))return;
		
		System.out.print("주소(-Q:qtui) >>");
		String strDAddr = scanner.nextLine();
		if(strDAddr.equals("-Q"))return;
		
		DeptDTO deptDTO = DeptDTO.builder()
				.d_code(strDCode)
				.d_name(strDName)
				.d_ceo(strDCeo)
				.d_tel(strDTel)
				.d_addr(strDAddr)
				.build();
		
		int ret = deptDao.insert(deptDTO);
		if(ret > 0) {
			System.out.println("거래처 등록 완료");
		}else {
			System.out.println("거래처 등록 실패!!!");
		}
		
	}
	
}
