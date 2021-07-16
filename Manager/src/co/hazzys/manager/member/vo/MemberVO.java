package co.hazzys.manager.member.vo;

public class MemberVO {
	private String id;
	private String password;
	private String name;
	private String address;
	private String tel;
	private int age;
	private String author;

	public MemberVO() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		System.out.print(" | "+id+" | ");
		System.out.print(password+" | ");
		System.out.print(name+" | ");
		System.out.print(address+" | ");
		System.out.print(tel+" | ");
		System.out.print(age+" | ");
		System.out.println(author);
		return null;
	}
	
	
}
