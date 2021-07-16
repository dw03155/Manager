package co.hazzys.manager.menu;

import java.util.Scanner;

import co.hazzys.manager.member.service.MemberService;
import co.hazzys.manager.member.serviceImpl.MemberServiceImpl;
import co.hazzys.manager.member.vo.MemberVO;

public class LoginMenu {
	private MemberService memberDao = new MemberServiceImpl();// 로그인 처리
	private Scanner sc = new Scanner(System.in);

	// 사용자 메뉴 객체 생성
	private UserMenu user = new UserMenu();
	// 관리자 메뉴 객체 생성
	private AdminMenu admin = new AdminMenu();

	private void loginTitle() { // 로그인 화면 출력
		System.out.println("🔻🔻🔻🔻🔻🔻🔻🔻🔻");
		System.out.println("      로 그 인");
		System.out.println("🔺🔺🔺🔺🔺🔺🔺🔺🔺");
	}// end of LT

	private MemberVO loginCheck() { // 로그인 확인
		MemberVO vo = new MemberVO();
		boolean run = false;
		int loginCount = 1;
		do {
			loginTitle();
			System.out.print("ID : ");
			vo.setId(sc.next());
			sc.nextLine();
			System.out.print("Password : ");
			vo.setPassword(sc.next());
			sc.nextLine();

			vo = memberDao.loginCheck(vo);
			if (vo.getName() != null) {// 이름이 존재하면
				run = true;
			} else {
				System.out.println("등록되지 않는 아이디거나 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
				if (loginCount == 3) { // 3번만 반복
					run = true;
					System.out.println("로그인시도 " + loginCount + "회 초과하였습니다. 관리자에게 문의하세요.");
					vo = new MemberVO(); // vo 초기화(null)- 종료하기 위해
				} else
					loginCount++;
			}
		} while (!run); // 무한 반복
		return vo;
	}// end of LC

	public void login() { // 로그인 후 권한에 따른 사용자 관리자메뉴
		MemberVO vo = new MemberVO();
		vo = loginCheck();
		if (vo.getId() != null) { // 주메뉴 호출
			if (vo.getAuthor().equals("ADMIN"))
				admin.run(vo);
			else
				user.run(vo);
		}
		sc.close();
	}// end of login
}
