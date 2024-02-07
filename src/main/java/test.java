import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu helpMenu = new Menu("Help");

        // Create menu items
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");

        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");

        MenuItem aboutItem = new MenuItem("About");

        // Add menu items to menus
        fileMenu.getItems().addAll(newItem, openItem, saveItem, exitItem);
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);
        helpMenu.getItems().addAll(aboutItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);

        // Create a layout
        BorderPane root = new BorderPane();

        // Set the menu bar at the top of the layout
        root.setTop(menuBar);

        // Create the scene
        Scene scene = new Scene(root, 400, 300);

        // Set the scene in the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Navigation Bar Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
