package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.*;
import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		Connection conn = getConnection();
		
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		close(conn);
		
		return m;
		
	}
	
	public int insertMember(Member m) {
		
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn,m);
		
		if(result >0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Member updateMember(Member m) {
		
		Connection conn = getConnection();
		
		// 1) 회원정보 변경요청 보내기(update)
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMember = null;
		
		// 2) 트랜잭션 처리
		if(result > 0) { // 성공
			commit(conn);
			
			// 3) 갱신된 회원정보 다시 조회해오기
			updateMember = new MemberDao().selectMember(conn, m.getUserId());
			
		} else {
			rollback(conn);
		}
		
		// 4) 자원반납
		close(conn);
		
		return updateMember;
		
	}

	public Member updatePwdMember(String userId, String userPwd, String updatePwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwdMember(conn, userId, userPwd, updatePwd);
		
		Member updatePwdMember = null;
		
		if(result > 0 ) {
			commit(conn);
			
			updatePwdMember = new MemberDao().selectMember(conn, userId);
			
			
		}else {
			rollback(conn);
		}
		close(conn);
		
		return updatePwdMember; 
		
	}

	public int deleteMember(String userId, String userPwd) {

		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMember(conn, userId, userPwd);
		
		if (result>0) {
			commit(conn);
			
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	
	}

	public int checkIdMember(String checkId) {
		Connection conn = getConnection();
		
		int count = new MemberDao().checkIdMember(conn, checkId);
		
		close(conn);
		
		return count;
		
	}


}
