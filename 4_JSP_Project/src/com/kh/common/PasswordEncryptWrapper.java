package com.kh.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncryptWrapper extends HttpServletRequestWrapper{
	
	public PasswordEncryptWrapper(HttpServletRequest request) {
		super(request);
	}
	
	// getParameter 오버라이딩
	// 매개변수로 넘어온 password 값 찾아서 암호화하기
	@Override
	public String getParameter(String name) {
		String value= "" ;
		
		// 매개변수로 전달받은 name 변수의 값이 userPwd일때 암호화하기
		if(name.equals("userPwd")) {
			// 암호화작업
			System.out.println("암호화 전 pwd : "+super.getParameter(name));
			//
			value = getSHA512(super.getParameter(name));
			System.out.println("암호화 후 pwd : "+value);
		} else {
			value = super.getParameter(name);
		}
		return value;
	}
	
	private String getSHA512(String val) {
		String encPwd = "";
		// 암호화 처리 객체 선언
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		// 암호화시 bit연산하고, bit연산한 결과를 byte[]로 담아서 보관.
		byte[] bytes = val.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		
		encPwd = Base64.getEncoder().encodeToString(md.digest());
		
		return encPwd;
		
		
	}
	
	
	
}
