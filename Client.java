import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Client extends User {
	private Payment creditCard;
	private Reservation reservation;
	static ArrayList<Client> Clients = new ArrayList<Client>();
	static File clientDB = new File("ClientsDatabase.txt");
	
	public Client(String username, String password, String firstName, String lastName, int id, LocalDate DoB, String email, int phoneNum) throws IOException {
		super(username, password, firstName, lastName, id, DoB, email, phoneNum);
		
		Clients.add(this);
		
	}
	

	public Payment getCreditCard() {
		return creditCard;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setCreditCard(Payment creditCard) {
		this.creditCard = creditCard;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	
	
}
