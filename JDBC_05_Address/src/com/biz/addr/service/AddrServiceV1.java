package com.biz.addr.service;

import java.util.List;
import java.util.Scanner;

import com.biz.addr.persistence.AddrDTO;
import com.biz.addr.persistence.dao.AddrDao;
import com.biz.addr.persistence.dao.AddrDaoImp;

public class AddrServiceV1 {

	AddrDao addrDao = null;
	Scanner scanner = null;
	
	public AddrServiceV1() {
		addrDao = new AddrDaoImp();
		scanner = new Scanner(System.in);
	}
	
	public void viewAddr() {
		List<AddrDTO> addrList = addrDao.selectAll();
		this.viewList(addrList);
	}

	private void viewList(List<AddrDTO> addrList) {
		System.out.println("====================================");
		System.out.println("전체 주소");
		System.out.println("====================================");
		System.out.println("ID\t이름\t전화번호\t주소\t관계");
		System.out.println("------------------------------------");
		for(AddrDTO vo : addrList) {
			System.out.printf("%s\t",vo.getID());
			System.out.printf("%s\t",vo.getNAME());
			System.out.printf("%s\t",vo.getTEL());
			System.out.printf("%s\t",vo.getADDR());
			System.out.printf("%s\n",vo.getCHAIN());
		}
		System.out.println("====================================");
	}
	
	public String searchAddrName() {
		
		System.out.println("====================================");
		System.out.println("이름 검색");
		System.out.println("====================================");
		System.out.print("이름(-Q:quit) >>");
		String strName = scanner.nextLine();
		if(strName.equals("-Q"))return "-Q";
		this.searchAddrName(strName);
		return strName;
		
	}
	
	public boolean searchAddrName(String strName) {
		List<AddrDTO> addrList = addrDao.findByName(strName);
		//this.viewList(addrList);
		if(addrList == null || addrList.size() < 1) {
			System.out.println("찾는 사람 없음");
			return false;
		}
		this.viewList(addrList);
		return true;
	}
	
}
