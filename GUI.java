import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {
	File imageSelected;
	int currentClientIndex;
	int currentAdminIndex;
	int currentManagerIndex;
	@Override
	public void start(Stage primaryStage) throws Exception {
		// import all data from the Databases
		importClients();
		importAdmins();
		importManagers();
		importCars();
		
		// Log in Scene
		Label usernameLb = new Label("Username");
		Label passwordLb = new Label("Password");
		TextField userTf = new TextField();
		PasswordField passwordTf = new PasswordField();
		
		
		GridPane gp = new GridPane();
		gp.add(usernameLb, 0, 0);
		gp.add(passwordLb, 0, 1);
		gp.add(userTf, 1, 0);
		gp.add(passwordTf, 1, 1);
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(50); gp.setVgap(20);
		
		Label title = new Label("Luxury Cars"); title.setAlignment(Pos.CENTER); title.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 48));
		
		Button login = new Button("Log In"); login.setAlignment(Pos.CENTER); login.setMinWidth(200); 
		login.setTextFill(Color.web("#FFFFFF")); login.setStyle("-fx-background-color: #000000");
		
		Button newAcc = new Button("Create New Account"); newAcc.setAlignment(Pos.CENTER); newAcc.setMinWidth(200);
		newAcc.setTextFill(Color.web("#FFFFFF")); newAcc.setStyle("-fx-background-color: #000000");
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(title, gp, login, newAcc);
		vbox.setPadding(new Insets(50,50,50,50));
		vbox.setSpacing(100); 
		ImageView loginImage = new ImageView(new Image("file:images/background.jpg"));
		loginImage.setFitHeight(800); loginImage.setPreserveRatio(true);
		HBox loginHbox = new HBox(); loginHbox.getChildren().addAll(vbox, loginImage);
		
	
		
		
		// Create new Account page
		GridPane newAccGp = new GridPane();
		TextField firstName = new TextField();
		TextField lastName = new TextField();
		TextField username = new TextField();
		PasswordField password = new PasswordField();
		PasswordField rePassword = new PasswordField();
		TextField id = new TextField();
		DatePicker dob = new DatePicker();
		TextField email = new TextField();
		TextField phoneNum = new TextField();
		newAccGp.add(new Label("First Name"), 0, 0); newAccGp.add(firstName, 1, 0);
		newAccGp.add(new Label("Last Name"), 0, 1); newAccGp.add(lastName, 1, 1);
		newAccGp.add(new Label("Username"), 0, 2); newAccGp.add(username, 1, 2);
		newAccGp.add(new Label("Enter password"), 0, 3); newAccGp.add(password, 1, 3);
		newAccGp.add(new Label("Re-enter password"), 0, 4); newAccGp.add(rePassword, 1, 4);
		newAccGp.add(new Label("National ID"), 0, 5); newAccGp.add(id, 1, 5);
		newAccGp.add(new Label("Date of Birth"), 0, 6); newAccGp.add(dob, 1, 6);
		newAccGp.add(new Label("Email Address"), 0, 7); newAccGp.add(email, 1, 7);
		newAccGp.add(new Label("Phone Number"), 0, 8); newAccGp.add(phoneNum, 1, 8);
		newAccGp.setHgap(50); newAccGp.setVgap(20);
		
		Label createAccTitle = new Label("Create New Account");
		createAccTitle.setFont(Font.font(48));
		
		Button createBtn = new Button("Create"); createBtn.setMinWidth(200);
		Button cancelAccBtn = new Button("Cancel"); cancelAccBtn.setMinWidth(200);
		HBox newAccHbox = new HBox(); newAccHbox.setAlignment(Pos.CENTER); newAccHbox.setSpacing(50);
		newAccHbox.getChildren().addAll(cancelAccBtn, createBtn);
		
		VBox newAccVbox = new VBox();
		newAccVbox.getChildren().addAll(createAccTitle, newAccGp, newAccHbox);
		newAccVbox.setPadding(new Insets(50,50,50,50));
		newAccVbox.setSpacing(100);
		
		createBtn.setOnAction(e -> {
			boolean usernameUsed = false;
			Alert usernameExist = new Alert(AlertType.ERROR, "Username is already exist", ButtonType.OK);
			for (int i = 0 ; i < Client.Clients.size() ; i++) {
				if (Client.Clients.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			for (int i = 0 ; i < Admin.Admins.size() ; i++) {
				if (Admin.Admins.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			for (int i = 0 ; i < InventoryManager.Managers.size() ; i++) {
				if (InventoryManager.Managers.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			
			if (!usernameUsed) {
				try {
					if (password.getText().equals(rePassword.getText())) {
						new Client(username.getText(), password.getText(), firstName.getText(), lastName.getText(), Integer.parseInt(id.getText()), dob.getValue(), email.getText(), Integer.parseInt(phoneNum.getText()) );
						Alert accCreated = new Alert(AlertType.INFORMATION, "New Client Account has been Created", ButtonType.OK);
						accCreated.setHeaderText(""); accCreated.show();
					} else {
						Alert wrongInput = new Alert(AlertType.ERROR, "Passwords don't match", ButtonType.OK);
						wrongInput.setHeaderText(""); wrongInput.show();
					}
				} catch (Exception e1) {
					Alert wrongInput = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK);
					wrongInput.setHeaderText(""); wrongInput.show();
				} 
				
			}
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
			System.out.println(Client.Clients.size());
		});
		
			
		// code
		Scene newAccScene = new Scene(newAccVbox);
		Stage newAccStage = new Stage(); newAccStage.setScene(newAccScene); newAccStage.setResizable(false);
		newAcc.setOnAction(e -> {newAccStage.show();});
		cancelAccBtn.setOnAction(e -> {newAccStage.hide();});
		
		
		//Client Menu pane
		Button viewCarsBtn = new Button("View Available Cars"); viewCarsBtn.setMinWidth(200); 
		viewCarsBtn.setTextFill(Color.web("#FFFFFF")); viewCarsBtn.setStyle("-fx-background-color: #000000");
		Button reserveCarBtn = new Button("Reserve A Car"); reserveCarBtn.setMinWidth(200);
		reserveCarBtn.setTextFill(Color.web("#FFFFFF")); reserveCarBtn.setStyle("-fx-background-color: #000000");
		Button returnCarBtn = new Button("Reurn A Car"); returnCarBtn.setMinWidth(200);
		returnCarBtn.setTextFill(Color.web("#FFFFFF")); returnCarBtn.setStyle("-fx-background-color: #000000");
		VBox clientMenu = new VBox(); clientMenu.setAlignment(Pos.CENTER); 
		clientMenu.setPadding(new Insets(50,50,50,50)); clientMenu.setSpacing(120);
		clientMenu.getChildren().addAll(viewCarsBtn, reserveCarBtn, returnCarBtn);
		clientMenu.setStyle("-fx-background-color: #D3D3D3");
		
		
		// Client view cars page
		GridPane viewCarsGp = new GridPane(); viewCarsGp.setAlignment(Pos.CENTER);
		viewCarsGp.setHgap(50); viewCarsGp.setVgap(50);
		ArrayList<VBox> carsDetails = new ArrayList<VBox>();
		ArrayList<ImageView> carsImages = new ArrayList<ImageView>();
		ArrayList<Label> names = new ArrayList<Label>();
		ArrayList<Label> prices = new ArrayList<Label>();
		
		for (int i=0 ; i < Car.Cars.size() ; i++) {
			if(Car.Cars.get(i).getAvailability()) {
				carsDetails.add(new VBox()); carsImages.add(new ImageView(new Image(Car.Cars.get(i).getimagePath())));
				carsImages.get(i).setPreserveRatio(true); carsImages.get(i).setFitWidth(300); carsImages.get(i).setFitHeight(200);
				names.add(new Label(Car.Cars.get(i).getCarName() + " (" + Car.Cars.get(i).getModelYear() + ")"));
				names.get(i).setFont(Font.font(18));
				prices.add(new Label("Price: "+Car.Cars.get(i).getPricePD()+" SAR/Day"));
				prices.get(i).setFont(Font.font(18));
				carsDetails.get(i).getChildren().addAll(carsImages.get(i), names.get(i), prices.get(i));
				carsDetails.get(i).setStyle("-fx-background-color: #D3D3D3"); carsDetails.get(i).setAlignment(Pos.CENTER);
				carsDetails.get(i).setSpacing(30); carsDetails.get(i).setPadding(new Insets(20,20,20,20));
			}
		}
		
		for (int i=0,j=0 ; i < carsDetails.size() ; i+=2,j++) {
			viewCarsGp.add(carsDetails.get(i), 0, j);
			if (i < carsDetails.size()-1)
				viewCarsGp.add(carsDetails.get(i+1), 1, j);
		}
		
		
		
		
		
		// CLient Reserve a car page
		Label resCarTitle = new Label("Select the Car You Want to Reserve"); resCarTitle.setFont(Font.font(24));
		ComboBox<Car> selectCar = new ComboBox<Car>(); 
		for (int i=0 ; i < Car.Cars.size() ; i++)
			selectCar.getItems().add(Car.Cars.get(i));
		
		selectCar.setMinWidth(400);
		Label durationTitle = new Label("Select Duration"); durationTitle.setFont(Font.font(24));
		
		DatePicker from = new DatePicker();
		DatePicker to = new DatePicker();
		HBox resCarHbox = new HBox(); resCarHbox.getChildren().addAll(new Label("From"), from, new Label("\t"), new Label("To"), to);
		resCarHbox.setSpacing(20); resCarHbox.setMinWidth(600);
		
		Label creditTitle = new Label("Enter Credit Card Information"); creditTitle.setFont(Font.font(24));
		
		HBox creditInfo1 = new HBox(); creditInfo1.setSpacing(40);
		TextField cardNumber = new TextField();
		TextField cardName = new TextField();
		creditInfo1.getChildren().addAll(new Label("Card Number"), cardNumber, new Label("Name on Card"), cardName);
		
		HBox creditInfo2 = new HBox(); creditInfo2.setSpacing(0);
		Pane blank = new Pane(); blank.setMinWidth(47); Pane blank0 = new Pane(); blank0.setMinWidth(85);
		ComboBox<Integer> cardDateM = new ComboBox<Integer>(); cardDateM.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12);
		ComboBox<Integer> cardDateY = new ComboBox<Integer>(); cardDateY.getItems().addAll(2020,2021,2022,2023,2024,2025,2026,2027,2028);
		Label ccvLbl = new Label("CCV"); ccvLbl.setMinWidth(130); TextField cvv = new TextField(); cvv.setMaxWidth(80);
		creditInfo2.getChildren().addAll(new Label("Expiary Date"), blank, cardDateM, cardDateY, blank0, ccvLbl, cvv);
		
		CheckBox agree = new CheckBox("I agree to the company Terms of Service and Privacy Policy"); agree.setAlignment(Pos.CENTER); agree.setFont(Font.font(null, FontWeight.BOLD, 16));
		
		Button submitRes = new Button("Submit"); submitRes.setMinWidth(150); 
		
		VBox resCarVbox = new VBox(); resCarVbox.getChildren().addAll(resCarTitle, selectCar, durationTitle, resCarHbox, creditTitle, creditInfo1, creditInfo2, agree, submitRes);
		resCarVbox.setPadding(new Insets(50,50,50,50)); resCarVbox.setSpacing(50); resCarVbox.setAlignment(Pos.CENTER);
		
		submitRes.setOnAction(e -> {
			if (agree.isSelected()) {
				try {
					int days = (int) ChronoUnit.DAYS.between(from.getValue(), to.getValue());
					if (days < 1) {
						Alert fliped = new Alert(AlertType.ERROR, "The Dates are incorrect", ButtonType.OK);
						fliped.setHeaderText(""); fliped.show();
					} else {
						Client.Clients.get(currentClientIndex).setCreditCard(new Payment(new BigInteger(cardNumber.getText()), cardName.getText(), cardDateM.getValue(), cardDateY.getValue(), Integer.parseInt(cvv.getText())));
						Client.Clients.get(currentClientIndex).setReservation(new Reservation(selectCar.getValue(), from.getValue(), to.getValue()));
						selectCar.getValue().setAvailability(false);
						Alert done = new Alert(AlertType.INFORMATION, "Your reservation is completed, please pick up the car on the chosen date", ButtonType.OK);
						done.setHeaderText(""); done.show();
					}
				} catch(Exception e1) {
					Alert wrongInput = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK);
					wrongInput.setHeaderText(""); wrongInput.show();
				}
			} else {
				Alert checkAgree = new Alert(AlertType.ERROR, "You need to agree on our Terms of Service and Privacy Policy to proceed", ButtonType.OK);
				checkAgree.setHeaderText(""); checkAgree.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		// Client return a car page
		Label returnTitle = new Label("Select the car you want to return"); returnTitle.setFont(Font.font(24));
		ComboBox<Car> returnCar = new ComboBox<Car>();
		
		Button calcCost = new Button("Calculate Cost");
		Label cost = new Label("Cost = 00,00 SAR");
		
		calcCost.setOnAction(e -> {
			int days = (int) ChronoUnit.DAYS.between(Client.Clients.get(currentClientIndex).getReservation().getFromDate(), Client.Clients.get(currentClientIndex).getReservation().getToDate());
			cost.setText("Cost = " + returnCar.getValue().getPricePD() * days + " SAR");
		});
		
		Label payMethod = new Label("Select your payment method"); payMethod.setFont(Font.font(24));
		RadioButton onSite = new RadioButton("Pay On-Site");
		RadioButton online = new RadioButton("Pay online using credit card");
		ToggleGroup payment = new ToggleGroup(); 
		onSite.setToggleGroup(payment); online.setToggleGroup(payment);
		
		
		Button proceedBtn = new Button("Proceed"); 
		VBox returnVbox = new VBox(); returnVbox.getChildren().addAll(returnTitle, returnCar, calcCost, cost, payMethod, onSite, online, proceedBtn);
		returnVbox.setPadding(new Insets(50,50,50,50)); returnVbox.setSpacing(50); returnVbox.setAlignment(Pos.CENTER);
		
		proceedBtn.setOnAction(e -> {
			if(onSite.isSelected()) {
				Alert doneSite = new Alert(AlertType.INFORMATION, "Done! Please return the car to the checkout site and pay", ButtonType.OK);
				doneSite.setHeaderText(""); doneSite.show();
				Client.Clients.get(currentClientIndex).setReservation(null);
				returnVbox.setDisable(true);
			} else if (online.isSelected()) {
				Alert doneOnline = new Alert(AlertType.INFORMATION, "Done! Please return the car to the checkout site", ButtonType.OK);
				doneOnline.setHeaderText(""); doneOnline.show();
				Client.Clients.get(currentClientIndex).setReservation(null);
				returnVbox.setDisable(true);
			} else {
				Alert doneOnline = new Alert(AlertType.WARNING, "Please select the payment method", ButtonType.OK);
				doneOnline.setHeaderText(""); doneOnline.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
			
		});
		
		
		
		// StackPane for all Client Panes
		StackPane clientPanes = new StackPane();
		clientPanes.getChildren().addAll(viewCarsGp, resCarVbox, returnVbox);
		viewCarsGp.setVisible(false); resCarVbox.setVisible(false); returnVbox.setVisible(false);
		
		viewCarsBtn.setOnAction(e -> {viewCarsGp.setVisible(true); resCarVbox.setVisible(false); returnVbox.setVisible(false);});
		
		reserveCarBtn.setOnAction(e -> {viewCarsGp.setVisible(false); resCarVbox.setVisible(true); returnVbox.setVisible(false);});
		returnCarBtn.setOnAction(e -> {
			try {
				returnVbox.setDisable(false);
				viewCarsGp.setVisible(false); resCarVbox.setVisible(false); returnVbox.setVisible(true);
				returnCar.getItems().add(Client.Clients.get(currentClientIndex).getReservation().getReservedCar());
			} catch(Exception e1) {
				Alert noRes = new Alert(AlertType.WARNING, "You don't have any reserved cars to return", ButtonType.OK);
				noRes.setHeaderText(""); noRes.show();
				returnVbox.setDisable(true);
			}
			});
		
		
		// Add client panes Togather
		HBox clientPane = new HBox();
		clientPane.getChildren().addAll(clientMenu, clientPanes);
		HBox clientBar = new HBox(); Label barTitle = new Label("Luxury Cars"); 
		barTitle.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 38)); Button logout = new Button("Log Out");
		logout.setStyle("-fx-background-color: #FFFFFF");
		Label cLoggedin = new Label("Logged in as, Client"); cLoggedin.setTextFill(Color.web("#ffffff"));
		cLoggedin.setFont(Font.font(18));
		Pane blankPane1 = new Pane(); blankPane1.setMinWidth(500);
		
		clientBar.getChildren().addAll(barTitle, blankPane1, cLoggedin, logout); clientBar.setSpacing(20); clientBar.setPadding(new Insets(10, 50, 10, 50));
		clientBar.setStyle("-fx-background-color: #000000"); barTitle.setTextFill(Color.web("#FFFFFF")); clientBar.setAlignment(Pos.CENTER);
		
		VBox clientMain = new VBox(); clientMain.getChildren().addAll(clientBar, clientPane);
		
		
		
		
		
		// Admin Menu pane
		Button addAccBtn = new Button("Add Employee Account"); addAccBtn.setMinWidth(250);
		addAccBtn.setTextFill(Color.web("#FFFFFF")); addAccBtn.setStyle("-fx-background-color: #000000");
		Button modAccBtn = new Button("Modify Employee Information"); modAccBtn.setMinWidth(250);
		modAccBtn.setTextFill(Color.web("#FFFFFF"));modAccBtn.setStyle("-fx-background-color: #000000");
		Button modCarBtn = new Button("Change Availability of a Car"); modCarBtn.setMinWidth(250);
		modCarBtn.setTextFill(Color.web("#FFFFFF")); modCarBtn.setStyle("-fx-background-color: #000000");
		VBox adminMenu = new VBox(); adminMenu.setAlignment(Pos.CENTER); 
		adminMenu.setPadding(new Insets(50,30,50,30)); adminMenu.setSpacing(120);
		adminMenu.getChildren().addAll(addAccBtn, modAccBtn, modCarBtn);
		adminMenu.setStyle("-fx-background-color: #D3D3D3");
				
		
		// Add Employee Pane
		GridPane newEmpGp = new GridPane();
		TextField firstNameE = new TextField();
		TextField lastNameE = new TextField();
		TextField usernameE = new TextField();
		PasswordField passwordE = new PasswordField();
		TextField idE = new TextField();
		DatePicker dobE = new DatePicker();
		TextField emailE = new TextField();
		TextField phoneNumE = new TextField();
		newEmpGp.add(new Label("First Name"), 0, 0); newEmpGp.add(firstNameE, 1, 0);
		newEmpGp.add(new Label("Last Name"), 0, 1); newEmpGp.add(lastNameE, 1, 1);
		newEmpGp.add(new Label("Username"), 0, 2); newEmpGp.add(usernameE, 1, 2);
		newEmpGp.add(new Label("Enter password"), 0, 3); newEmpGp.add(passwordE, 1, 3);
		newEmpGp.add(new Label("National ID"), 0, 4); newEmpGp.add(idE, 1, 4);
		newEmpGp.add(new Label("Date of Birth"), 0, 5); newEmpGp.add(dobE, 1, 5);
		newEmpGp.add(new Label("Email Address"), 0, 6); newEmpGp.add(emailE, 1, 6);
		newEmpGp.add(new Label("Phone Number"), 0, 7); newEmpGp.add(phoneNumE, 1, 7);
		newEmpGp.setHgap(50); newEmpGp.setVgap(20); newEmpGp.setAlignment(Pos.CENTER);
		
		Label createEmpTitle = new Label("Create New Employee Account");
		createEmpTitle.setFont(Font.font(48));
		
		Label authority = new Label("Authority Level");
		RadioButton rb1 = new RadioButton("Admin");
		RadioButton rb2 = new RadioButton("Inventory Manager");
		ToggleGroup group = new ToggleGroup(); rb1.setToggleGroup(group); rb2.setToggleGroup(group);
		HBox auth = new HBox(); auth.getChildren().addAll(authority, rb1, rb2); auth.setSpacing(50);
		auth.setAlignment(Pos.CENTER);
		
		Button createEmpBtn = new Button("Create"); createEmpBtn.setMinWidth(200);
		HBox newEmpHbox = new HBox(); newEmpHbox.setAlignment(Pos.CENTER); newEmpHbox.setSpacing(50);
		newEmpHbox.getChildren().add(createEmpBtn);
		
		VBox newEmpVbox = new VBox();
		newEmpVbox.getChildren().addAll(createEmpTitle, newEmpGp, auth, newEmpHbox);
		newEmpVbox.setPadding(new Insets(50,50,50,50));
		newEmpVbox.setSpacing(80);
		
		createEmpBtn.setOnAction(e -> {
			boolean usernameUsed = false;
			Alert usernameExist = new Alert(AlertType.ERROR, "Username is already exist", ButtonType.OK);
			for (int i = 0 ; i < Client.Clients.size() ; i++) {
				if (Client.Clients.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			for (int i = 0 ; i < Admin.Admins.size() ; i++) {
				if (Admin.Admins.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			for (int i = 0 ; i < InventoryManager.Managers.size() ; i++) {
				if (InventoryManager.Managers.get(i).getUsername().equals(username.getText())) {
					usernameUsed = true;
					usernameExist.setHeaderText(""); usernameExist.show();
				}
			}
			
			if (!usernameUsed) {
				if (rb1.isSelected()) {
					try {
						new Admin(usernameE.getText(), passwordE.getText(), firstNameE.getText(), lastNameE.getText(), Integer.parseInt(idE.getText()), dobE.getValue(), emailE.getText(), Integer.parseInt(phoneNumE.getText()));
						importAdmins();
						Alert accCreated = new Alert(AlertType.INFORMATION, "New Admin Account has been Created", ButtonType.OK);
						accCreated.setHeaderText(""); accCreated.show();
					} catch (Exception e1) {
						Alert wrongInput = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK);
						wrongInput.setHeaderText(""); wrongInput.show();
					}
				} else if (rb2.isSelected()) {
					try {
						new InventoryManager(usernameE.getText(), passwordE.getText(), firstNameE.getText(), lastNameE.getText(), Integer.parseInt(idE.getText()), dobE.getValue(), emailE.getText(), Integer.parseInt(phoneNumE.getText()));
						importClients();
						Alert accCreated = new Alert(AlertType.INFORMATION, "New Inventory Manager Account has been Created", ButtonType.OK);
						accCreated.setHeaderText(""); accCreated.show();
					} catch (Exception e1) {
						Alert wrongInput = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK);
						wrongInput.setHeaderText(""); wrongInput.show();
					}
				}
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		// Modify Employee Pane
		Label modTitle = new Label("Select the employee you want to modify");
		modTitle.setFont(Font.font(24));
		ComboBox<User> employees = new ComboBox<User>(); employees.setMinWidth(200);
		for(int i=0 ; i < Admin.Admins.size() ; i++)
			employees.getItems().add(Admin.Admins.get(i));
		for(int i=0 ; i < InventoryManager.Managers.size() ; i++)
			employees.getItems().add(InventoryManager.Managers.get(i));
		
		HBox empDet1 = new HBox();
		TextField HoW = new TextField(); TextField eAbs = new TextField();
		Pane blank1 = new Pane(); blank1.setMinWidth(100); empDet1.setSpacing(20);
		empDet1.getChildren().addAll(new Label("Hours of work"), HoW, blank1, new Label("Excused Absences"), eAbs);
		
		HBox empDet2 = new HBox(); empDet2.setPadding(new Insets(0,0,0,55));
		TextField salary = new TextField(); TextField unAbs = new TextField();
		Pane blank2 = new Pane(); blank2.setMinWidth(80); empDet2.setSpacing(20);
		empDet2.getChildren().addAll(new Label("Salary"), salary, blank2, new Label("Unexcused Absences"), unAbs);
		
		Label position = new Label("Position:");
		RadioButton rb1Mod = new RadioButton("Admin"); rb1Mod.setDisable(true);
		RadioButton rb2Mod = new RadioButton("Inventory Manager"); rb2Mod.setDisable(true);
		ToggleGroup groupMod = new ToggleGroup(); rb1Mod.setToggleGroup(groupMod); rb2Mod.setToggleGroup(groupMod);
		HBox pos = new HBox(); pos.getChildren().addAll(position, rb1Mod, rb2Mod); pos.setSpacing(50);
		pos.setAlignment(Pos.CENTER);
		
		Button modEmpBtn = new Button("Apply Modifications"); modEmpBtn.setMinWidth(200);
		
		VBox modEmpVbox = new VBox(); modEmpVbox.getChildren().addAll(modTitle, employees, empDet1, empDet2, pos, modEmpBtn);
		modEmpVbox.setPadding(new Insets(50,50,50,50)); modEmpVbox.setSpacing(80); modEmpVbox.setAlignment(Pos.CENTER);
		
		employees.setOnAction(e -> {
			int index = employees.getItems().indexOf(employees.getValue());
			if (index >= Admin.Admins.size()) {
				index -= Admin.Admins.size();
				HoW.setText(""+InventoryManager.Managers.get(index).getHoursOfWork());
				salary.setText(""+InventoryManager.Managers.get(index).getSalary());
				eAbs.setText(""+InventoryManager.Managers.get(index).getExcusedAbs());
				unAbs.setText(""+InventoryManager.Managers.get(index).getUnexcuseAbs());
				rb2Mod.setSelected(true);
			} else {
				HoW.setText(""+Admin.Admins.get(index).getHoursOfWork());
				salary.setText(""+Admin.Admins.get(index).getSalary());
				eAbs.setText(""+Admin.Admins.get(index).getExcusedAbs());
				unAbs.setText(""+Admin.Admins.get(index).getUnexcuseAbs());
				rb1Mod.setSelected(true);
			}
		});
		
		modEmpBtn.setOnAction(e -> {
			int index = employees.getItems().indexOf(employees.getValue());
			try {
				if (index >= Admin.Admins.size()) {
					index -= Admin.Admins.size();
					InventoryManager.Managers.get(index).setHoursOfWork(Integer.parseInt(HoW.getText()));
					InventoryManager.Managers.get(index).setSalary(Integer.parseInt(salary.getText()));
					InventoryManager.Managers.get(index).setExcusedAbs(Integer.parseInt(eAbs.getText()));
					InventoryManager.Managers.get(index).setUnexcuseAbs(Integer.parseInt(unAbs.getText()));
				} else {
					Admin.Admins.get(index).setHoursOfWork(Integer.parseInt(HoW.getText()));
					Admin.Admins.get(index).setSalary(Integer.parseInt(salary.getText()));
					Admin.Admins.get(index).setExcusedAbs(Integer.parseInt(eAbs.getText()));
					Admin.Admins.get(index).setUnexcuseAbs(Integer.parseInt(unAbs.getText()));
				}
				Alert done = new Alert(AlertType.INFORMATION, "Employee information has been updated", ButtonType.OK);
				done.setHeaderText(""); done.show();
				
			} catch(Exception e1) {
				Alert wrongInput = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK);
				wrongInput.setHeaderText(""); wrongInput.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		
		// Change Availability Pane
		Label chgTitle = new Label("Select a Car"); chgTitle.setFont(Font.font(24));
		ComboBox<Car> selectCar1 = new ComboBox<Car>();
		selectCar1.setMinWidth(400);
		
		Label chgTitle2 = new Label("Choose Availability Status"); chgTitle2.setFont(Font.font(24));
		
		RadioButton available = new RadioButton("Available");
		RadioButton reserved = new RadioButton("Reserved");
		RadioButton removed = new RadioButton("Removed");
		ToggleGroup groupChg = new ToggleGroup(); 
		available.setToggleGroup(groupChg); reserved.setToggleGroup(groupChg); removed.setToggleGroup(groupChg);
		
		Button chgAppBtn = new Button("Apply"); chgAppBtn.setMinWidth(200);
		HBox btnHbox = new HBox(); btnHbox.getChildren().add(chgAppBtn); btnHbox.setAlignment(Pos.CENTER);
		
		Pane blank3 = new Pane(); blank3.setMinHeight(30);
		VBox chgVbox = new VBox(); chgVbox.getChildren().addAll(chgTitle, selectCar1, blank3, chgTitle2, available, reserved, removed, chgAppBtn);
		chgVbox.setPadding(new Insets(50,50,50,50)); chgVbox.setSpacing(80); chgVbox.setAlignment(Pos.CENTER);
		
		
		selectCar1.setOnAction(e -> {
			if (selectCar1.getValue().getAvailability())
				available.setSelected(true);
			else
				reserved.setSelected(true);
		});
		
		chgAppBtn.setOnAction(e -> {
			
			if (available.isSelected()) {
				selectCar1.getValue().setAvailability(true);
				Alert done = new Alert(AlertType.INFORMATION, "Availability status has been changed", ButtonType.OK);
				done.setHeaderText(""); done.show();
				
			} else if (reserved.isSelected() || removed.isSelected()) {
				selectCar1.getValue().setAvailability(false);
				Alert done = new Alert(AlertType.INFORMATION, "Availability status has been changed", ButtonType.OK);
				done.setHeaderText(""); done.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
				
		
		// StackPane for all admin Panes
		StackPane adminPanes = new StackPane();
		adminPanes.getChildren().addAll(newEmpVbox, modEmpVbox, chgVbox);
		newEmpVbox.setVisible(false); modEmpVbox.setVisible(false); chgVbox.setVisible(false);
		
		addAccBtn.setOnAction(e -> {newEmpVbox.setVisible(true); modEmpVbox.setVisible(false); chgVbox.setVisible(false);});
		
		modAccBtn.setOnAction(e -> {newEmpVbox.setVisible(false); modEmpVbox.setVisible(true); chgVbox.setVisible(false);});
		
		modCarBtn.setOnAction(e -> {
			newEmpVbox.setVisible(false); modEmpVbox.setVisible(false); chgVbox.setVisible(true);
			
			selectCar1.getItems().clear();
			for (int i=0 ; i < Car.Cars.size() ; i++)
				selectCar1.getItems().add(Car.Cars.get(i));
			});
		
		
		// add Admin panes togather
		HBox adminPane = new HBox();
		adminPane.getChildren().addAll(adminMenu, adminPanes);
		HBox adminBar = new HBox(); Label barTitleA = new Label("Luxury Cars"); 
		barTitleA.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 38)); Button logoutA = new Button("Log Out");
		logoutA.setStyle("-fx-background-color: #FFFFFF");
		
		Label aLoggedin = new Label("Logged in as, Admin"); aLoggedin.setTextFill(Color.web("#ffffff"));
		aLoggedin.setFont(Font.font(18));
		Pane blankPane2 = new Pane(); blankPane2.setMinWidth(600);
		
		adminBar.getChildren().addAll(barTitleA, blankPane2, aLoggedin, logoutA); adminBar.setSpacing(20); adminBar.setPadding(new Insets(10, 50, 10, 50));
		adminBar.setStyle("-fx-background-color: #000000"); barTitleA.setTextFill(Color.web("#FFFFFF")); adminBar.setAlignment(Pos.CENTER);
		VBox adminMain = new VBox(); adminMain.getChildren().addAll(adminBar, adminPane);
		
		
				
		// Inventory Manage menu pane
		Button addCarBtn = new Button("Add New Car"); addCarBtn.setMinWidth(200);
		addCarBtn.setTextFill(Color.web("#FFFFFF")); addCarBtn.setStyle("-fx-background-color: #000000");
		Button editCarBtn = new Button("Edit Car Details"); editCarBtn.setMinWidth(200);
		editCarBtn.setTextFill(Color.web("#FFFFFF")); editCarBtn.setStyle("-fx-background-color: #000000");
		Button removeCarBtn = new Button("Remove a Car"); removeCarBtn.setMinWidth(200);
		removeCarBtn.setTextFill(Color.web("#FFFFFF")); removeCarBtn.setStyle("-fx-background-color: #000000");
		VBox invMenu = new VBox(); invMenu.setAlignment(Pos.CENTER); 
		invMenu.setPadding(new Insets(0,50,0,50)); invMenu.setSpacing(120);
		invMenu.getChildren().addAll(addCarBtn, editCarBtn, removeCarBtn);
		invMenu.setStyle("-fx-background-color: #D3D3D3"); 
		
		
		// Add Car pane
		Label addTitle = new Label("Add New Car"); addTitle.setFont(Font.font(24));
		
		GridPane imGp = new GridPane(); imGp.setHgap(30); imGp.setVgap(40);
		TextField carName = new TextField(); Button carImageBtn = new Button("Select Image");
		TextField carColor = new TextField(); ComboBox<Integer> carModel = new ComboBox<Integer>(); carModel.setMinWidth(100);
		carModel.getItems().addAll(2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021);
		TextField carPrice = new TextField(); TextField carPlate = new TextField();

		imGp.add(new Label("Car's Name"), 0, 0); imGp.add(carName, 1, 0); imGp.add(new Label("Car's Image"), 2, 0); imGp.add(carImageBtn, 3, 0);
		imGp.add(new Label("Car's Color"), 0, 1); imGp.add(carColor, 1, 1); imGp.add(new Label("Model Year"), 2, 1); imGp.add(carModel, 3, 1);
		imGp.add(new Label("Price/Day"), 0, 2); imGp.add(carPrice, 1, 2); imGp.add(new Label("Plate Number"), 2, 2); imGp.add(carPlate, 3, 2);
		
		Button addBtn = new Button("Add New Car"); addBtn.setMinWidth(200);
		
		VBox addCarVbox = new VBox(); addCarVbox.getChildren().addAll(addTitle, imGp, addBtn);
		addCarVbox.setPadding(new Insets(50,50,50,50)); addCarVbox.setSpacing(80); addCarVbox.setAlignment(Pos.CENTER);
		
		carImageBtn.setOnAction(e -> {
			FileChooser filechooser = new FileChooser();
			imageSelected = filechooser.showOpenDialog(primaryStage);
		});
		
		addBtn.setOnAction(e -> {
			try {
				new Car(carName.getText(), carColor.getText(), carModel.getValue(), Double.parseDouble(carPrice.getText()), Integer.parseInt(carPlate.getText()), true, "file:"+imageSelected.getPath());
				Alert done = new Alert(AlertType.INFORMATION, "The Car has been added to the system", ButtonType.OK );
				done.setHeaderText(""); done.show();
			} catch (Exception e1) {
				Alert wrongInputs = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK );
				wrongInputs.setHeaderText(""); wrongInputs.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		
		// Edit Car Pane
		Label edtTitle = new Label("Select the car you want to modify"); edtTitle.setFont(Font.font(24));
		ComboBox<Car> selectCar2 = new ComboBox<Car>(); selectCar2.setMinWidth(400);
		
		GridPane edtGp = new GridPane(); edtGp.setHgap(30); edtGp.setVgap(40);
		TextField carColor2 = new TextField(); 
		ComboBox<Integer> carModel2 = new ComboBox<Integer>(); carModel2.setMinWidth(100);
		carModel2.getItems().addAll(2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021);
		TextField carPrice2 = new TextField(); TextField carPlate2 = new TextField();
		edtGp.add(new Label("Car's Color"), 0, 1); edtGp.add(carColor2, 1, 1); edtGp.add(new Label("Model Year"), 2, 1); edtGp.add(carModel2, 3, 1);
		edtGp.add(new Label("Price/Day"), 0, 2); edtGp.add(carPrice2, 1, 2); edtGp.add(new Label("Plate Number"), 2, 2); edtGp.add(carPlate2, 3, 2);
		
		Button edtBtn = new Button("Apply Modifications"); edtBtn.setMinWidth(200);
		
		VBox edtCarVbox = new VBox(); edtCarVbox.getChildren().addAll(edtTitle, selectCar2, edtGp, edtBtn);
		edtCarVbox.setPadding(new Insets(50,50,50,50)); edtCarVbox.setSpacing(80); edtCarVbox.setAlignment(Pos.CENTER);
		
		selectCar2.setOnAction(e -> {
			carColor2.setText(selectCar2.getValue().getCarColor());
			carModel2.setValue(selectCar2.getValue().getModelYear());
			carPrice2.setText(""+selectCar2.getValue().getPricePD());
			carPlate2.setText(""+selectCar2.getValue().getPlateNum());
		});
		
		edtBtn.setOnAction(e -> {
			try {
				selectCar2.getValue().setCarColor(carColor2.getText());
				selectCar2.getValue().setModelYear(carModel2.getValue());
				selectCar2.getValue().setPricePD(Integer.parseInt(carPrice2.getText()));
				selectCar2.getValue().setPricePD(Integer.parseInt(carPlate2.getText()));
				Alert done = new Alert(AlertType.INFORMATION, "Car information has been updated", ButtonType.OK);
				done.setHeaderText(""); done.show();
			} catch(Exception e1){
				Alert wrongInputs = new Alert(AlertType.ERROR, "Wrong Inputs", ButtonType.OK );
				wrongInputs.setHeaderText(""); wrongInputs.show();
			}
			
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		
		// Remove Car Pane
		Label rmvTitle = new Label("Select the car you want to Remove"); rmvTitle.setFont(Font.font(24));
		ComboBox<Car> selectCar3 = new ComboBox<Car>(); selectCar3.setMinWidth(400);
		
		Button rmvBtn = new Button("Remove Car"); rmvBtn.setMinWidth(200);
		
		VBox rmvCarVbox = new VBox(); rmvCarVbox.getChildren().addAll(rmvTitle, selectCar3, rmvBtn);
		rmvCarVbox.setPadding(new Insets(50,50,50,50)); rmvCarVbox.setSpacing(80); rmvCarVbox.setAlignment(Pos.CENTER);
		
		rmvBtn.setOnAction(e -> {
			try {
				int index = selectCar3.getItems().indexOf(selectCar3.getValue());
				Car.Cars.remove(index);
				Alert done = new Alert(AlertType.INFORMATION, "Car has been removed", ButtonType.OK);
				done.setHeaderText(""); done.show();
			} catch(Exception e1) {
				Alert wrongInputs = new Alert(AlertType.WARNING, "Please select a Car", ButtonType.OK );
				wrongInputs.setHeaderText(""); wrongInputs.show();
			}
			try {
				updateDatabases();
			} catch (IOException e1) {
				
			}
		});
		
		
		
		
		// StackPane for all Inventory Manager Panes
		StackPane invPanes = new StackPane();
		invPanes.getChildren().addAll(addCarVbox, edtCarVbox, rmvCarVbox);
		addCarVbox.setVisible(false); edtCarVbox.setVisible(false); rmvCarVbox.setVisible(false);
		
		addCarBtn.setOnAction(e -> {addCarVbox.setVisible(true); edtCarVbox.setVisible(false); rmvCarVbox.setVisible(false);});
		
		editCarBtn.setOnAction(e -> {
			addCarVbox.setVisible(false); edtCarVbox.setVisible(true); rmvCarVbox.setVisible(false);
			selectCar2.getItems().clear();
			
			for (int i=0 ; i <Car.Cars.size() ; i++) 
				selectCar2.getItems().add(Car.Cars.get(i));
			});
		
		removeCarBtn.setOnAction(e -> {
			addCarVbox.setVisible(false); edtCarVbox.setVisible(false); rmvCarVbox.setVisible(true);
			selectCar3.getItems().clear();
			
			for (int i=0 ; i <Car.Cars.size() ; i++) 
				selectCar3.getItems().add(Car.Cars.get(i));
			});
		
		
		// Add Inventory Manager Panes togather
		HBox invPane = new HBox();
		invPane.getChildren().addAll(invMenu, invPanes);
		HBox invBar = new HBox(); Label barTitleB = new Label("Luxury Cars"); 
		barTitleB.setFont(Font.font(null, FontWeight.BOLD, FontPosture.ITALIC, 38)); Button logoutB = new Button("Log Out");
		logoutB.setStyle("-fx-background-color: #FFFFFF");
		
		Label iLoggedin = new Label("Logged in as, Inventory Manager"); iLoggedin.setTextFill(Color.web("#ffffff"));
		iLoggedin.setFont(Font.font(18));
		Pane blankPane3 = new Pane(); blankPane3.setMinWidth(350);
		
		invBar.getChildren().addAll(barTitleB, blankPane3, iLoggedin, logoutB); invBar.setSpacing(20); invBar.setPadding(new Insets(10, 50, 10, 50));
		invBar.setStyle("-fx-background-color: #000000"); barTitleB.setTextFill(Color.web("#FFFFFF")); invBar.setAlignment(Pos.CENTER);
		VBox invMain = new VBox(); invMain.getChildren().addAll(invBar, invPane);
		
		
		
		// setScenes and Stages
		Stage clientStage = new Stage(); clientStage.setScene(new Scene(clientMain)); clientStage.setTitle("Luxury Cars");
		Stage adminStage = new Stage(); adminStage.setScene(new Scene(adminMain)); adminStage.setTitle("Luxury Cars");
		Stage managerStage = new Stage(); managerStage.setScene(new Scene(invMain)); managerStage.setTitle("Luxury Cars");
		
		login.setOnAction(e -> {
			boolean loggedIn = false;
			for (int i = 0 ; i < Client.Clients.size() ; i++) {
				if (Client.Clients.get(i).getUsername().equals(userTf.getText()) && Client.Clients.get(i).getPassword().equals(passwordTf.getText())) {
					clientStage.show(); primaryStage.hide();
					loggedIn = true;
					currentClientIndex = i;
				}
			}
			for (int i = 0 ; i < Admin.Admins.size() ; i++) {
				if (Admin.Admins.get(i).getUsername().equals(userTf.getText()) && Admin.Admins.get(i).getPassword().equals(passwordTf.getText())) {
					adminStage.show(); primaryStage.hide();
					loggedIn = true;
					currentAdminIndex = i;
				}
			}
			for (int i = 0 ; i < InventoryManager.Managers.size() ; i++) {
				if (InventoryManager.Managers.get(i).getUsername().equals(userTf.getText()) && InventoryManager.Managers.get(i).getPassword().equals(passwordTf.getText())) {
					managerStage.show(); primaryStage.hide();
					loggedIn = true;
					currentManagerIndex = i;
				}
			}
			if (!loggedIn) {
				Alert loginFail = new Alert(AlertType.ERROR, "Username or Password is Incorrect", ButtonType.OK);
				loginFail.setHeaderText(""); loginFail.show();
			}
		});
		
		logout.setOnAction(e -> {
			clientStage.close();
			primaryStage.show();
			userTf.setText("");
			passwordTf.setText("");
		});
		logoutA.setOnAction(e -> {
			adminStage.close();
			primaryStage.show();
			userTf.setText("");
			passwordTf.setText("");
		});
		logoutB.setOnAction(e -> {
			managerStage.close();
			primaryStage.show();
			userTf.setText("");
			passwordTf.setText("");
		});
		
		Scene loginPage = new Scene(loginHbox);
		primaryStage.setScene(loginPage);
		primaryStage.setTitle("Luxury Cars");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void importClients() throws IOException, ParseException {
		File clientDB = new File("ClientsDatabase.txt");
		Scanner s = new Scanner(clientDB);
		
		while (s.hasNext()) {
			new Client(s.next(), s.next(), s.next(), s.next(), s.nextInt(), LocalDate.parse(s.next()), s.next(), s.nextInt());
		}
		s.close();
	}
	
	public static void importCars() throws IOException, ParseException {
		File carDB = new File("CarsDatabase.txt");
		Scanner s = new Scanner(carDB);
		
		while (s.hasNext()) {
			String tempCar = s.nextLine(); String[] m = tempCar.split("\t");
			new Car(m[0], m[1], Integer.parseInt(m[2]), Double.parseDouble(m[3]), Integer.parseInt(m[4]), Boolean.parseBoolean(m[5]), m[6]);
		}
		s.close();
	}
	
	public static void importAdmins() throws IOException, ParseException {
		File adminDB = new File("AdminsDatabase.txt");
		Scanner s = new Scanner(adminDB);
		
		while (s.hasNext()) {
			new Admin(s.next(), s.next(), s.next(), s.next(), s.nextInt(), LocalDate.parse(s.next()), s.next(), s.nextInt());
		}
		s.close();
	}
	
	public static void importManagers() throws IOException, ParseException {
		File managerDB = new File("ManagersDatabase.txt");
		Scanner s = new Scanner(managerDB);
		
		while (s.hasNext()) {
			new InventoryManager(s.next(), s.next(), s.next(), s.next(), s.nextInt(), LocalDate.parse(s.next()), s.next(), s.nextInt());
		}
		s.close();
	}
	
	public static void updateDatabases() throws IOException {
		File clientDB = new File("ClientsDatabase.txt");
		PrintWriter pwc = new PrintWriter(clientDB);
		for (int i=0 ; i < Client.Clients.size() ; i++) {
			pwc.println(Client.Clients.get(i).getUsername() +"\t"+ Client.Clients.get(i).getPassword() +"\t"+ Client.Clients.get(i).getFirstName()
					 +"\t"+ Client.Clients.get(i).getLastName() +"\t"+ Client.Clients.get(i).getId() +"\t"+ Client.Clients.get(i).getDoB()
					 +"\t"+ Client.Clients.get(i).getEmail() +"\t"+ Client.Clients.get(i).getPhoneNum());
		}
		pwc.close();
		
		File adminDB = new File("AdminsDatabase.txt");
		PrintWriter pwa = new PrintWriter(adminDB);
		for (int i=0 ; i < Admin.Admins.size() ; i++) {
			pwa.println(Admin.Admins.get(i).getUsername() +"\t"+ Admin.Admins.get(i).getPassword() +"\t"+ Admin.Admins.get(i).getFirstName()
					 +"\t"+ Admin.Admins.get(i).getLastName() +"\t"+ Admin.Admins.get(i).getId() +"\t"+ Admin.Admins.get(i).getDoB()
					 +"\t"+ Admin.Admins.get(i).getEmail() +"\t"+ Admin.Admins.get(i).getPhoneNum());
		}
		pwa.close();
		
		File managerDB = new File("ManagersDatabase.txt");
		PrintWriter pwm = new PrintWriter(managerDB);
		for (int i=0 ; i < InventoryManager.Managers.size() ; i++) {
			pwm.println(InventoryManager.Managers.get(i).getUsername() +"\t"+ InventoryManager.Managers.get(i).getPassword() +"\t"+InventoryManager.Managers.get(i).getFirstName()
					 +"\t"+ InventoryManager.Managers.get(i).getLastName() +"\t"+ InventoryManager.Managers.get(i).getId() +"\t"+ InventoryManager.Managers.get(i).getDoB()
					 +"\t"+ InventoryManager.Managers.get(i).getEmail() +"\t"+ InventoryManager.Managers.get(i).getPhoneNum());
		}
		pwm.close();
		
		File carDB = new File("CarsDatabase.txt");
		PrintWriter pwr = new PrintWriter(carDB);
		for (int i=0 ; i < Car.Cars.size() ; i++) {
			pwr.println(Car.Cars.get(i).getCarName() +"\t"+ Car.Cars.get(i).getCarColor() +"\t"+ Car.Cars.get(i).getModelYear()
			 +"\t"+ Car.Cars.get(i).getPricePD() +"\t"+ Car.Cars.get(i).getPlateNum() +"\t"+ Car.Cars.get(i).getAvailability()
			 +"\t"+  Car.Cars.get(i).getimagePath());
		}
		pwr.close();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
