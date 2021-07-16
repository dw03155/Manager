package co.hazzys.manager.member.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.hazzys.manager.Dao;
import co.hazzys.manager.member.service.MemberService;
import co.hazzys.manager.member.vo.MemberVO;

public class MemberServiceImpl extends Dao implements MemberService {
	private Connection conn = Dao.getConnection(); // DBMS와 연결하는 객체
	private PreparedStatement psmt; // conn을 통해 sql 명령을 전달하고 실행하는 객체
	private ResultSet rs; // select 구문을 호출시 결과를 돌려받는 객체

	@Override
	public List<MemberVO> memberList() { // 회원 전체 목록
		List<MemberVO> members = new ArrayList<MemberVO>(); // 집합
		MemberVO member;
		String sql = "select * from member";
		try {
			psmt = conn.prepareStatement(sql); // sql 명령을 실행해서 보여준다.
			rs = psmt.executeQuery();
			while (rs.next()) { // 몇행 올지 모르니까 - while
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setAge(rs.getInt("age"));
				member.setAuthor(rs.getString("author"));
				members.add(member);
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	@Override
	public MemberVO memberSelect(MemberVO vo) { // 회원 조회 (한건 조회)
		String sql = "select * from member where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId()); //?에 대한 값
			rs = psmt.executeQuery();
			if (rs.next()) { // 한행만 불러오기 - if
				vo.setId(rs.getString("id"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				vo.setAge(rs.getInt("age"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public MemberVO loginCheck(MemberVO vo) { // 로그인 과정
		String sql = "select * from member where id = ? and password = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPassword());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setAuthor(rs.getString("author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		int n = 0;
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		int n = 0;
		return n;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		int n = 0;
		return n;
	}

}
