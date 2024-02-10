package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;

public class OrderHistoryFormController {
    public BorderPane pane;
    public JFXComboBox statusComboBox;
    public JFXTextField SearchByNameTxt;
    public ComboBox categoryComboBox;
    public JFXTreeTableView orderTbl;
    public TreeTableColumn idCol;
    public TreeTableColumn dateCol;
    public TreeTableColumn nameCol;
    public Label orderIdLbl;
    public Label customerNameLbl;
    public Label itemNameTxt;
    public JFXTextArea descriptionTxt;
    public JFXComboBox selectedOrderStatusComboBox;
    public JFXTextField phoneNumberTxt;
    public JFXTextField emailTxt;

    public void statusComboBoxOnAction(ActionEvent actionEvent) {
    }

    public void searchBarOnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void closeOrderBtnOnAction(ActionEvent actionEvent) {
    }

    public void sendEmailBtnOnAction(ActionEvent actionEvent) {
    }

    public void bckBtnOnAction(ActionEvent actionEvent) {
    }
}
