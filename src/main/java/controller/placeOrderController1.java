package controller;

import DTO.ItemDto;
import Services.custom.impl.ItemBoImpl;
import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class placeOrderController1 implements Initializable {

    public BorderPane pane;
    public FlowPane flowPane;
    public ComboBox categoryComboBox;
    public JFXButton addItemToCatalogBtn;
    private ItemBox itemSelected = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItems();


    }
    private void loadItems() {
        List<ItemDto> itemDtoList = ItemBoImpl.getItemList();
        flowPane.getChildren().clear();
        for (ItemDto ItemDto : itemDtoList) {
            ItemBox itemBox=createItemBox(ItemDto);
            flowPane.getChildren().add(itemBox);
            
        }
    }
    private ItemBox createItemBox(ItemDto ItemDto) {
        ItemBox itemBox = new ItemBox(ItemDto);

        ImageView imageView = new ImageView();
        imageView.setImage(ItemDto.getImage());
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        Label itemCode = new Label(ItemDto.getCode());

        Label itemName = new Label(ItemDto.getDescription());


        itemBox.getChildren().addAll(imageView, itemCode, itemName);

        itemBox.setSpacing(10);

        //what happenes when clicked
        //--item clicked background becomes dark AND THE BEFORE CLICKED ONE BECOMES NORMAL
        //--item clicked triggers
        itemBox.setOnMouseClicked(mouseEvent -> handleItemClick(itemBox));

        addPressAndHoldHandler(itemBox, Duration.seconds(1),
                event -> {
                    System.out.println("68 th in placeorderCOntoler");
                });


        return itemBox;

    }

    private void addPressAndHoldHandler(ItemBox node, Duration holdTime,
                                        EventHandler<MouseEvent> handler) {

        class Wrapper<T> {
            T content;
        }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }

    private void handleItemClick(ItemBox itemClicked) {
        selectAnItem(itemClicked);
    }
    void selectAnItem(ItemBox itemClicked){
        if (itemSelected != null) {
            itemSelected.setStyle("-fx-background-color: white;");

        }
        itemClicked.setStyle("-fx-background-color: gray;");
        itemSelected = itemClicked;


    }
    Stage addItemStage;
    public void addItemToCatalogBtnOnPress(ActionEvent actionEvent) throws IOException {
        if(addItemStage==null){
            addItemStage= new Stage();
            addItemStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AddItemCatalogForm.fxml"))));
            addItemStage.show();

        }

        addItemStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                loadItems();
                System.out.println("ohh yeah");

            }
        });

        addItemStage.setOnHiding(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                loadItems();
                System.out.println("ohh yeah");

            }
        });
    }
    public void addCustomerAndPlaceOrderBtnOnPress(ActionEvent actionEvent) {


    }


}

class ItemBox extends VBox {
    @Getter
    @Setter
    private ItemDto item;

    ItemBox(ItemDto ItemDto) {
        super();
        this.item = ItemDto;
    }

}


