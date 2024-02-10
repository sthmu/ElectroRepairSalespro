package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardController {
    public BorderPane pane;

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

    public void orderHistoryBtnOnAction(ActionEvent actionEvent) {
    }
}
