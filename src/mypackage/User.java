package mypackage;

public class User {
	public User(String string, int i) {
		this.name = string;
		this.age = i;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String name;
	public int age;
	public String emailAddress;
	
	@Override
	public String toString(){
		return "User [Name=" + name + ", Age=" + age + ", " +
	            "Email=" + emailAddress ;
	}
}

