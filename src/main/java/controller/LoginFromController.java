package controller;

import DTO.UserDto;
import Services.BoFactory;
import Services.BoType;
import Services.custom.UserBo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;


public class LoginFromController implements Initializable {


    public TextField usernameTxtField;
    public PasswordField passwordTxtField;
    public BorderPane pane;

    private UserBo userService= BoFactory.getInstance().getBo(BoType.USER_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            boolean ini=userService.registerUser(new UserDto("u0001","root","root","rootUser",10));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public void loginBtnOnAction(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        boolean isError = false;
        if(usernameTxtField.getText()!=null && passwordTxtField.getText()!=null) {
            isError =userService.login(usernameTxtField.getText(),passwordTxtField.getText());
            if(isError){
                Stage stage =(Stage) pane.getScene().getWindow();

                try {
                    //here it will rediect to the user beloging dashboard
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
                    stage.setResizable(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void createAccountOnPress(MouseEvent mouseEvent) throws IOException {
        Stage stage= (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddUserForm.fxml"))));
    }
}