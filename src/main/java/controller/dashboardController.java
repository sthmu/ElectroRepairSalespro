package controller;

import Services.BoFactory;
import Services.BoType;
import Services.custom.UserBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class dashboardController implements Initializable {
    public BorderPane pane;
    public JFXButton userBtn;

    JFXPopup userPopup;
    private JFXButton logBtn;

    private UserBo userBo= BoFactory.getInstance().getBo(BoType.USER_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initUserPopup();
    }


    public void manageUsersBtnOnAction(ActionEvent actionEvent) {
    }

    public void placeOrderBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/PlaceOrder1.fxml"))));
    }

    public void manageInventoryBtnOnAction(ActionEvent actionEvent) {
    }

    public void itemCatalogBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ItemCatalogForm.fxml"))));

    }

    public void orderHistoryBtnOnAction(ActionEvent actionEvent) throws IOException {
        Stage thisStage = (Stage) pane.getScene().getWindow();
        thisStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/OrderHistory.fxml"))));

    }
    private void initUserPopup() {
        JFXListView<JFXButton> list = new JFXListView<>();
        userPopup = new JFXPopup(list);
        logBtn = new JFXButton("Login");
        list.getItems().add(logBtn);
    }

    public void userBtnOnPress(ActionEvent actionEvent) {
        if (userBo.getLoggedInUser() == null) {
            System.out.println("Logged In User IS NULL");
            logBtn.setText("login");
            logBtn.setOnAction(e -> {
                try {
                    gotoLogin();
                } catch (IOException ae) {
                    throw new RuntimeException(ae);
                }
            });
        } else {
            logBtn.setText("Logout");
            logBtn.setOnAction(e -> {
                userBo.logout();
                userPopup.hide();
                new Alert(Alert.AlertType.CONFIRMATION,"Logged Out Successfully").show();
            });
        }
        userPopup.show(userBtn, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT);
    }
    private void gotoLogin() throws IOException {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"))));

    }


}
