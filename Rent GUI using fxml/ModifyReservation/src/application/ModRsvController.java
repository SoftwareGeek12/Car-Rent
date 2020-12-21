package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ModRsvController {

    @FXML
    private TextField text1;

    @FXML
    private CheckBox cb1;

    @FXML
    private CheckBox cb2;

    @FXML
    private CheckBox cb3;

    @FXML
    private CheckBox cb4;

    @FXML
    private TextArea text2;

    @FXML
    private Button btn_mod;

    @FXML
    void displayMessage(ActionEvent event) throws IOException{
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Submit Cancellation request?");
    	

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // ... user chose OK
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}

    }

}

