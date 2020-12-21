import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Car {
	private String carName;
	private String carColor;
	private int modelYear;
	private double pricePD;
	private int plateNum;
	private Boolean availability;
	private String imagePath;
	
	static ArrayList<Car> Cars = new ArrayList<Car>();
	static File CarDB = new File("CarsDatabase.txt");
	
	public Car(String carName, String carColor, int modelYear, double pricePD, int plateNum, Boolean availability, String imagePath) throws IOException {
		this.carName = carName;
		this.carColor = carColor;
		this.modelYear = modelYear;
		this.pricePD = pricePD;
		this.plateNum = plateNum;
		this.availability = availability;
		this.imagePath = imagePath;
		
		Cars.add(this);
		
	}
	
	public String toString() {
		return carName + " (" + modelYear + ")";
	}

	public String getCarName() {
		return carName;
	}

	public String getCarColor() {
		return carColor;
	}

	public int getModelYear() {
		return modelYear;
	}

	public double getPricePD() {
		return pricePD;
	}

	public int getPlateNum() {
		return plateNum;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public String getimagePath() {
		return imagePath;
	}


	public static ArrayList<Car> getCars() {
		return Cars;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public void setModelYear(int modelYear) {
		this.modelYear = modelYear;
	}

	public void setPricePD(double pricePD) {
		this.pricePD = pricePD;
	}

	public void setPlateNum(int plateNum) {
		this.plateNum = plateNum;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public void setCarImage(String imagePath) {
		this.imagePath = imagePath;
	}

	public static void setCars(ArrayList<Car> cars) {
		Cars = cars;
	}
	
	
}
