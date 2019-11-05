package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1{
	
	public void deptMenu() {
		System.out.println("===================================");
		System.out.println("거래처 정보 관리");
		System.out.println("===================================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.println("-----------------------------------");
		System.out.print("업무선택 >>");
		String strMenu = scanner.nextLine();
		
		try {
			int intMenu = Integer.valueOf(strMenu);
			
			/*
			 * else를 사용하지 않으면 모든 if문을
			 * 다 검사하는 방식으로 코드가 실행되지만
			 * else를 사용하면 해당하는 코드에서 true가 나오면 
			 * 이후 코드는 건너뛴다
			 */
			if(intMenu == 0)return;
			else if(intMenu == 1) {
				this.deptInsert();
			}else if(intMenu == 2) {
				this.viewNameList();
				this.deptUpdate();
			}else if(intMenu == 3) {
				// 상호로 검색을해서 리스트를 보여주고
				this.viewNameList();
				// 보여준 리스트 중에서
				// 거래처 코드를 입력받아서 삭제
				this.deptDelete();
			}else if(intMenu == 4) {
				this.viewNameAndCeoList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void deptDelete() {
		while(true) {
		System.out.print("삭제할 거래처 코드(-Q:quit) >>");
		String strDCode = scanner.nextLine();
		if(strDCode.equals("-Q"))break;
		DeptDTO deptDTO = deptDao.findById(strDCode);
		if(deptDTO == null) {
			System.out.println("삭제할 거래처코드가 없음");
			continue;
			}
		this.viewDetail(deptDTO);
		System.out.println("정말 삭제?? Enter:실행");
		String strYesNo = scanner.nextLine();
		
		if(strYesNo.trim().isEmpty()) {
			int ret = deptDao.delete(strDCode);
			if(ret > 0) {
				System.out.println("삭제완료");
				break;
			}
			else System.out.println("삭제실패");	
		}
		}
	}// end delete
	
	public void deptUpdate() {
			
			System.out.print("수정할 거래처 코드(-Q:quit) >>");
			String strDCode = scanner.nextLine();
			
			if(strDCode.equals("-Q"))return;
			DeptDTO deptDTO = deptDao.findById(strDCode);
			if(deptDTO == null) {
				System.out.println("수정할 코드가 없음");
			 return;
			}
			System.out.printf("거래처명(%s) : )",deptDTO.getD_name());
			String strDName = scanner.nextLine();
			// Enter를 눌렀는지 아니면
			// 값(거래처명)을 입력하고 Enter를 눌렀는지에 따라
			// 다른 행동을 하기
			if(!strDName.trim().isEmpty()) {
				deptDTO.setD_name(strDName);
			}
			//if(strDName.trim().isEmpty()) { 그냥 Enter만 눌렀으면 건너뛰기
			// } else {
			// deptDTO.setD_name(strDName); 값을 입력했으면 입력한 값으로 변경하기
			// }
			System.out.printf("대표(%s) : )",deptDTO.getD_ceo());
			String strDCeo = scanner.nextLine();
			if(!strDCeo.trim().isEmpty()) {
				deptDTO.setD_ceo(strDCeo);
			}
			System.out.printf("전화(%s) : )",deptDTO.getD_tel());
			String strDTel = scanner.nextLine();
			if(!strDTel.trim().isEmpty()) {
				deptDTO.setD_tel(strDTel);
			}
			System.out.printf("주소(%s) : ",deptDTO.getD_addr());
			String strDAddr = scanner.nextLine();
			if(!strDAddr.trim().isEmpty()) {
				deptDTO.setD_addr(strDAddr);
			}
			

			System.out.println(deptDao.findById(strDCode).toString());
		
		int ret = deptDao.update(deptDTO);
		if(ret > 0) {
			System.out.println("수정 완료");
			return;
		}else {
			System.out.println("수정 실패!!");
		}
		System.out.println(deptDao.findById(strDCode).toString());
	}
	
	public void deptInsert() {
		
	}
	
}
