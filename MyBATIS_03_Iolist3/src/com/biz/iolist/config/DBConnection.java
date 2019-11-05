package com.biz.iolist.config;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import lombok.Builder;

public class DBConnection {

	private static SqlSessionFactory sqlSessionFactory;

	static {
		String configFile = "com/biz/iolist/config/mybatis-config.xml";
		InputStream inputStream = null;

		try {
			inputStream = Resources.getResourceAsStream(configFile);

			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
			
			/*
			 * sqlSessionFactory 를 noll 인가를 검사하는 이유
			 * multi thread 환경에서 singletone을 보장하기 위해서
			 */
			if (sqlSessionFactory == null) {
				sqlSessionFactory = builder.build(inputStream);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static SqlSessionFactory getsqlSessionFactory() {
		return sqlSessionFactory;
	}
}
