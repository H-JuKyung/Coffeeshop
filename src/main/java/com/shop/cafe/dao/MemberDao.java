package com.shop.cafe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.shop.cafe.dto.Member;

@Repository
public class MemberDao {
	
	@Value("${spring.datasource.driver-class-name}")
	private String DB_DRIVER;
	
	@Value("${spring.datasource.url}")
	private String DB_URL;
	
	@Value("${spring.datasource.username}")
	private String DB_USER;
	
	@Value("${spring.datasource.password}")
	private String DB_PW;
	
	public void insertMember(Member m) throws Exception {
		System.out.println("MemberDao insertMember() 호출됨"); // 회원가입할 때는 호출 빈도수를 줄일 수 없음
		
		Class.forName(DB_DRIVER);
		Connection con = DriverManager.getConnection(DB_URL, DB_USER , DB_PW);
		PreparedStatement stmt = con.prepareStatement("insert into member(email, pw, nickname) values(?, ?, ?)");
		
		stmt.setString(1, m.getEmail());
		stmt.setString(2, m.getPw());
		stmt.setString(3, m.getNickname());
		
		int i = stmt.executeUpdate();
		
		System.out.println(i + "행이 insert됨");
	}
}
