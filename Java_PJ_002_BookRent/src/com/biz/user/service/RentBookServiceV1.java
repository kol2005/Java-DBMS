package com.biz.user.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.user.config.DBConnection;
import com.biz.user.dao.BookDao;
import com.biz.user.dao.RentBookDao;
import com.biz.user.dao.UserDao;
import com.biz.user.persistence.BookDTO;
import com.biz.user.persistence.RentBookDTO;
import com.biz.user.persistence.UserDTO;

public class RentBookServiceV1 {

	protected BookDao bookDao;
	protected UserDao userDao;
	protected RentBookDao rentBookDao;

	protected Scanner scanner;
	
	

	public RentBookServiceV1() {

		SqlSession sqlSession = 
				DBConnection.getsqlSessionFactory().openSession(true);
		
		this.bookDao = sqlSession.getMapper(BookDao.class);
		this.userDao = sqlSession.getMapper(UserDao.class);
		this.rentBookDao = sqlSession.getMapper(RentBookDao.class);
		
		scanner = new Scanner(System.in);
	}
	public void rentMenu() {
		while(true) {
			System.out.println("============================================");
			System.out.println("도서 대출 정보v1");
			System.out.println("============================================");
			System.out.println("1.검색 2.대여 3.반납 4.삭제 0.종료");
			System.out.println("--------------------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scanner.nextLine();
			
			int intMenu = -1;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				
			}
			if(intMenu == 0)break;
			else if(intMenu == 1)this.search();
			else if(intMenu == 2)this.rental();//대여
			else if(intMenu == 3)this.rentreturn();//반납
			else if(intMenu == 4)this.delete();
		}
			System.out.println("업무종료 ");
	}
	
	private void rentreturn() {//반납
		
		List<RentBookDTO> rentBookList = rentBookDao.selectAll();
		RentBookDTO rentBookDTO = null;
		while(true) {
			
			int sw = 0;
		System.out.print("도서명(-Q:quit) >> ");
		String strBName = scanner.nextLine();
		if(strBName.equals("-Q"))break;
		List<BookDTO> bookList = bookDao.findByName(strBName);
		BookDTO bookDTO;
		if(bookList != null && bookList.size() > 0) {
			for(BookDTO dto : bookList) {
				System.out.println(dto.toString());
			}
		}
		System.out.println("============================");
		System.out.println("도서 반납");
		System.out.println("============================");
		System.out.print("도서코드 >> ");
		String strBCode = scanner.nextLine();
		bookDTO = bookDao.findById(strBCode);
		for(RentBookDTO dto : rentBookList) {
		//RentBookDTO rentList = rentBookDao.findByBCode(strBCode);
		System.out.println(dto.toString());
		}
		System.out.println("============================");
		System.out.println("도서 반납");
		System.out.println("============================");
		System.out.print("SEQ >> ");
		String strSEQ = scanner.nextLine();
		long longSEQ = Long.valueOf(strSEQ);
		rentBookDTO = rentBookDao.findBySeq(longSEQ);
		if(longSEQ == 0 || rentBookDTO.getRent_seq()!=longSEQ) {
			System.out.println("도서대여 정보 틀림(SEQ)");
			continue;
		}else if(!rentBookDTO.getRent_bcode().equals(strBCode)) {
			System.out.println("SEQ정보와 도서코드 일치하지 않음");
		}
		String YesNo = null;
		String strUName = null;
		String strUCode = null;
		String strreturyn = null;
		int strpoint = 0;
	
		if(bookDTO == null) {
			System.out.println("도서코드를 확인하세요");
			continue;
		}else {
			System.out.println("-----------------------------");	
			System.out.println(rentBookDTO.toString());
			System.out.println("-----------------------------");
			System.out.println("반납하시겠습니까?(YES:Enter/No:처음으로)");
			YesNo = scanner.nextLine();
			
		} if(YesNo.equalsIgnoreCase("no")) {//인서트문
		continue;
		}
			//System.out.print("회원코드 입력 >> ");
			//strUCode = scanner.nextLine();
			//List<UserDTO> userList = userDao.findByName(strUName);
			//for(UserDTO dto : userList) {
				//System.out.println(dto.toString());
			
			System.out.println("반납하실 분의 회원코드를 입력하세요");
			System.out.print("회원코드 >> ");
			strUCode = scanner.nextLine();
			rentBookDTO = rentBookDao.findByUCode(strUCode);
			if(!rentBookDTO.getRent_ucode().equals(strUCode)) {
				System.out.println("회원코드를 잘못 입력함");
				continue;
			}
			System.out.println(rentBookDTO.toString());
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String curDate = sd.format(date);
			
			//Calendar cal = Calendar.getInstance();
			//cal.setTime(date);
			//cal.add(Calendar.DATE, 14);
			String curcal = sd.format(date);
			
			strreturyn = "N";
			rentBookDTO.setRent_retur_yn(strreturyn);
			System.out.println(rentBookDTO.toString());
	
			int ret = rentBookDao.update(rentBookDTO);
			if(ret > 0 ) {
				System.out.println("도서 대여정보 반납 완료!!");
			}
			continue;
	}
}
	
		
	
