package com.biz.user.dao;

import java.util.List;

import com.biz.user.persistence.RentBookDTO;
import com.biz.user.persistence.UserDTO;

public interface RentBookDao {

	public List<RentBookDTO> selectAll();
	public List<RentBookDTO> selectAllN();
	public RentBookDTO findByBCode(String rent_bcode);
	public RentBookDTO findByUCode(String rent_ucode);
	public RentBookDTO findBySeq(long rent_seq);
	public int insert(RentBookDTO rentBookDTO);
	public int update(RentBookDTO rentBookDTO);
	public int delete(long rent_seq);
	
}
