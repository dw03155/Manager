package co.hazzys.manager.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.hazzys.manager.board.service.BoardService;
import co.hazzys.manager.board.serviceImpl.BoardServiceImpl;
import co.hazzys.manager.board.vo.BoardVO;
import co.hazzys.manager.member.service.MemberService;
import co.hazzys.manager.member.serviceImpl.MemberServiceImpl;
import co.hazzys.manager.member.vo.MemberVO;

public class UserMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 멤버 정보 수정
	private BoardService boDao = new BoardServiceImpl(); // 게시글 관리

	private void title() {
		System.out.println();
		System.out.println("▶▶▶ 사용자 메뉴 ◀◀◀");
	}

	private void userMenu(MemberVO vo) {
		System.out.println(vo.getName() + "님 사용자로 로그인되었습니다.");
		title();
		boardList();
	}

	private void boardList() {// 게시판 전체 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardList();
		for (BoardVO vo : list) {
			vo.toString();
		}
	}

	public void run(MemberVO vo) {
		userMenu(vo);
	}

}
