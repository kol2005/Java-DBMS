package com.biz.addr.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddrDTO {

	private long ID;//	NUMBER
	private String NAME;//	NVARCHAR2(50 CHAR)
	private String TEL;//	VARCHAR2(20 BYTE)
	private String ADDR;//	NVARCHAR2(125 CHAR)
	private String CHAIN;//	NVARCHAR2(10 CHAR)
}
