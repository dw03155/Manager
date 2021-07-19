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
		System.out.println("▶   1. 마이페이지  ◀");
		System.out.println("▶   2. 글 목록	 ◀");
		System.out.println("▶   3. 글 조회	 ◀");
		System.out.println("▶   4. 글 쓰기	 ◀");
		System.out.println("▶   5. 글 수정	 ◀");
		System.out.println("▶   6. 글 삭제	 ◀");
		System.out.println("▶   7. 돌아가기	 ◀");
		System.out.println("▶▶▶▶▶▶▶◀◀◀◀◀◀◀");
		System.out.print("메뉴를 선택하세요.");
	}// end of Title

	private void userMenu(MemberVO vo) {
		System.out.println(vo.getName() + "님 사용자로 로그인되었습니다.");
		title();
		boolean run = false;
		do {
			int chk;
			switch (chk = sc.nextInt()) {
			case 1:
				myPage(); // 마이페이지
				break;
			case 2:
				boardList(); // 글 목록
				break;
			case 3:
				boardSelect();// 글 조회
				break;
			case 4:
				boardInsert(); // 글 입력
				break;
			case 5:
				boardUpdate(vo.getId()); // 글 수정
				break;
			case 6:
				boardDelete(); // 글 삭제
				break;
			case 7:
				run = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}
		} while (!run);
	}// end of UM

	private void myPage() { // 마이페이지
		MemberVO vo = new MemberVO();
		System.out.println("수정할 주소 > ");
		vo.setAddress(sc.nextLine());
		System.out.println("수정할 번호 > ");
		vo.setTel(sc.nextLine());
		System.out.println("수정할 나이 > ");
		vo.setAge(sc.nextInt());
		sc.nextLine();

		int n = memDao.memberUpdate(vo);
		if (n != 0)
			System.out.println("수정에 성공하였습니다.");
		else
			System.out.println("수정에 실패하였습니다.");
	}// end of MP

	private void boardList() { // 글 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardList();
		System.out.println("▶▶▶ 게시판 목록 ◀◀◀");
		for (BoardVO vo : list) {
			vo.toString();
		}
	}// end of BL

	private void boardSelect() { // 글 조회
		BoardVO vo = new BoardVO();
		System.out.print("조회할 글번호 > ");
		vo.setBoardid(sc.next());
		sc.nextLine();
		System.out.println(vo.getSubject());
	}// end of BS

	private void boardInsert() { // 글 입력
		BoardVO vo = new BoardVO();
		System.out.print("글번호 > ");
		vo.setBoardid(sc.next());
		sc.nextLine();
		System.out.print("글제목 > ");
		vo.setTitle(sc.nextLine());
		System.out.print("글내용 > ");
		vo.setSubject(sc.nextLine());
		System.out.print("작성자 > ");
		vo.setWriter(sc.next());
		sc.nextLine();

		int n = boDao.boardInsert(vo);
		if (n != 0)
			System.out.println("입력을 성공하였습니다.");
		else
			System.out.println("입력을 실패하였습니다.");
	}// end of BI

	private void boardUpdate(String id) { // 글 수정
		BoardVO vo = new BoardVO();
		System.out.println("수정할 글 번호 > ");
		String boardId = sc.next();
		sc.nextLine();
		vo.setBoardid(boardId);
		vo = boDao.boardSelect(vo);
		if (vo.getWriter().equals(id)) {
			vo = new BoardVO();
			System.out.println("수정할 글 제목 > ");
			vo.setTitle(sc.nextLine());
			System.out.println("수정할 글 내용 > ");
			vo.setSubject(sc.nextLine());
			vo.setWriter(id);
			vo.setBoardid(boardId);
			int n = boDao.boardUpdate(vo);
			if (n != 0)
				System.out.println("수정을 성공하였습니다.");
			else
				System.out.println("수정을 실패하였습니다.");
		} else {
			System.out.println("본인의 글만 수정 가능합니다.");
			vo.toString();
		}
	}// end of BU

	private void boardDelete() { // 글 삭제
		BoardVO vo = new BoardVO();
		System.out.println("삭제할 글 번호 > ");
		vo.setBoardid(sc.next());
		sc.nextLine();

		int n = boDao.boardDelete(vo);
		if (n != 0)
			System.out.println("삭제를 성공하였습니다.");
		else
			System.out.println("삭제를 실패하였습니다.");

	}// end of BD

	public void run(MemberVO vo) {
		userMenu(vo);
	}// end of run

}// end of class
