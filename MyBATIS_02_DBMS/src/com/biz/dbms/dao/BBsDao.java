package com.biz.dbms.dao;

import java.util.List;

import com.biz.dbms.persistence.BBSDTO;

public interface BBsDao {
	
	public List<BBSDTO> selectAll();
	
	public BBSDTO findById(long bs_id);
	
	public int insert(BBSDTO bbsDTO);
	public int update(BBSDTO bbsDTO);
	public int delete(long bs_id);

}
