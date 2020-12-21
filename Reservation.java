import java.time.LocalDate;

public class Reservation {
	private Car reservedCar;
	private LocalDate fromDate;
	private LocalDate toDate;
	
	
	public Reservation(Car reservedCar, LocalDate fromDate, LocalDate toDate) {
		this.reservedCar = reservedCar;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	
	

	public Car getReservedCar() {
		return reservedCar;
	}


	public void setReservedCar(Car reservedCar) {
		this.reservedCar = reservedCar;
	}


	public LocalDate getFromDate() {
		return fromDate;
	}


	public LocalDate getToDate() {
		return toDate;
	}


	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}


	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	
	
}
