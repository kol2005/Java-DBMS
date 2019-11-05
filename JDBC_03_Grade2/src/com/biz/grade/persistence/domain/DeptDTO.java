package com.biz.grade.persistence.domain;

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
@ToString
@Builder
public class DeptDTO {
	private String d_num;	//	varchar2(5 byte)
	private String d_name;	//	varchar2(30 byte)
	private String d_pro;	//	varchar2(20 byte)
	private String d_tel;	//	varchar2(20 byte)
}
