package com.biz.iolist.exec;

import java.util.List;

import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.persistence.IolistDTO;
import com.biz.iolist.service.IolistServiceV2;

public class IolistEx_03 {

	public static void main(String[] args) {
		
		IolistServiceV2 is = new IolistServiceV2();
		
		List<IolistDTO> iolist = IolistDao.class
		is.selectAll(iolist);

	}

}
