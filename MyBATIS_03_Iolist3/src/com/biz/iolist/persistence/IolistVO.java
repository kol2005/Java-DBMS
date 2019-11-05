package com.biz.iolist.persistence;

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
public class IolistVO {

	private String io_date;
	private String io_inout;
	private String io_dcode;
	private String io_dname;
	private String io_dceo;
	private String io_dtel;
	private String io_daddr;
	private String io_pcode;
	private String io_pname;
	private int io_iprice;
	private int io_oprice;
	private String io_pvat;
	private int io_qty;
	private int io_price;
	private int io_total;
	private long io_seq;
}
