import java.time.LocalDate;

public abstract class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int id;
	private LocalDate DoB;
	private String email;
	private int phoneNum;
	
	public User(String username, String password, String firstName, String lastName, int id, LocalDate DoB, String email, int phoneNum) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.DoB = DoB;
		this.email = email;
		this.phoneNum = phoneNum;
		
	}
	public String toString() {
		return firstName + " " + lastName;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getId() {
		return id;
	}

	public LocalDate getDoB() {
		return DoB;
	}

	public String getEmail() {
		return email;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDoB(LocalDate doB) {
		DoB = doB;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