	private void rental() {//대여
		
		
		List<RentBookDTO> rentBookList = rentBookDao.selectAll();
		RentBookDTO rentBookDTO = null;
		while(true) {
			
			int sw = 0;
		System.out.print("도서명(-Q:quit) >> ");
		String strBName = scanner.nextLine();
		if(strBName.equals("-Q"))break;
		List<BookDTO> bookList = bookDao.findByName(strBName);
		if(bookList != null && bookList.size() > 0) {
			for(BookDTO dto : bookList) {
				System.out.println(dto.toString());
			}
		}
		System.out.println("============================");
		System.out.println("도서 대여");
		System.out.println("============================");
		System.out.print("도서코드 >> ");
		String strBCode = scanner.nextLine();
		String YesNo = null;
		String strUName = null;
		String strUCode = null;
		String strreturyn = null;
		int strpoint = 0;
		
		BookDTO bookDTO = bookDao.findById(strBCode);
		for(RentBookDTO rentDTO : rentBookList) {
			System.out.println(rentDTO.toString());
			if(rentDTO.getRent_bcode()!=null || rentDTO.getRent_bcode() == strBCode 
					&& rentDTO.getRent_retur_yn().equalsIgnoreCase("Y")) {
				System.out.println("도서코드 중복,대출중 대출불가");
				sw = 1;
				break;
			}
			
		}
		if(sw == 1) {
			continue;
		}
		//rentBookDTO = rentBookDao.findByBCode(strBCode);
		//if(rentBookDTO==null) {
		//System.out.println(rentBookDTO.toString());
		//UserDTO userDTO = userDao.findById(strUCode);
		if(bookDTO == null) {
			System.out.println("도서코드를 확인하세요");
			continue;
		}else {
			System.out.println("-----------------------------");	
			System.out.println(bookDTO.toString());
			System.out.println("-----------------------------");
			System.out.println("대여하시겠습니까?(YES:Enter/No:처음으로)");
			YesNo = scanner.nextLine();
			
		} if(YesNo.equalsIgnoreCase("no")) {//인서트문
		continue;
		}
			System.out.print("회원정보 확인(이름검색) >> ");
			strUName = scanner.nextLine();
			List<UserDTO> userList = userDao.findByName(strUName);
			for(UserDTO dto : userList) {
				System.out.println(dto.toString());
			
			System.out.println("대여하실 분의 회원코드를 입력하세요");
			System.out.print("회원코드 >> ");
			strUCode = scanner.nextLine();
			UserDTO userDTO = userDao.findById(strUCode);
			if(!userDTO.getU_code().equals(strUCode)) {
				System.out.println("회원코드를 잘못 입력함");
				continue;
			}
			System.out.println(userDTO.toString());
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String curDate = sd.format(date);
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			cal.add(Calendar.DATE, 14);
			String curcal = sd.format(cal.getTime());
			
			strreturyn = "Y";
			
					rentBookDTO = RentBookDTO.builder()
					.rent_date(curDate)
					.rent_return_date(curcal)
					.rent_bcode(strBCode)
					.rent_ucode(strUCode)
					.rent_retur_yn(strreturyn)
					.rent_point(strpoint)
					.build();
			
					int ret = rentBookDao.insert(rentBookDTO);
					if(ret > 0 ) {
						System.out.println("도서 대여정보 등록 완료!!");
					}
		}
		
		
		continue;
		}
	}
	private void search() {//검색
		while(true) {
			System.out.println("===============================");
			System.out.println("도서명 검색");
			System.out.println("===============================");
			System.out.print("도서명(-Q:처음으로) >> ");
			String strBName = scanner.nextLine();
			
			if(strBName.equals("-Q"))break;
			
			List<BookDTO> bookList = bookDao.findByName(strBName);
			if(bookList != null && bookList.size() > 0) {
				
				for(BookDTO dto : bookList) {
					System.out.println(dto.toString());
				}
			}
			continue;
		}
	}
	private void delete() {
		// TODO Auto-generated method stub
		
	}

}
