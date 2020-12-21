import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class InventoryManager extends User {
	private int hoursOfWork;
	private double salary;
	private int excusedAbs;
	private int unexcuseAbs;
	static ArrayList<InventoryManager> Managers = new ArrayList<InventoryManager>();
	static File managersDB = new File("ManagersDatabase.txt");
	
	public InventoryManager(String username, String password, String firstName, String lastName, int id, LocalDate DoB, String email, int phoneNum) throws IOException {
		super(username, password, firstName, lastName, id, DoB, email, phoneNum);
		
		Managers.add(this);
		
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
