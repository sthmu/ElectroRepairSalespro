package controller;

import DTO.ItemDto;
import Services.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable {

    public JFXTextField txtItemTitle;
    public JFXTextField txtDescription;
    public JFXTextField pictureUrlLbl;
    public ImageView imageItmView;
    public AnchorPane pane;
    public JFXComboBox categoryCombo;

    private ItemBoImpl itemBo=new ItemBoImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        categoryCombo.setItems(FXCollections
                .observableArrayList("Electrical","Electronic"));
    }
    public void addUrlBtnOnPress(ActionEvent actionEvent) {
        Image image=new Image(pictureUrlLbl.getText());
        imageItmView.setImage(image);
    }

    public void addToCatalogBtnOnPress(ActionEvent actionEvent) {
        boolean isAdded= itemBo.saveItem(new ItemDto(
                itemBo.getGeneratedCode(),
                txtItemTitle.getText(),
                txtDescription.getText(),
                new Image(pictureUrlLbl.getText()),
                (String)categoryCombo.getSelectionModel().getSelectedItem()
        ));

        Stage thisStage= (Stage) pane.getScene().getWindow();

        if(isAdded){
            thisStage.close();

        }
    }


}
