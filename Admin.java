import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Admin extends User {
	private int hoursOfWork;
	private double salary;
	private int excusedAbs;
	private int unexcuseAbs;
	static ArrayList<Admin> Admins = new ArrayList<Admin>();
	static File adminDB = new File("AdminsDatabase.txt");
	
	public Admin(String username, String password, String firstName, String lastName, int id, LocalDate DoB, String email, int phoneNum) throws IOException {
		super(username, password, firstName, lastName, id, DoB, email, phoneNum);
		
		Admins.add(this);
		
		
	}
	
	public int getHoursOfWork() {
		return hoursOfWork;
	}

	public double getSalary() {
		return salary;
	}

	public int getExcusedAbs() {
		return excusedAbs;
	}

	public int getUnexcuseAbs() {
		return unexcuseAbs;
	}

	public void setHoursOfWork(int hoursOfWork) {
		this.hoursOfWork = hoursOfWork;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setExcusedAbs(int excusedAbs) {
		this.excusedAbs = excusedAbs;
	}

	public void setUnexcuseAbs(int unexcuseAbs) {
		this.unexcuseAbs = unexcuseAbs;
	}
	
	
}
