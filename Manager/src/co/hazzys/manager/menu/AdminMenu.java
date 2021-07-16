package co.hazzys.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.hazzys.manager.board.service.BoardService;
import co.hazzys.manager.board.serviceImpl.BoardServiceImpl;
import co.hazzys.manager.member.service.MemberService;
import co.hazzys.manager.member.serviceImpl.MemberServiceImpl;
import co.hazzys.manager.member.vo.MemberVO;

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 회원 정보 수정
	private BoardService boDao = new BoardServiceImpl(); // 게시글 관리

	private void title() {
		System.out.println();
		System.out.println("▶▶▶ 관리자 메뉴 ◀◀◀");
	}

	private void adminMenu(MemberVO vo) {
		System.out.println(vo.getName() + "님 관리자로 로그인되었습니다.");
		title();
		memberList();
	}

	private void memberList() { // 회원 전체 목록
		List<MemberVO> list = new ArrayList<MemberVO>();
		list = memDao.memberList();
		for (MemberVO vo : list) {
			vo.toString();
		}
	}

	public void run(MemberVO vo) {
		adminMenu(vo);
	}

}
