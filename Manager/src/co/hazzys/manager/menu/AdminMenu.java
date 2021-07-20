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

public class AdminMenu {
	private Scanner sc = new Scanner(System.in);
	private MemberService memDao = new MemberServiceImpl(); // 회원 정보 수정
	private BoardService boDao = new BoardServiceImpl(); // 게시글 관리

	private void adminMenu(MemberVO vo) {
		System.out.println(vo.getName() + "님 관리자로 로그인되었습니다.");
		boolean run = false;
		do {
			title();
			int choice;
			switch (choice = sc.nextInt()) {
			case 1:
				userManager();
				break;
			case 2:
				boardManager();
				break;
			case 3:
				run = true;
				System.out.println("작업을 종료합니다.");
				break;
			}
		} while (!run);
	}

	private void title() {
		System.out.println();
		System.out.println("▶▶▶ 관리자 메뉴 ◀◀◀");
		System.out.println("▶  1. 사용자 관리	 ◀");
		System.out.println("▶  2. 게시판 관리	 ◀");
		System.out.println("▶  3. 종료	 ◀");
		System.out.println("▶▶▶▶▶▶▶◀◀◀◀◀◀◀");
		System.out.println("메뉴를 선택하세요.");
	}

	private void userManager() { // 사용자 관리
		boolean run = false;
		do {
			int chk;
			subMemberTitle(); // 사용자 메뉴 호출
			switch (chk = sc.nextInt()) {
			case 1:
				memberList(); // 회원 목록
				break;
			case 2:
				sc.nextLine();
				memberSelect(); // 회원 조회
				break;
			case 3:
				sc.nextLine();
				memberDelete(); // 회원 삭제
				break;
			case 4: // 돌아가기
				run = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}
		} while (!run);
	}

	private void subMemberTitle() {
		System.out.println();
		System.out.println("▶▶▶ 사용자 관리 ◀◀◀");
		System.out.println("▶  1. 회원 목록	 ◀");
		System.out.println("▶  2. 회원 조회	 ◀");
		System.out.println("▶  3. 회원 삭제	 ◀");
		System.out.println("▶  4. 돌아가기	 ◀");
		System.out.println("▶▶▶▶▶▶▶◀◀◀◀◀◀◀");
		System.out.print("메뉴를 선택하세요.");
	}//end of SM

	private void memberList() { // 회원 목록
		List<MemberVO> members = new ArrayList<MemberVO>();
		members = memDao.memberList();
		System.out.println("▶▶▶ 사용자 목록 ◀◀◀");
		for (MemberVO member : members) {
			member.toString();
		}
	}//end of ML

	private void memberSelect() { // 회원 조회
		MemberVO member = new MemberVO();
		System.out.print("검색할 회원 ID를 입력하세요.");
		member.setId(sc.nextLine());
		member = memDao.memberSelect(member);

		member.toString(); // 한줄이니까 for 반복문 쓸 필요 없음.
	}//end of MS

	private void memberDelete() { // 회원 삭제
		MemberVO member = new MemberVO();
		System.out.println("삭제할 회원ID를 입력하세요.");
		member.setId(sc.nextLine());
		int n = memDao.memberDelete(member);
		if (n != 0)
			System.out.println("회원 삭제 성공하였습니다.");
		else
			System.out.println("회원 삭제에 실패하였습니다.");
	}//end of MD

	private void boardManager() { // 게시판 관리
		boolean run = false;
		do {
			int chk;
			subBoardTitle();
			switch (chk = sc.nextInt()) {
			case 1:
				boardList(); // 글 목록
				break;
			case 2:
				sc.nextLine();
				boardSelect(); // 글 조회
				break;
			case 3:
				boardInsert(); // 글 쓰기
				break;
			case 4:
				boardUpdate(); // 글 수정
				break;
			case 5:
				boardDelete(); // 글 삭제
				break;
			case 6: // 돌아가기
				run = true;
				System.out.println("목록으로 돌아갑니다.");
				break;
			}
		} while (!run);
	}//end of BM

	private void subBoardTitle() {
		System.out.println();
		System.out.println("▶▶▶ 게시판 관리 ◀◀◀");
		System.out.println("▶   1. 글 목록	 ◀");
		System.out.println("▶   2. 글 조회	 ◀");
		System.out.println("▶   3. 글 쓰기	 ◀");
		System.out.println("▶   4. 글 수정	 ◀");
		System.out.println("▶   5. 글 삭제	 ◀");
		System.out.println("▶   6. 돌아가기	 ◀");
		System.out.println("▶▶▶▶▶▶▶◀◀◀◀◀◀◀");
		System.out.print("메뉴를 선택하세요.");
	}//end of SBT

	private void boardList() { // 글 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = boDao.boardList();
		System.out.println("▶▶▶ 게시판 목록 ◀◀◀");
		for (BoardVO vo : list) {
			vo.toString();
		}
	}//end of BL

	private void boardSelect() { // 글 조회
		BoardVO vo = new BoardVO();
		System.out.println("조회할 글번호 > ");
		vo.setBoardId(sc.next());sc.nextLine();
		vo = boDao.boardSelect(vo);
		System.out.println(vo.getSubject());
	}// end of BS

	private void boardInsert() { // 글 쓰기
		BoardVO vo = new BoardVO();
		System.out.print("글번호 > ");
		vo.setBoardId(sc.next()); sc.nextLine();
		System.out.print("글제목 > ");
		vo.setTitle(sc.nextLine());
		System.out.print("글내용 > ");
		vo.setSubject(sc.nextLine());
		System.out.print("작성자 > ");
		vo.setWriter(sc.nextLine());

		int n = boDao.boardInsert(vo);
		if (n != 0)
			System.out.println("입력을 성공하였습니다.");
		else
			System.out.println("입력을 실패하였습니다.");
	}// end of BI

	private void boardUpdate() { // 글 수정 (본인글만)
		BoardVO vo = new BoardVO();
		System.out.println("수정할 글 번호 > ");
		vo.setBoardId(sc.next());sc.nextLine();
		System.out.println("수정할 글 제목 > ");
		vo.setTitle(sc.nextLine());
		System.out.println("수정할 글 내용 > ");
		vo.setSubject(sc.nextLine());

		int n = boDao.boardUpdate(vo);
		if (n != 0)
			System.out.println("수정을 성공하였습니다.");
		else
			System.out.println("수정을 실패하였습니다.");
	}// end of BU

	private void boardDelete() { // 글 삭제
		BoardVO vo = new BoardVO();
		System.out.println("삭제할 글 번호 > ");
		vo.setBoardId(sc.next());sc.nextLine();

		int n = boDao.boardDelete(vo);
		if (n != 0)
			System.out.println("삭제를 성공하였습니다.");
		else
			System.out.println("삭제를 실패하였습니다.");
	}//end of BD

	public void run(MemberVO vo) {
		adminMenu(vo);
	}//end of run

}//end of class
