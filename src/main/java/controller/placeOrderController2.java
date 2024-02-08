package controller;

import DTO.CustomerDto;
import DTO.ItemDto;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;

import java.util.regex.Pattern;

public class placeOrderController2 {
    public JFXTextField emailTxt;
    public JFXTextField custNameTxt;
    public JFXTextField phoneTxt;
    public JFXTextArea descriptionTxt;
    public BorderPane pane;


    ItemDto selecteItemDto;

    CustomerDto customerDto;


    public void initialize(ItemDto selectedItem) {
        this.selecteItemDto = selectedItem;
    }

    public void placeOrdeBtnOnPress(ActionEvent actionEvent) {
        if (validateTxtBoxes()) {
            System.out.println("wooow" );
        }

    }

    private boolean validateTxtBoxes() {
        // Check if any field is null
        if (emailTxt.getText().isEmpty() || custNameTxt.getText().isEmpty() || phoneTxt.getText().isEmpty() || descriptionTxt.getText().isEmpty()) {
            showAlert("Please fill in all fields.");
            return false;
        }

        // Validate email
        String emailPattern = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
        if (!Pattern.matches(emailPattern, emailTxt.getText())) {
            showAlert("Invalid email format.");
            return false;
        }

        // Validate phone number length
        if (phoneTxt.getText().length() != 10) {
            showAlert("Phone number must be 10 digits long.");
            return false;
        }

        return true;
    }

    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public void checkEmail(ActionEvent actionEvent) {

    }

    public void checkEmailBtnOnAction(ActionEvent actionEvent) {
    }
}