package com.biz.user.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.user.config.DBConnection;
import com.biz.user.dao.BookDao;
import com.biz.user.dao.RentBookDao;
import com.biz.user.dao.UserDao;
import com.biz.user.persistence.BookDTO;

public class BookServiceV1 {

	protected BookDao bookDao;
	protected UserDao userDao;
	protected RentBookDao rbookDao;
	
	protected RentBookServiceV1 rbs;
	
	protected Scanner scanner;
	
	public BookServiceV1() {
		SqlSession sqlSession = DBConnection.getsqlSessionFactory().openSession(true);
		
		this.bookDao = sqlSession.getMapper(BookDao.class);
		this.userDao = sqlSession.getMapper(UserDao.class);
		// this.rbookDao = sqlSession.getMapper(RentBookDao.class);
		
		scanner = new Scanner(System.in);
	}
	public void bookMenu() {
		while(true) {
			System.out.println("==========================================");
			System.out.println("빛고을 도서관");
			System.out.println("==========================================");
			System.out.println("1.검색 2.등록 3.수정 4.삭제 0.종료");
			System.out.println("------------------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scanner.nextLine();
			
			int intMenu = -1;
			try {
				intMenu = Integer.valueOf(strMenu);
				if(intMenu == 0)return;
				else if(intMenu == 1) {
					//System.out.println("작동");
					this.booksearch();
				}
				else if(intMenu == 2) {
					
					this.bookinsert();
				}
				else if(intMenu == 3) {
					this.bookupdate();
				}
				else if(intMenu == 4) {
					this.bookdelete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//break;
		}
	}
	
	public void viewAllList() {
		List<BookDTO> bookList = bookDao.selectAll();
		if(bookList == null || bookList.size() < 1) {
			System.out.println("리스트가 없음");
		}else {
			this.viewList(bookList);
		}
	}

	protected void viewList(List<BookDTO> bookList) {
		System.out.println("============================================================");
		System.out.println("코드\t도서명\t작가\t출판사\t출간연도\t구입가격\t대여가격");
		for(BookDTO bookDTO : bookList) {
			this.viewList(bookDTO);
		}
		System.out.println("============================================================");	
	}

	protected void viewList(BookDTO bookDTO) {
		System.out.print(bookDTO.getB_code()+"\t");
		System.out.print(bookDTO.getB_name()+"\t");
		System.out.print(bookDTO.getB_auther()+"\t");
		System.out.print(bookDTO.getB_comp()+"\t");
		System.out.print(bookDTO.getB_year()+"\t");
		System.out.print(bookDTO.getB_iprice()+"\t");
		System.out.print(bookDTO.getB_rprice()+"\n");
		//System.out.print();
	}
	public void viewNameList() {
		System.out.println("=============================");
		System.out.println("도서이름 검색");
		System.out.println("=============================");
		System.out.print("도서이름 >> ");
		String strBName = scanner.nextLine();
		
		List<BookDTO> bookList = null;
		if(strBName.trim().isEmpty()) {
			bookDao.findByName(strBName);
		}else {
			bookList = bookDao.findByName(strBName);
		}
		this.viewList(bookList);
	}
	public void viewNameList(String b_name) {
		//System.out.print("도서이름 >> ");
		String strBName = b_name;
		
		List<BookDTO> bookList = null;
		if(strBName.trim().isEmpty()) {
			bookDao.findByName(strBName);
		}else {
			bookList = bookDao.findByName(strBName);
		}
		this.viewList(bookList);
	}
	
	public void viewAutherList() {
		System.out.println("=============================");
		System.out.println("저자 검색");
		System.out.println("=============================");
		System.out.print("저자 >> ");
		String strBAt = scanner.nextLine();
		
		List<BookDTO> bookList = null;
		if(strBAt.trim().isEmpty()) {
			bookDao.findByAuther(strBAt);
		}else {
			bookList = bookDao.findByAuther(strBAt);
		}
		this.viewList(bookList);
	}
	private void bookdelete() {
		// TODO Auto-generated method stub
	}
	private void bookupdate() {
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
		if(!strBName.trim().isEmpty()) {
			bookDTO.setB_name(strBName);
		}
		System.out.printf("수정할 저자(%s) >> ",bookDTO.getB_auther());
		strBAuther = scanner.nextLine();
		if(!strBAuther.trim().isEmpty()) {
			bookDTO.setB_auther(strBAuther);
		}
		System.out.printf("수정할 출판사(%s) >> ",bookDTO.getB_comp());
		strBComp = scanner.nextLine();
		if(!strBComp.trim().isEmpty()) {
			bookDTO.setB_comp(strBComp);
		}
		
		System.out.printf("수정할 구입연도(%s) >> ",bookDTO.getB_year());
		int intYear = 0;
		try {
			strBYear = scanner.nextLine();
			intYear = Integer.valueOf(strBYear);
			if(!strBYear.trim().isEmpty()) {
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
			if(!strBIprice.trim().isEmpty()) {
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
			if(!strBRprice.trim().isEmpty()) {
				bookDTO.setB_rprice(intRPrice);
			}
		} catch (Exception e) {
			System.out.println("대여가격은 숫자로만 입력");
			return;
		}
		System.out.println(bookDTO.toString());
		int ret = bookDao.update(bookDTO);
		if(ret > 0) {
			System.out.println("도서정보 변경 완료");
		}else {
			System.out.println("도서정보 변경 실패!!");
		}
	}
	private void bookinsert() {
		System.out.println("=======================");
		System.out.println("도서 등록");
		System.out.println("=======================");
		String strBCode;
		List<BookDTO> bookList = null;
		
		while(true) {
			System.out.print("도서코드(Enter:자동생성/-Q:quit)");
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
	private void booksearch() {
		this.viewNameList();
	}
}
