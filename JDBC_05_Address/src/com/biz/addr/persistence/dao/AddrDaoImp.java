package com.biz.addr.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.biz.addr.config.DBContract;
import com.biz.addr.persistence.AddrDTO;

public class AddrDaoImp extends AddrDao{

	@Override
	public List<AddrDTO> selectAll() {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_ADDR;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private AddrDTO rst_2_DTO(ResultSet rst) throws SQLException {
		AddrDTO dto = AddrDTO.builder()
				.ID(rst.getLong("id"))
				.NAME(rst.getString("name"))
				.TEL(rst.getString("tel"))
				.ADDR(rst.getString("addr"))
				.CHAIN(rst.getString("chain"))
				.build();
		return dto;
	}
	
	@Override
	public AddrDTO findById(long id) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_ADDR;
		sql += " WHERE ID = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, id);
			ResultSet rst = pStr.executeQuery();
			AddrDTO addrDTO = null;
			if(rst.next()) {
				addrDTO = this.rst_2_DTO(rst);
			}
			rst.close();
			pStr.close();
			return addrDTO;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<AddrDTO> findByName(String name) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_ADDR;
		
		sql += " WHERE name LIKE '%' || ? || '%' ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, name);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<AddrDTO> findByTel(String tel) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_ADDR;
		sql += " WHERE tel LIKE '%' || ? || '%' ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, tel);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public List<AddrDTO> findByChain(String chain) {
		PreparedStatement pStr = null;
		String sql = DBContract.SQL.SELECT_ADDR;
		sql += " WHERE chain LIKE '%' || ? || '%' ";
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, chain);
			ResultSet rst = pStr.executeQuery();
			List<AddrDTO> addrList = new ArrayList<AddrDTO>();
			while(rst.next()) {
				AddrDTO dto = this.rst_2_DTO(rst);
				addrList.add(dto);
			}
			rst.close();
			pStr.close();
			return addrList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public int insert(AddrDTO addrDTO) {
		PreparedStatement pStr = null;
		String sql = " INSERT INTO tbl_addr( ";
				sql += " ID, ";
				sql += " NAME, ";
				sql += " TEL, ";
				sql += " ADDR, ";
				sql += " CHAIN) "; 
				sql += " VALUES (SEQ_ADDR.NEXTVAL, "
						+ " ?,"
						+ " ?,"
						+ " ?,"
						+ " ? )";
				
				try {
					pStr = dbConn.prepareStatement(sql);
					pStr.setString(1, addrDTO.getNAME());
					pStr.setString(2, addrDTO.getTEL());
					pStr.setString(3, addrDTO.getADDR());
					pStr.setString(4, addrDTO.getCHAIN());
					
					int ret = pStr.executeUpdate();
					pStr.close();
					return ret;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		return 0;
	}

	@Override
	public int update(AddrDTO addrDTO) {
		PreparedStatement pStr = null;
		
		String sql = " UPDATE tbl_addr SET ";
		
		sql += " name = ?, ";
		sql += " tel = ?, ";
		sql += " addr = ?, ";
		sql += " chain = ? ";
		sql += " WHERE id = ? ";
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, addrDTO.getNAME());
			pStr.setString(2, addrDTO.getTEL());
			pStr.setString(3, addrDTO.getADDR());
			pStr.setString(4, addrDTO.getCHAIN());
			pStr.setLong(5, addrDTO.getID());
			
			int ret = pStr.executeUpdate();
			
			if(ret > 0 ) {
				System.out.println("주소록 변경 완료");
			} else {
				System.out.println("주소록 변경 실패");
			}
			pStr.close();
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
