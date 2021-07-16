package co.hazzys.manager;

import co.hazzys.manager.menu.LoginMenu;

public class MainApp {
	public static void main(String[] args) {
		LoginMenu menu = new LoginMenu();
		menu.login();
	}
}
