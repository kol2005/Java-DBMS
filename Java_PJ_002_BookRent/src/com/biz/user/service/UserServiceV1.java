package com.biz.user.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.user.config.DBConnection;
import com.biz.user.dao.BookDao;
import com.biz.user.dao.RentBookDao;
import com.biz.user.dao.UserDao;
import com.biz.user.persistence.UserDTO;

public class UserServiceV1 {

	protected BookDao bookDao;
	protected UserDao userDao;
	protected RentBookDao ruserDao;
	
	protected RentBookServiceV1 rbs;
	
	protected Scanner scanner;
	
	public UserServiceV1() {
		SqlSession sqlSession = DBConnection.getsqlSessionFactory().openSession(true);
		
		this.bookDao = sqlSession.getMapper(BookDao.class);
		this.userDao = sqlSession.getMapper(UserDao.class);
		// this.ruserDao = sqlSession.getMapper(RentuserDao.class);
		
		scanner = new Scanner(System.in);
	}
	public void userMenu() {
		while(true) {
			System.out.println("==========================================");
			System.out.println("도서회원 정보");
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
					this.usersearch();
				}
				else if(intMenu == 2) {
					
					this.userinsert();
				}
				else if(intMenu == 3) {
					this.userupdate();
				}
				else if(intMenu == 4) {
					this.userdelete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			//break;
		}
	}
	
	public void viewAllList() {
		List<UserDTO> userList = userDao.selectAll();
		if(userList == null || userList.size() < 1) {
			System.out.println("리스트가 없음");
		}else {
			this.viewList(userList);
		}
	}

	protected void viewList(List<UserDTO> userList) {
		System.out.println("============================================================");
		System.out.println("코드\t이름\t전화\t주소");
		for(UserDTO userDTO : userList) {
			this.viewList(userDTO);
		}
		System.out.println("============================================================");	
	}

	protected void viewList(UserDTO userDTO) {
		System.out.print(userDTO.getU_code()+"\t");
		System.out.print(userDTO.getU_name()+"\t");
		System.out.print(userDTO.getU_tel()+"\t");
		System.out.print(userDTO.getU_addr()+"\n");
		//System.out.print();
	}
	public void viewNameList() {
		System.out.println("=============================");
		System.out.println("회원이름 검색");
		System.out.println("=============================");
		System.out.print("회원이름 >> ");
		String strUName = scanner.nextLine();
		
		List<UserDTO> userList = null;
		if(strUName.trim().isEmpty()) {
			userDao.findByName(strUName);
		}else {
			userList = userDao.findByName(strUName);
		}
		this.viewList(userList);
	}
	public void viewNameList(String u_name) {
		String strUName = u_name;
		
		List<UserDTO> userList = null;
		if(strUName.trim().isEmpty()) {
			userDao.findByName(strUName);
		}else {
			userList = userDao.findByName(strUName);
		}
		this.viewList(userList);
	}
	
