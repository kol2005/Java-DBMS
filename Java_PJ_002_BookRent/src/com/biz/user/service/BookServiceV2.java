package com.biz.user.service;

import java.util.List;

import com.biz.user.persistence.BookDTO;

public class BookServiceV2 extends BookServiceV1{

	protected UserServiceV1 userService = new UserServiceV1();
	protected RentBookServiceV1 rentservice = new RentBookServiceV1();

	public void booksearch() {
		this.viewNameList();
		/*
		System.out.println("========================");
		System.out.println("도서검색");
		System.out.println("========================");
		String strBCode = scanner.nextLine();
		
		List<BookDTO> bookList = null;
		if(strBCode.trim().isEmpty()) {
			bookDao.findById(strBCode);
		}else {
			//bookList = bookDao.findById(strBCode);
		}
		
		BookDTO bookDTO = new BookDTO();
		
		while(true) {
			
		
		}
		*/
	}
	protected void bookupdate() {
		System.out.println("=======================");
		System.out.println("도서정보 변경");
		System.out.println("=======================");
		//String strBCode;
		//List<BookDTO> bookList = null;
		System.out.print("도서명 검색 >> ");
		String strBName = scanner.nextLine();
			if(strBName.trim().isEmpty()) {
				this.viewAllList();
			} else {
				this.viewNameList(strBName);
			}
			
		String strBAuther = null;
		String strBComp = null;
		String strBYear = null;
		String strBIprice = null;
		String strBRprice = null;
		
		System.out.print("수정할 도서코드 >>");
		String strBCode = scanner.nextLine();
		BookDTO bookDTO = bookDao.findById(strBCode);
		System.out.println(bookDTO.toString());
		
		System.out.printf("수정할 도서명(%s) >> ",bookDTO.getB_name());
		strBName = scanner.nextLine();
		if(strBName.trim().isEmpty()) {
			bookDTO.setB_name(strBName);
		}
		System.out.printf("수정할 저자(%s) >> ",bookDTO.getB_auther());
		strBAuther = scanner.nextLine();
		if(strBAuther.trim().isEmpty()) {
			bookDTO.setB_auther(strBAuther);
		}
		System.out.printf("수정할 출판사(%s) >> ",bookDTO.getB_comp());
		strBComp = scanner.nextLine();
		if(strBComp.trim().isEmpty()) {
			bookDTO.setB_comp(strBComp);
		}
		
		System.out.printf("수정할 구입연도(%s) >> ",bookDTO.getB_year());
		int intYear = 0;
		try {
			strBYear = scanner.nextLine();
			intYear = Integer.valueOf(strBYear);
			if(strBYear.trim().isEmpty()) {
				bookDTO.setB_year(intYear);
			}
		} catch (Exception e) {
			System.out.println("구입연도는 숫자로만 입력");
		}
		
		System.out.printf("수정할 구입가격(%d) >> ",bookDTO.getB_iprice());
		int intIPrice = 0;
		try {
			strBIprice = scanner.nextLine();
			intIPrice = Integer.valueOf(strBIprice);
			if(strBIprice.trim().isEmpty()) {
				bookDTO.setB_iprice(intIPrice);
			}
		} catch (Exception e) {
			System.out.println("구입가격은 숫자로만 입력");
		}
		
		System.out.printf("수정할 대여가격(%d) >> ",bookDTO.getB_rprice());
		int intRPrice = 0;
		try {
			strBRprice = scanner.nextLine();
			intRPrice = Integer.valueOf(strBRprice);
			if(strBRprice.trim().isEmpty()) {
				bookDTO.setB_rprice(intRPrice);
			}
		} catch (Exception e) {
			System.out.println("대여가격은 숫자로만 입력");
			return;
		}

		
		int ret = bookDao.update(bookDTO);
		if(ret > 0) {
			System.out.println("도서정보 변경 완료");
		}else {
			System.out.println("도서정보 변경 실패!!");
		}
			
		
	}
	public void bookinsert() {
		System.out.println("=======================");
		System.out.println("도서 등록");
		System.out.println("=======================");
		String strBCode;
		List<BookDTO> bookList = null;
		
		while(true) {
			System.out.print("도서코드(Enter:자동생성/-Q:quit");
			strBCode = scanner.nextLine();
			if(strBCode.equals("-Q"))break;
			if(strBCode.trim().isEmpty()) {
				String strTMBCode = bookDao.getMaxBCode();
				
				int intBCode = Integer.valueOf(strTMBCode.substring(2));
				intBCode ++;
				strBCode = strTMBCode.substring(0,2);
				strBCode += String.format("%04d", intBCode);
				
				System.out.println("생성된 코드 : " + strBCode);
				System.out.print("사용하시겠습니까? (Yes/No)");
				String YesNo = scanner.nextLine();
				if(YesNo.trim().isEmpty())break;
				else continue;
			}
			if(strBCode.length() != 6) {
				System.out.println("도서코드 길이가 맞지않음(6칸)");
				continue;
			}
			if(!strBCode.substring(0,2).equals("BK")) {
				System.out.println("도서코드는 'BK'로 시작해야함");
				continue;
			}
			if(bookDao.findById(strBCode) != null) {
				System.out.println("중복된 코드값");
				continue;
			}
			try {
				Integer.valueOf(strBCode.substring(2));
			} catch (Exception e) {
				System.out.println("코드의 3번째 이후는 숫자만 올수있음");
				continue;
			}
			break;
		}
		if(strBCode.equals("-Q"))return;
		String strBName;
		while(true) {
			System.out.print("도서명(-Q:quit) >>");
			strBName = scanner.nextLine();
			if(strBName.equals("-Q"))break;
			if(strBName.trim().isEmpty()) {
				System.out.println("도서명은 반드시 입력해야함");
				continue;
			}
			if(strBName.equals(bookDao.findByName(strBName))) {
				System.out.println("중복된 도서명");
				continue;
			}
			break;
		}
		if(strBName.equals("-Q"))return;
		String strBAuther= null;
		String strBComp= null;
		String strBYear = null;
		String strBIprice= null;
		String strBRprice= null;
		while(true) {
			System.out.print("저자(-Q:quit) >> ");
			strBAuther = scanner.nextLine();
			if(strBAuther.equals("-Q"))break;
			if(strBAuther.trim().isEmpty()) {
				System.out.println("저자는 반드시 입력해야함");
				continue;
			}
			
			System.out.print("출판사(-Q:quit) >> ");
			strBComp = scanner.nextLine();
			if(strBComp.equals("-Q"))break;
			if(strBComp.trim().isEmpty()) {
				System.out.println("출판사는 반드시 입력해야함");
				continue;
			}
			
			System.out.print("출간연도(-Q:quit) >> ");
			strBYear = scanner.nextLine();
			if(strBYear.equals("-Q"))break;
			if(strBYear.trim().isEmpty()) {
				System.out.println("출간연도는 반드시 입력해야함");
				continue;
			}
			
			System.out.print("출간연도(-Q:quit) >> ");
			strBYear = scanner.nextLine();
			if(strBYear.equals("-Q"))break;
			if(strBYear.trim().isEmpty()) {
				System.out.println("출간연도는 반드시 입력해야함");
				continue;
			}
			
			System.out.print("구입가격(-Q:quit) >> ");
			strBIprice = scanner.nextLine();
			if(strBIprice.equals("-Q"))break;
			if(strBIprice.trim().isEmpty()) {
				System.out.println("구입가격은 반드시 입력해야함");
				continue;
			}
			
			System.out.print("대여가격(-Q:quit) >> ");
			strBRprice = scanner.nextLine();
			if(strBRprice.equals("-Q"))break;
			if(strBRprice.trim().isEmpty()) {
				System.out.println("대여가격은 반드시 입력해야함");
				continue;
			}
			break;
		}
		int intYear = Integer.valueOf(strBYear);
		int intIPrice = Integer.valueOf(strBIprice);
		int intRPrice = Integer.valueOf(strBRprice);
		
		BookDTO bookDTO = BookDTO.builder()
				.b_code(strBCode)
				.b_name(strBName)
				.b_auther(strBAuther)
				.b_comp(strBComp)
				.b_year(intYear)
				.b_iprice(intIPrice)
				.b_rprice(intRPrice)
				.build();
		
		int ret = bookDao.insert(bookDTO);
		if(ret > 0) {
			System.out.println("도서정보 등록 완료");
			
		}else {
			System.out.println("도서정보 등록 실패!!");
		}
	}
}
