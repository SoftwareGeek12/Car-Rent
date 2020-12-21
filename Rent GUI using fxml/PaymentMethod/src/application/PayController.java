package application;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class PayController {

	ObservableList<String> cbox1list = 
			FXCollections.observableArrayList("01", "02", "03","04","05","06","07","08","09","10","11","12");

	ObservableList<String> cbox2list = 
			FXCollections.observableArrayList("20","21", "22", "23","24","25","26","27","28","29","30");
	@FXML
	private RadioButton rb1;

	@FXML
	private RadioButton rb2;

	@FXML
	private TextField txt1;

	@FXML
	private TextField txt2;

	@FXML
	private TextField txt3;

	@FXML
	private ChoiceBox cbox1;

	@FXML
	private ChoiceBox cbox2;

	@FXML
	private Button btn_confirm;

	@FXML
	public void initialize() {
		cbox1.setItems(cbox1list);
		cbox2.setItems(cbox2list);
	}

	@FXML
	void showMessage(ActionEvent event) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Confirm Payment Method?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
		} else {
			// ... user chose CANCEL or closed the dialog
		}

	}

}