	public void viewAutherList() {
		System.out.println("=============================");
		System.out.println("전화번호 검색");
		System.out.println("=============================");
		System.out.print("전화번호 >> ");
		String strUta = scanner.nextLine();
		
		List<UserDTO> userList = null;
		if(strUta.trim().isEmpty()) {
			userDao.findByTel(strUta);
		}else {
			userList = userDao.findByTel(strUta);
		}
		this.viewList(userList);
	}
	private void userdelete() {
		// TODO Auto-generated method stub
	}
	private void userupdate() {
		System.out.println("=======================");
		System.out.println("회원정보 변경");
		System.out.println("=======================");
		//String strBCode;
		//List<userDTO> userList = null;
		System.out.print("회원명 검색 >> ");
		String strUName = scanner.nextLine();
			if(strUName.trim().isEmpty()) {
				this.viewAllList();
			} else {
				this.viewNameList(strUName);
			}
			
		String strUTel = null;
		String strUAddr = null;
		
		System.out.print("수정할 회원코드 >>");
		String strUCode = scanner.nextLine();
		UserDTO userDTO = userDao.findById(strUCode);
		System.out.println(userDTO.toString());
		
		System.out.printf("수정할 회원명(%s) >> ",userDTO.getU_name());
		strUName = scanner.nextLine();
		if(!strUName.trim().isEmpty()) {
			userDTO.setU_name(strUName);
		}
		System.out.printf("수정할 전화번호(%s) >> ",userDTO.getU_tel());
		strUTel = scanner.nextLine();
		if(!strUTel.trim().isEmpty()) {
			userDTO.setU_tel(strUTel);
		}
		System.out.printf("수정할 주소(%s) >> ",userDTO.getU_addr());
		strUAddr = scanner.nextLine();
		if(!strUAddr.trim().isEmpty()) {
			userDTO.setU_addr(strUAddr);
		}
				
		System.out.println(userDTO.toString());
		int ret = userDao.update(userDTO);
		if(ret > 0) {
			System.out.println("회원정보 변경 완료");
		}else {
			System.out.println("회원정보 변경 실패!!");
		}
	}
	private void userinsert() {
		System.out.println("=======================");
		System.out.println("신규회원 등록");
		System.out.println("=======================");
		String strUCode;
		List<UserDTO> userList = null;
		
		while(true) {
			System.out.print("회원코드(Enter:자동생성/-Q:quit)");
			strUCode = scanner.nextLine();
			if(strUCode.equals("-Q"))break;
			if(strUCode.trim().isEmpty()) {
				String strTMUCode = userDao.getMaxUCode();
				
				int intUCode = Integer.valueOf(strTMUCode.substring(1));
				intUCode ++;
				strUCode = strTMUCode.substring(0,1);
				strUCode += String.format("%05d", intUCode);
				
				System.out.println("생성된 코드 : " + strUCode);
				System.out.print("사용하시겠습니까? (Yes/No)");
				String YesNo = scanner.nextLine();
				if(YesNo.trim().isEmpty())break;
				else continue;
			}
			if(strUCode.length() != 6) {
				System.out.println("회원코드 길이가 맞지않음(6칸)");
				continue;
			}
			if(!strUCode.substring(0,1).equals("S")) {
				System.out.println("회원코드는 'S'로 시작해야함");
				continue;
			}
			if(userDao.findById(strUCode) != null) {
				System.out.println("중복된 코드값");
				continue;
			}
			try {
				Integer.valueOf(strUCode.substring(1));
			} catch (Exception e) {
				System.out.println("코드의 2번째 이후는 숫자만 올수있음");
				continue;
			}
			break;
		}
		if(strUCode.equals("-Q"))return;
		String strUName;
		while(true) {
			System.out.print("회원명(-Q:quit) >>");
			strUName = scanner.nextLine();
			if(strUName.equals("-Q"))break;
			if(strUName.trim().isEmpty()) {
				System.out.println("회원명은 반드시 입력해야함");
				continue;
			}
			if(strUName.equals(userDao.findByName(strUName))) {
				System.out.println("중복된 회원명");
				continue;
			}
			break;
		}
		if(strUName.equals("-Q"))return;
		String strUTel = null;
		String strUAddr = null;
		while(true) {
			System.out.print("전화(-Q:quit) >> ");
			strUTel = scanner.nextLine();
			if(strUTel.equals("-Q"))break;
			if(strUTel.trim().isEmpty()) {
				System.out.println("전화는 반드시 입력해야함");
				continue;
			}
			if(strUTel.equals(userDao.findByTel(strUTel))) {
				System.out.println("중복된 전화번호");
				continue;
			}
			
			System.out.print("주소(-Q:quit) >> ");
			strUAddr = scanner.nextLine();
			if(strUAddr.equals("-Q"))break;
			if(strUAddr.trim().isEmpty()) {
				System.out.println("주소는 반드시 입력해야함");
				continue;
			}
			break;
		}// while 종료
		
		UserDTO userDTO = UserDTO.builder()
				.u_code(strUCode)
				.u_name(strUName)
				.u_tel(strUTel)
				.u_addr(strUAddr)
				.build();
		
		int ret = userDao.insert(userDTO);
		if(ret > 0) {
			System.out.println("회원정보 등록 완료");
			
		}else {
			System.out.println("회원정보 등록 실패!!");
		}
	}
	private void usersearch() {
		this.viewNameList();
	}
}
