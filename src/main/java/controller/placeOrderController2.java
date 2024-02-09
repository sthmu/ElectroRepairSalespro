package controller;

import DTO.CategoryItemDto;
import DTO.CustomerDto;
import DTO.OrderDto;
import Services.custom.impl.CustomerBoImpl;
import Services.custom.impl.OrderBoImpl;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class placeOrderController2 {
    public JFXTextField emailTxt;
    public JFXTextField custNameTxt;
    public JFXTextField phoneTxt;
    public JFXTextArea descriptionTxt;
    public BorderPane pane;


    CategoryItemDto selecteItemDto;

    CustomerDto customerDto;


    public void initialize(CategoryItemDto selectedItem) {
        this.selecteItemDto = selectedItem;
    }

    public void placeOrdeBtnOnPress(ActionEvent actionEvent) {
        if (validateTxtBoxes()) {
             customerDto = new CustomerDto(emailTxt.getText(), custNameTxt.getText(), phoneTxt.getText());
            System.out.println("before CustomerId"+customerDto.getId());
            CustomerBoImpl customerBo = new CustomerBoImpl();
            boolean isCustomerSaved = customerBo.saveCustomer(customerDto);
            System.out.println("After CustomerId"+customerDto.getId());

            if (isCustomerSaved) {
                OrderDto orderDto = new OrderDto(""+ LocalDate.now(),customerDto.getId(), selecteItemDto.getCatItemCode(),"ACTIVE", descriptionTxt.getText());
                new OrderBoImpl().placeOrder(orderDto);
            }
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

    public void bckBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrder1.fxml"))));
    }
}